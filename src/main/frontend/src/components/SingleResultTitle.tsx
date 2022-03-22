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
        <td className='clickable' onClick={(e)=>props.setPage("title" + props.content.tconst)}>{props.content.primaryTitle}</td>
        <td>{props.content.titleType}</td>
        <td>{props.content.startYear}</td>
        <td>{props.content.endYear}</td>
        <td>{props.content.genres?.map((genre, index) => {
        return (
            <span>{genre} </span>
        );
        })}</td>
    </tr>
  )
}

export default SingleResultTitle