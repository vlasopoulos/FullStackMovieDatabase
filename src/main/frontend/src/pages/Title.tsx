import React from 'react'

type Props = {
    tconst: string;
    setPage:React.Dispatch<React.SetStateAction<string>>;
}

const Title = (props: Props) => {
  return (
    <div className='page'>Title {props.tconst}</div>
  )
}

export default Title