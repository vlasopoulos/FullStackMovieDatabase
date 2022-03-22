import React, { Component }from 'react'
import { RootObject } from '../SearchResultsTitleInterface'
import SingleResultTitle from './SingleResultTitle';

type Props = {
    data: RootObject;
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
          <th>End Year</th>
          <th>Genres</th>
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