import axios from 'axios';
import React, { useState } from 'react'
import { WatchlistContent } from '../Interfaces'
import UserRating from './UserRating';
import WatchedButton from './WatchedButton';

type Props = {
    content: WatchlistContent
    setPage:React.Dispatch<React.SetStateAction<string>>;
}

const SingleWatchlistTitle = (props: Props) => {
    const [watchlist, setWatchlist] = useState<boolean>(true);

  const handleClick = () => {
    setWatchlist(!watchlist);
    axios.delete("http://localhost:8080/api/v1/user/watchlist/" + props.content.tconst);
  };

  return (
    <>
    {(watchlist) ?
      <tr className='result'>
          <td className='clickable-link' onClick={(e)=>props.setPage("title" + props.content.tconst)}>{props.content.primaryTitle}</td>
          <td>{props.content.titleType}</td>
          <td>{(props.content.averageRating === 0) ? "N/A" : props.content.averageRating}</td>
          <td><UserRating tconst = {props.content.tconst} userRating = {props.content.userRating}/></td>
          <td className='state-button'><WatchedButton tconst = {props.content.tconst} watched = {props.content.watched}/></td>
          <td className='state-button'><button className='clickable' style={{ backgroundColor:'rgb(241, 206, 165)'}} onClick={handleClick}>{"Remove from watchlist"}</button>
</td>
      </tr>
      : (null)
      }
    </>
  )
}

export default SingleWatchlistTitle