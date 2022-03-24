import axios from 'axios';
import React, { useEffect, useState } from 'react'
import SingleWatchlistTitle from '../components/SingleWatchlistTitle';
import { WatchlistRootObject } from '../Interfaces';

type Props = {
  setPage:React.Dispatch<React.SetStateAction<string>>;
}

const Watchlist = (props: Props) => {
  const [loading, setLoading] = useState(true);
  const [data, setData] = useState<WatchlistRootObject | null>(null);

  useEffect(() => {
      const fetchData = async () =>{
        setLoading(true);
        try {
          const response = await axios.get("http://localhost:8080/api/v1/user/watchlist");
          setData(response.data);
        } catch (error) {
          console.error("Error!");
        }
        setLoading(false);
      }
      fetchData();
    }, []);

  if (loading) return <div className='no-results'><p>Loading...</p></div>;

  return (
    <div className='margin-page'>
      <div className='page'>
        {(data?.empty) ? <div className='no-results'><p>No results found.</p></div> : 
      <table>
        <tr>
          <th>Title</th>
          <th>Type</th>
          <th>Average<br/>Rating</th>
          <th>Your<br/>Rating</th>
          <th>Watched</th>
          <th>Watchlist</th>
        </tr>
        {data?.content.map((content, _index) => {
        return (
          <SingleWatchlistTitle content = {content} setPage = {props.setPage}/>
        );
        })}
      </table>}
      </div>
    </div>
  )
}

export default Watchlist