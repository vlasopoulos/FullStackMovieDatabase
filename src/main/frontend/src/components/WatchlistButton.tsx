import axios from 'axios'
import React, { useState } from 'react'

type Props = {
  tconst: string
  watchlist: boolean
}

const WatchlistButton = (props: Props) => {
const [watchlist,setWatchlist] = useState<boolean>(props.watchlist)

  const handleClick = () => {
    setWatchlist(!watchlist);

    if (!watchlist) {
      const body = { tconst : props.tconst };
      axios.post("http://vlasopoulos.ddns.net:8080/api/v1/user/watchlist",body);
    } else {
      axios.delete("http://vlasopoulos.ddns.net:8080/api/v1/user/watchlist/" + props.tconst);
    }
  };

  return (
    <button className='clickable' style={{ backgroundColor: watchlist ? 'rgb(241, 206, 165)' : 'rgb(159, 235, 219)', width:'170px'}} onClick={handleClick}>{watchlist? "Remove from watchlist" : "Add to watchlist"}</button>
  )
}

export default WatchlistButton