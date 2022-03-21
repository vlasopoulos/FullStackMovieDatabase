import React from 'react';
import {
  BrowserRouter,
  Routes,
  Route
} from "react-router-dom";
import logo from './logo.svg';
import './App.css';
import NavBar from './components/NavBar';
import Home from "./pages/Home"
import Watchlist from "./pages/Watchlist"
import Watched from "./pages/Watched"
import About from "./pages/About"
import Footer from './components/Footer';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <NavBar />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="watchlist" element={<Watchlist />} />
          <Route path="watched" element={<Watched />} />
          <Route path="about" element={<About />} />
        </Routes>
      </BrowserRouter>
      <Footer />
    </div>
  );
}

export default App;
