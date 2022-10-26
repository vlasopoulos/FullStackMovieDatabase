import axios from 'axios';
import React, { useEffect, useState } from 'react'
import NameFromNconst from '../components/NameFromNconst';
import Principals from '../components/Principals';
import UserRating from '../components/UserRating';
import WatchedButton from '../components/WatchedButton';
import WatchlistButton from '../components/WatchlistButton';
import { TitleRootObject, NamesFromNconsts } from '../Interfaces'

type Props = {
    tconst: string;
    setPage: React.Dispatch<React.SetStateAction<string>>;
}

const Title = (props: Props) => {
  const [loading, setLoading] = useState(true);
  const [data, setData] = useState<TitleRootObject>();
  const [nameData, setNameData] = useState<NamesFromNconsts[]>([{nconst:"",primary_name:""}]);
  
  const fetchURL: string = "http://vlasopoulos.ddns.net:8080/api/v1/title/" + props.tconst;

  useEffect(() => {
    const fetchAllData = async () => {
      setLoading(true);
      try {
        const fetchData = await axios.get(fetchURL);
        const localData: TitleRootObject = fetchData.data;
        setData(localData);
        let fetchNamesURL: string = "http://vlasopoulos.ddns.net:8080/api/v1/person-names?";
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
        <div className='user-buttons'>
          <WatchedButton tconst={data!.tconst} watched={data!.watched}/>
          <WatchlistButton tconst={data!.tconst} watchlist={data!.watchlist}/>
          <span>Your rating:</span>
          <UserRating tconst={data!.tconst} userRating={data!.userRating}/>
        </div>
        <p>Type: {data?.titleType.replaceAll(",",", ")}</p>
        <p>Genres: {data?.genres.toString().replaceAll(",",", ")}</p>
        <p>Runtime: {data?.runtimeMinutes} minutes</p>
        {(data?.titleType === "tvSeries" || data?.titleType === "tvMiniSeries") ? <p>Start Year: {data?.startYear}</p> : <p>Release Year: {data?.startYear}</p>}
        {(data?.titleType === "tvSeries" || data?.titleType === "tvMiniSeries") && <p>End Year: {data?.endYear}</p>}
        <p>Average Rating: {data?.averageRating}</p>
        <p>Number of votes: {data?.numVotes}</p>
        <p >Directors:</p>
        <ul>{data?.directors.map((nconst)=>{return <li><NameFromNconst nconst = {nconst} nameData = {nameData} setPage = {props.setPage}/></li>;})}</ul>
        <p>Writers:</p>
        <ul>{data?.writers.map((nconst)=>{return <li><NameFromNconst nconst = {nconst} nameData = {nameData} setPage = {props.setPage}/></li>;})}</ul>
        <p>Principals:</p>
        <Principals tconst = {data!.tconst} setPage = {props.setPage}/>
      </div>
    </div>
  )
}

export default Title