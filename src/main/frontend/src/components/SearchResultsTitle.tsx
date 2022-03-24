import React, { Component }from 'react'
import { TitleSearchRootObject } from '../Interfaces'
import Pagination from './Pagination';
import SingleResultTitle from './SingleResultTitle';

type Props = {
    data: TitleSearchRootObject;
    setPage: React.Dispatch<React.SetStateAction<string>>;
    page: string;
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
      <Pagination totalPages = {props.data.totalPages} currentPage = {props.data.number + 1} setPage = {props.setPage} page = {props.page}/>
    </div>
  )
}

export default SearchResultsTitle