import React from 'react';
import Navbar from './components/Navbar';
import './App.css';
import Home from './components/pages/Home';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Background from './components/Background';
import Map from './components/pages/Map';
// import Services from './components/pages/Services';
// import Products from './components/pages/Products';
// import SignUp from './components/pages/SignUp';

function App() {
  return (
    <>
      <Router>
        <Navbar />
        {/* <Background /> */}
        <Routes>
          <Route path='/' exact element={<Home/>} />
          <Route path='/map' exact element={<Map/>} />
          {/* <Route path='/services' component={Home} />
          <Route path='/products' component={Home} />
          <Route path='/sign-up' component={Home} /> */}
        </Routes>
      </Router>
    </>
  );
}

export default App;