import React, { Component, ReactElement } from 'react'
import { useState, useEffect } from "react";
import axios from "axios"
import SearchResultsTitle from '../components/SearchResultsTitle';
import SearchResultsPerson from '../components/SearchResultsPerson';

type Props = {
  searchTerms :string;
  setPage:React.Dispatch<React.SetStateAction<string>>;
}

const SearchResults = (props: Props) => {
    const [loading, setLoading] = useState(true);
    const [data, setData] = useState<any | null>(null);
    
    
    let apiURL: string;
    let searchResultsList :ReactElement = <SearchResultsTitle data = {data} setPage = {props.setPage}/>;
    if(props.searchTerms.startsWith("person")){
        apiURL = "http://localhost:8080/api/v1/" ;
        searchResultsList = <SearchResultsPerson data = {data} setPage = {props.setPage}/>
    } else {
        apiURL = "http://localhost:8080/api/v1/title/search/";
    }
    
    const searchURL: string = apiURL + props.searchTerms;

    // console.log(searchURL);
    
    useEffect(() => {
      const fetchData = async () =>{
        setLoading(true);
        try {
          const response = await axios.get(searchURL);
          setData(response.data);
        } catch (error) {
          console.error("Error!");
        }
        setLoading(false);
      }
      fetchData();
    }, [searchURL]);


    // console.log(data);
    
    
    if (loading) return <div className='no-results'><p>Loading...</p></div>

    return (
    <div className='page'> 
      {searchResultsList}
    </div>
    )
}

export default SearchResults