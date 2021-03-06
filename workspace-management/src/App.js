import React, {useState} from 'react';
import Navbar from './components/Navbar';
import './App.css';
import Home from './components/pages/Home';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Map from './components/pages/Map';
import Users from './components/pages/Users';
import Rooms from './components/pages/Rooms';
import PersonalCenter from './components/pages/PersonalCenter';
import Demo from './components/pages/Demo';
import { GlobalProvider } from './components/Context/GlobalState';
import Meetings from './components/pages/Meetings';

// check login status!!!
function App() {

    return (
      <GlobalProvider>
        <Router>
          <Navbar />
          {/* <Background /> */}
          <Routes>
            <Route path='/' exact element={<Home/>} />
            <Route path='/map' exact element={<Map/>} />
            <Route path='/services/findaperson' element={<Users/>} />
            <Route path='/services/findaroom' element={<Rooms/>} />
            <Route path='/services/meetings' element={<Meetings/>} />
            <Route path='/personalcenter' element={<PersonalCenter/>} />
            <Route path='/demo' element={<Demo/>} />
            {/* <Route path='/products' component={Home} />
            <Route path='/sign-up' component={Home} /> */}
          </Routes>
        </Router>
      </GlobalProvider>
    )
}

export default App;