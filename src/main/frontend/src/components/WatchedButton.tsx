import axios from 'axios'
import React, { useState } from 'react'

type Props = {
  tconst: string
  watched: boolean
}

const WatchedButton = (props: Props) => {
const [watched,setWatched] = useState<boolean>(props.watched)

  const handleClick = () => {
    setWatched(!watched);
    
    if (!watched) {
      const body = { tconst : props.tconst };
      axios.post("http://localhost:8080/api/v1/user/watched",body);
    } else {
      axios.delete("http://localhost:8080/api/v1/user/watched/" + props.tconst);
    }
  };

  return (
    <button style={{ backgroundColor: watched ? 'rgb(159, 235, 159)' : 'rgb(241, 165, 165)' , width:'100px'}} onClick={handleClick}>{watched? "Watched" : "Not Watched"}</button>
  )
}

export default WatchedButton