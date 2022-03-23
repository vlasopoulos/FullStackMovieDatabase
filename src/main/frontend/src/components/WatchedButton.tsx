import React, { useState } from 'react'

type Props = {
  tconst: string
  watched: boolean
}

const WatchedButton = (props: Props) => {
  const [watched,setWatched] = useState<boolean>(props.watched)

  const handleClick = () => {
    setWatched(!watched);
  };

  return (
    <button style={{ backgroundColor: watched ? 'rgb(159, 235, 159)' : 'rgb(241, 165, 165)' , width:'100px'}} onClick={handleClick}>{watched? "Watched" : "Not Watched"}</button>
  )
}

export default WatchedButton