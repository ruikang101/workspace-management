import React from 'react';
import '../App.css';
import { Button } from './Button';
import './Background.css';
import video from '../public/videos/video-3.mp4';

function Background() {
  return (
    <div className='hero-container'>
      <video src={video} autoPlay loop muted />
      {/* <h1>ADVENTURE AWAITS</h1>
      <p>What are you waiting for?</p> */}
      {/* <div className='hero-btns'>
        <Button
          className='btns'
          buttonStyle='btn--outline'
          buttonSize='btn--large'
        >
          GET STARTED
        </Button>
        <Button
          className='btns'
          buttonStyle='btn--primary'
          buttonSize='btn--large'
          onClick={console.log('hey')}
        >
          WATCH TRAILER <i className='far fa-play-circle' />
        </Button>
      </div> */}
    </div>
  );
}

export default Background;
