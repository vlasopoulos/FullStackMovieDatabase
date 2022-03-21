import React from 'react'
import { Link } from "react-router-dom";
import Search from "./Search"
import '../App.css';

type Props = {}

const NavBar = (props: Props) => {
  return (
    <div className='navbar'>
      <Link to="/"><img src="logo.png" alt="Logo" /></Link>
      <Search />
      <nav>
        <ul>
          <li><Link to="/">Home</Link></li>
          <li><Link to="/watchlist">Watchlist</Link></li>
          <li><Link to="/watched">Watched</Link></li>
          <li><Link to="/about">About</Link></li>
        </ul>
      </nav>
    </div>
  )
}

export default NavBar;