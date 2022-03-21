import React from 'react'
import { Link } from "react-router-dom";

type Props = {}

const NavBar = (props: Props) => {
  return (
      <div className='navbar'>
        <nav>
          <ul>
            <li><Link to="/">Home</Link></li>
            <li><Link to="watchlist">Watchlist</Link></li>
            <li><Link to="watched">Watched</Link></li>
            <li><Link to="about">About</Link></li>
          </ul>
        </nav>
      </div>
  )
}

export default NavBar;