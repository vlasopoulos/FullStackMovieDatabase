import axios from 'axios';
import React, { useEffect, useState } from 'react'
import Pagination from '../components/Pagination';
import SingleWatchedTitle from '../components/SingleWatchedTitle';
import { WatchedRootObject } from '../Interfaces';

type Props = {
  setPage: React.Dispatch<React.SetStateAction<string>>;
  page: string;
}

const Watched = (props: Props) => {
  const [loading, setLoading] = useState(true);
  const [data, setData] = useState<WatchedRootObject>();

  useEffect(() => {
      const fetchData = async () =>{
        setLoading(true);
        try {
          const response = await axios.get("http://localhost:8080/api/v1/user/watched");
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
        <h1>Watched</h1>
        {(data?.empty) ? <div className='Your watched list is empty'><p>No results found.</p></div> : 
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
          <SingleWatchedTitle content = {content} setPage = {props.setPage}/>
        );
        })}
      </table>}
      <Pagination totalPages = {data!.totalPages} currentPage = {data!.number + 1} setPage = {props.setPage} page = {props.page}/>
      </div>
    </div>
  )
}

export default Watched