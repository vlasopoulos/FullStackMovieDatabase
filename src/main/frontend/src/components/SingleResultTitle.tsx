import { isContentEditable } from '@testing-library/user-event/dist/utils';
import React from 'react'
import { TitleSearchContent } from '../Interfaces'
import UserRating from './UserRating';
import WatchedButton from './WatchedButton';
import WatchlistButton from './WatchlistButton';

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
        <td>{(props.content.genres?.toString() === "N" ? "N/A" : props.content.genres?.toString().replaceAll(",",", "))}</td>
        <td>{(props.content.averageRating === 0) ? "N/A" : props.content.averageRating}</td>
        <td><UserRating tconst = {props.content.tconst} userRating = {props.content.userRating}/></td>
        <td className='state-button'><WatchedButton tconst = {props.content.tconst} watched = {props.content.watched}/></td>
        <td className='state-button'><WatchlistButton tconst = {props.content.tconst} watchlist = {props.content.watchlist}/></td>
    </tr>
  )
}

export default SingleResultTitle