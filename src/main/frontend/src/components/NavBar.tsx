import React from 'react'
import Search from "./Search"
import '../App.css';

type Props = {
  setPage:React.Dispatch<React.SetStateAction<string>>;
}

const NavBar = (props: Props) => {
  return (
    <div className='navbar'>
      <img src="logo.png" alt="Logo" className='clickable' onClick={(e)=>props.setPage("home")}/>
      <Search setPage = {props.setPage}/>
      <nav>
        <ul>
          <li className='clickable' onClick={(e)=>props.setPage("home")}>Home</li>
          <li className='clickable' onClick={(e)=>props.setPage("watchlist")}>Watchlist</li>
          <li className='clickable' onClick={(e)=>props.setPage("watched")}>Watched</li>
          <li className='clickable' onClick={(e)=>props.setPage("about")}>About</li>
        </ul>
      </nav>
    </div>
  )
}

export default NavBar;