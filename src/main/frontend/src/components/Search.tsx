import React from 'react'
import { useState } from "react";
import { useNavigate } from "react-router-dom";

type Props = {}

const Search = (props: Props) => {
  const [searchTerm, setSearchTerm] = useState<string>();
  const [searchCategory, setSearchCategory] = useState<string>("movie");
  const navigate = useNavigate();

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    let searchTerms = searchCategory + "/" + searchTerm?.trim().replace(/ +(?= )/g,'').replaceAll(' ','+');
    navigate("/search-results",{replace: true, state: searchTerms});
    console.log("event handled");
    
}

  return (
    <div className='searchform'>
        <form name='searchForm' onSubmit={handleSubmit}>
            <select name="searchCategory" onChange={(e) => setSearchCategory(e.target.value)}>
                <option value="movie">Movie</option>
                <option value="tv-series">TV Series</option>
                <option value="person">Person</option>
                <option value="short">Short</option>
                <option value="any">Any</option>
            </select>
            <input type='text' name='searchTerm' placeholder='Search' onChange={(e) => setSearchTerm(e.target.value)}/>
        </form>
    </div>
  )
}

export default Search