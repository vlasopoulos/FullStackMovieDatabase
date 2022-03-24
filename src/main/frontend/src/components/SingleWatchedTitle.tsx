import axios from 'axios';
import React, { useState } from 'react'
import { WatchedContent } from '../Interfaces'
import UserRating from './UserRating';
import WatchlistButton from './WatchlistButton';

type Props = {
    content: WatchedContent;
    setPage:React.Dispatch<React.SetStateAction<string>>;
}

const SingleWatchedTitle = (props: Props) => {
  const [watched, setWatched] = useState<boolean>(true);

  const handleClick = () => {
    setWatched(!watched);
    axios.delete("http://localhost:8080/api/v1/user/watched/" + props.content.tconst);
  };

  return (
    <>
    {(watched) ?
      <tr className='result'>
          <td className='clickable-link' onClick={(e)=>props.setPage("title" + props.content.tconst)}>{props.content.primaryTitle}</td>
          <td>{props.content.titleType}</td>
          <td>{(props.content.averageRating === 0) ? "N/A" : props.content.averageRating}</td>
          <td><UserRating tconst = {props.content.tconst} userRating = {props.content.userRating}/></td>
          <td className='state-button'><button className='clickable' style={{ backgroundColor: 'rgb(241, 165, 165)'}} onClick={handleClick}>Remove from Watched</button></td>
          <td className='state-button'><WatchlistButton tconst = {props.content.tconst} watchlist = {props.content.watchlist}/></td>
      </tr>
      : (null)
      }
    </>
  )
}

export default SingleWatchedTitle