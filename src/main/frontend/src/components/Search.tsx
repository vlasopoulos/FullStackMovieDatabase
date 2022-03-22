import React from 'react'
import { useState } from "react";

type Props = {
  setPage:React.Dispatch<React.SetStateAction<string>>;
}

const Search = (props: Props) => {
  const [searchTerm, setSearchTerm] = useState<string>();
  const [searchCategory, setSearchCategory] = useState<string>("movie");


  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    let searchTerms = searchCategory + "/" + searchTerm?.trim().replace(/ +(?= )/g,'').replaceAll(' ','+');
    props.setPage("search-results" + searchTerms);
    console.log("event handled");
    
}

  return (
    <div className='searchform'>
        <form name='searchForm' onSubmit={handleSubmit}>
            <select name="searchCategory" onChange={(e) => setSearchCategory(e.target.value)}>
                <option value="movie">Movie</option>
                <option value="tv-series">TV Series</option>
                <option value="person/search">Person</option>
                <option value="short">Short</option>
                <option value="any">Any</option>
            </select>
            <input type='text' name='searchTerm' placeholder='Search' onChange={(e) => setSearchTerm(e.target.value)}/>
        </form>
    </div>
  )
}

export default Search