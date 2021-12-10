import React, { useState, useEffect } from 'react';
import { Button } from './Button';
import { Link } from 'react-router-dom';
import './Navbar.css';

function Navbar() {
  const [click, setClick] = useState(false);
  const [button, setButton] = useState(true);

  const handleClick = () => setClick(!click);
  const closeMobileMenu = () => setClick(false);

  const showButton = () => {
    if (window.innerWidth <= 960) {
      setButton(false);
    } else {
      setButton(true);
    }
  };

  useEffect(() => {
    showButton();
  }, []);

  window.addEventListener('resize', showButton);

  return (
    <>
      <nav className='navbar'>
        <div className='navbar-container'>
          <Link to='/' className='navbar-logo' onClick={closeMobileMenu}>
            Workspace Management
          </Link>
          <div className='menu-icon' onClick={handleClick}>
            <i className={click ? 'fas fa-times' : 'fas fa-bars'} />
          </div>
          <ul className={click ? 'nav-menu active' : 'nav-menu'}>
            <li className='nav-item'>
              <Link to='/' className='nav-links' onClick={closeMobileMenu}>
                Home
              </Link>
            </li>
            <li className='dropdown'>
              <button className='dropbtn'>Services</button>
              <div class="dropdown-content">
                <Link to="/services/findaperson">Find a person</Link>
                <Link to="/services/findaroom">Find a room</Link>
              </div>
            </li>
            <li className='nav-item'>
              <Link
                to='/map'
                className='nav-links'
                onClick={closeMobileMenu}
              >
                Map
              </Link>
            </li>
            <li className='nav-item'>
              <Link
                to='/demo'
                className='nav-links'
                onClick={closeMobileMenu}
              >
                Demo
              </Link>
            </li>
            <li className='nav-item'>
              <Link
                to='/personalcenter'
                className='nav-links'
                onClick={closeMobileMenu}
              >
                <i className="fas fa-user"></i>
              </Link>
            </li>
            
          </ul>
          {/* {button && <Button buttonStyle='btn--outline'>SIGN UP</Button>} */}
        </div>
      </nav>
    </>
  );
}

export default Navbar;
