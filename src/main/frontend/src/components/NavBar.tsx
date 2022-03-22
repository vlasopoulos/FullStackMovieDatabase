import React from 'react'
import { Link } from "react-router-dom";
import Search from "./Search"
import '../App.css';

type Props = {
  setPage:React.Dispatch<React.SetStateAction<string>>;
}

const NavBar = (props: Props) => {
  return (
    <div className='navbar'>
      <img src="logo.png" alt="Logo" onClick={(e)=>props.setPage("home")}/>
      <Search setPage = {props.setPage}/>
      <nav>
        <ul>
          <li onClick={(e)=>props.setPage("home")}>Home</li>
          <li onClick={(e)=>props.setPage("watchlist")}>Watchlist</li>
          <li onClick={(e)=>props.setPage("watched")}>Watched</li>
          <li onClick={(e)=>props.setPage("about")}>About</li>
        </ul>
      </nav>
    </div>
  )
}

export default NavBar;