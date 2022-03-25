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
import Title from './pages/Title';
import Person from './pages/Person';

function App() {
  const [page, setPage] = useState<string>("home");

  let currentPage :ReactElement = <Home />;
  if ( page == "home") {
    currentPage = <Home />
  } else if ( page == "watchlist") {
    currentPage = <Watchlist setPage = {setPage} page={page}/>
  } else if ( page == "watched") {
    currentPage = <Watched setPage = {setPage} page={page}/>
  } else if (page == "about") {
    currentPage = <About />
  } else if (page.startsWith("search-results")) {
    currentPage = <SearchResults searchTerms = {page.substring(14)} setPage = {setPage} page={page}/>
  } else if (page.startsWith("title")) {
    currentPage = <Title tconst = {page.substring(5)} setPage = {setPage}/>
  }  else if (page.startsWith("person")) {
    currentPage = <Person nconst = {page.substring(6)} setPage = {setPage}/>
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