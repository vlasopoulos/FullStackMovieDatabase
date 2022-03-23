import { isContentEditable } from '@testing-library/user-event/dist/utils';
import React from 'react'
import { TitleSearchContent } from '../Interfaces'

type Props = {
    content: TitleSearchContent;
    setPage:React.Dispatch<React.SetStateAction<string>>;
}

const SingleResultTitle = (props: Props) => {
  return (
    <tr className='result'>
        <td className='clickable-link' onClick={(e)=>props.setPage("title" + props.content.tconst)}>{props.content.primaryTitle}</td>
        <td>{props.content.titleType}</td>
        <td>{(props.content.startYear === 0) ? "N/A" : props.content.startYear}</td>
        <td>{(props.content.endYear === 0) ? "N/A" : props.content.endYear}</td>
        <td>{(props.content.genres?.toString() === "N" ? "N/A" : props.content.genres?.toString())}</td>
        <td>{(props.content.averateRating === 0) ? "N/A" : props.content.averateRating}</td>
        <td>{(props.content.userRating)}</td>
        <td>{(props.content.watched) ? "YES" : "NO"}</td>
        <td>{(props.content.watchlist) ? "YES" : "NO"}</td>
    </tr>
  )
}

export default SingleResultTitle