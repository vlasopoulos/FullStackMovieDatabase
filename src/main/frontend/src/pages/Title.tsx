import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { TitleRootObject, NamesFromNconsts } from '../Interfaces'
type Props = {
    tconst: string;
    setPage:React.Dispatch<React.SetStateAction<string>>;
}

const Title = (props: Props) => {
  const [loading, setLoading] = useState(true);
  const [data, setData] = useState<TitleRootObject>();
  const [nameData, setNameData] = useState<NamesFromNconsts[]>([{nconst:"",primary_name:""}]);
  
  const fetchURL: string = "http://localhost:8080/api/v1/title/" + props.tconst;

  useEffect(() => {
    const fetchAllData = async () => {
      setLoading(true);
      try {
        const fetchData = await axios.get(fetchURL);
        const localData: TitleRootObject = fetchData.data;
        setData(localData);
        let fetchNamesURL: string = "http://localhost:8080/api/v1/person-names?";
        localData.directors.forEach(element => fetchNamesURL += "nconst=" + element + "&");
        localData.writers.forEach(element => fetchNamesURL += "nconst=" + element + "&");
        
        const fetchNameData = await axios.get(fetchNamesURL.slice(0,-1));
        const localNameData: NamesFromNconsts[] = fetchNameData.data;
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
        <h1>{data?.primaryTitle}</h1>
        <p>Type: {data?.titleType}</p>
        <p>Genres: {data?.genres.toString()}</p>
        <p>Runtime: {data?.runtimeMinutes} minutes</p>
        {(data?.titleType === "tvSeries" || data?.titleType === "tvMiniSeries") ? <p>Start Year: {data?.startYear}</p> : <p>Release Year: {data?.startYear}</p>}
        {(data?.titleType === "tvSeries" || data?.titleType === "tvMiniSeries") && <p>End Year: {data?.endYear}</p>}
        <p>Average Rating: {data?.averageRating}</p>
        <p>Number of votes: {data?.numVotes}</p>
        <p>{nameData.map((object,i)=>{return <span>{object.primary_name} </span>;})}</p>
      </div>
    </div>
  )
}

export default Title