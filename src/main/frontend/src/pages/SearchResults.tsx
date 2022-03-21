import React, { Component } from 'react'
import { useState, useEffect } from "react";
import { useLocation } from 'react-router-dom';
import axios from "axios"

type Props = {}



const SearchResults = (props: Props) => {
    const [loading, setLoading] = useState(true);
    const [data, setData] = useState<Object | null>();
    
    const location = useLocation();
    let state = location.state as string;
    let searchTerm: string = state;
    let searchURL: string;
    let apiURL: string;
    
    
    if(searchTerm.startsWith("person")){
        apiURL = "http://localhost:8080/api/v1/person/search/" ;
    } else {
        apiURL = "http://localhost:8080/api/v1/title/search/";
    }
    
    searchURL = apiURL + searchTerm;

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
    
    if (loading) return <div>Loading...</div>

    return (
    <div className='page'>Search Results {data?.toString} </div>
    )
}

export default SearchResults