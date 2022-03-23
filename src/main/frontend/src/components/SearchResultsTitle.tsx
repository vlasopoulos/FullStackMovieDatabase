import React, { Component }from 'react'
import { TitleSearchRootObject } from '../Interfaces'
import SingleResultTitle from './SingleResultTitle';

type Props = {
    data: TitleSearchRootObject;
    setPage:React.Dispatch<React.SetStateAction<string>>;
}

const SearchResultsTitle = (props: Props) => {

  return (
    <div>
      {(props.data.empty) ? <div className='no-results'><p>No results found.</p></div> : 
      <table>
        <tr>
          <th>Title</th>
          <th>Type</th>
          <th>Year</th>
          <th>End<br/>Year</th>
          <th>Genres</th>
          <th>Average<br/>Rating</th>
          <th>Your<br/>Rating</th>
          <th>Watched</th>
          <th>Watchlist</th>
        </tr>
        {props.data.content.map((content, _index) => {
        return (
          <SingleResultTitle content = {content} setPage = {props.setPage}/>
        );
        })}
      </table>
      }
    </div>
  )
}

export default SearchResultsTitle