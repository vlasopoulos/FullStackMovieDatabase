import React, { Component } from 'react'
import { useState, useEffect } from "react";
import axios from "axios"

type Props = {
  searchTerms :string;
}



const SearchResults = (props: Props) => {
    const [loading, setLoading] = useState(true);
    const [data, setData] = useState<Object | null>(null);
    
    
    let apiURL: string;
    if(props.searchTerms.startsWith("person")){
        apiURL = "http://localhost:8080/api/v1/" ;
    } else {
        apiURL = "http://localhost:8080/api/v1/title/search/";
    }
    
    const searchURL: string = apiURL + props.searchTerms;

    console.log(searchURL);
    
    // useEffect(() => {
    //   axios.get(searchURL).then(res => {setData(res.data)});
    // }, [searchURL]);
    // console.log(data);

    useEffect(() => {
      const fetchData = async () =>{
        setLoading(true);
        try {
          const {data: response} = await axios.get(searchURL);
          setData(response);
          console.log("data set");
          
        } catch (error) {
          console.error("Error!");
        }
        setLoading(false);
      }
      fetchData();
    }, [searchURL]);
    console.log(data);
    
    if (loading) return <div className='page'>Loading...</div>

    return (
    <div className='page'>Search Results {data?.toString} </div>
    )
}

export default SearchResults