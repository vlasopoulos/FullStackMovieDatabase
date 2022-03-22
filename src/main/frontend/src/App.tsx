import React, { ReactElement, useState } from 'react';
import logo from './logo.svg';
import './App.css';
import NavBar from './components/NavBar';
import Home from "./pages/Home"
import Watchlist from "./pages/Watchlist"
import Watched from "./pages/Watched"
import About from "./pages/About"
import Footer from './components/Footer';
import SearchResults from './pages/SearchResults';



function App() {
  const [page, setPage] = useState<string>("home");

  let currentPage :ReactElement = <Home />;
  if ( page == "home") {
        currentPage = <Home />
      } else if ( page == "watchlist") {
        currentPage = <Watchlist />
      } else if ( page == "watched") {
        currentPage = <Watched />
      } else if (page == "about") {
        currentPage = <About />
      } else if (page.startsWith("search-results")) {
        currentPage = <SearchResults searchTerms = {page.substring(14)}/>
      }

  return (
    <div className="App">
      
      <NavBar setPage = {setPage} />
      {currentPage}
      <Footer />
    </div>
  );
}

export default App;