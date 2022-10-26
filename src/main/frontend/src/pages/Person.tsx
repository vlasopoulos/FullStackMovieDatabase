import axios from 'axios';
import React, { useEffect, useState } from 'react'
import NameFromTconst from '../components/NameFromTconst';
import { NamesFromTconsts, PersonRootObject } from '../Interfaces';

type Props = {
    nconst: string;
    setPage: React.Dispatch<React.SetStateAction<string>>;
}

const Person = (props: Props) => {
  const [loading, setLoading] = useState(true);
  const [data, setData] = useState<PersonRootObject>();
  const [nameData, setNameData] = useState<NamesFromTconsts[]>([{tconst:"",primary_title:""}]);
  
  const fetchURL: string = "http://vlasopoulos.ddns.net:8080/api/v1/person/" + props.nconst;

  useEffect(() => {
    const fetchAllData = async () => {
      setLoading(true);
      try {
        const fetchData = await axios.get(fetchURL);
        const localData: PersonRootObject = fetchData.data;
        setData(localData);
        let fetchNamesURL: string = "http://vlasopoulos.ddns.net:8080/api/v1/title-names?";
        localData.knownForTitles.forEach(element => fetchNamesURL += "tconst=" + element + "&");
        
        const fetchNameData = await axios.get(fetchNamesURL.slice(0,-1));
        const localNameData: NamesFromTconsts[] = fetchNameData.data;
        setNameData(localNameData);
        } catch (error) {
          console.error("Error fetching names!");
        }
        setLoading(false);
      }
    fetchAllData();
  }, [fetchURL]);
  
  if (loading) return <div className='no-results'><p>Loading...</p></div>;

  return (
    <div className='margin-page'>
      <div className='page'>
        <h1>{data?.primaryName}</h1>
        <p>Professions: {data?.primaryProfession.toString().replaceAll(",",", ")}</p>
        <p>Birth Year: {data?.birthYear}</p>
        {(data?.deathYear != 0 ) && <p>Death Year: {data?.deathYear}</p>}
        
        <p >Known for:</p>
        <ul>{data?.knownForTitles.map((tconst)=>{return <li><NameFromTconst tconst = {tconst} nameData = {nameData} setPage = {props.setPage}/></li>;})}</ul>
      </div>
    </div>
  )
}

export default Person