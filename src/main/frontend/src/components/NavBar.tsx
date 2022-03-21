import React from 'react'
import {
  BrowserRouter,
  Routes,
  Route,
  Link,
} from "react-router-dom";
type Props = {}

const NavBar = (props: Props) => {
  return (
    <BrowserRouter>
      <div className='navbar'>
        <nav>
          <ul>
            <li><Link to="/">Home</Link></li>
            <li><Link to="watchlist">Watchlist</Link></li>
            <li><Link to="watched">Watched</Link></li>
            <li><Link to="about">About</Link></li>
          </ul>
        </nav>
        <Routes>
        <Route path="/" element={<h1>Home</h1>} />
        <Route path="watchlist" element={<h1>Watchlist</h1>} />
        <Route path="watched" element={<h1>Watched</h1>} />
        <Route path="about" element={<h1>About</h1>} />
      </Routes>
      </div>
    </BrowserRouter>
  )
}

export default NavBar;