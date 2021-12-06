import React from 'react';
import '../App.css';
import { Button } from './Button';
import './Background.css';
import video from '../public/videos/video-3.mp4';

const style = {
  
}

function Background() {
  return (
    <div className='background'>
      <video src={video} autoPlay loop muted />
    </div>
  );
}

export default Background;
