import React, {Component} from 'react';
import { Radio, Row, Col } from 'antd';
import './FloorPlan.css';
import pointInSvgPolygon from 'point-in-svg-polygon';

// test data
const rooms = [
  {
    id: 1,
    x: 0,
    y: 0,
    width: 200,
    height: 180,
    status: "",
    density: 0.15
  },

]

class FloorPlan extends Component {

  constructor(props) {
    super(props);
    this.state = {
        floor: 1,
        rooms: [],
        points: [], // all users location
    };
    this.onChange = this.onChange.bind(this);
  }

  componentDidMount() {
    // get room status & density
  }

  onChange(e) {
    console.log('radio checked', e.target.value);
    this.setState({
      floor: e.target.value
    });
    console.log(this.state.floor);
  }

  render() {
    const SelectFloor = () => {
      return (
        <div className="radios">
          <Radio.Group onChange={this.onChange} value={this.state.floor} size="large">
            <Radio value={1} className="radio"> Floor 1</Radio>
            <Radio value={2} className="radio"> Floor 2</Radio>
            <Radio value={3} className="radio"> Floor 3</Radio>
          </Radio.Group>
        </div>
      )
    }

    const convertRectangles = (x, y, width, height) => {
      var x = parseFloat(x, 10);
      var y = parseFloat(y, 10);
      var width = parseFloat(width, 10);
      var height = parseFloat(height, 10);
  
      if (x < 0 || y < 0 || width < 0 || height < 0) {
          return '';
      }
  
      return 'M' + x + ',' + y + 'L' + (x + width) + ',' + y + ' ' + (x + width) + ',' + (y + height) + ' ' + x + ',' + (y + height) + 'z';
    }

    // bathroom 2/floor
    // small meeting room
    // middle meeting room
    // large meeting room
    // gym 1st floor
    // dinning room 2nd floor
    // Lounge 1/floor

    const color = "#3e7086"
    const density = 1.00
    // x="0" y="0" width="200" height="180"
    const a=0;
    const b=0;
    const c=200;
    const d=180

    const cubicles = () => {
      let c = [];
      for (let i=0; i<4; i++) {
        for (let j=0; j<2; j++) {
          c.push(<rect x={75*i + 260} y={75*j + 150} width="75" height="75" className="cubicle"/>)
          c.push(<image xlinkHref="cubicle.svg" x={75*i + 260} y={75*j + 150} height="30px" width="30px" fill="white"></image>)
        }
      }
      return c
    }

    const Floor1 = () => {
      return (
        <svg className="floor" width="800" height="450">
          <rect style={{x: a, y: b, width: c, height: d, fill: color, filter: "brightness("+density+")"}} className="meetingroom" type="large" />
          <text x="0" y="20" fill="white" fontSize="20px">MeetingRoom 1</text>
          <rect x="0" y="270" width="200" height="180" className="meetingroom" type="large" />
          <text x="0" y="290" fill="white" fontSize="20px">MeetingRoom 2</text>
          <rect x="200" y="0" width="60" height="100" className="bathroom" type="large" />
          <image xlinkHref="wc.svg" x="200" y="0" height="30px" width="30px" fill="white"></image>
          <rect x="200" y="350" width="60" height="100" className="bathroom" type="large" />
          <image xlinkHref="wc.svg" x="200" y="350" height="30px" width="30px" fill="white"></image>
          <rect x="320" y="0" width="120" height="100" className="meetingroom" type="small" />
          <text x="320" y="15" fill="white" fontSize="12px">MeetingRoom 3</text>
          <rect x="320" y="350" width="120" height="100" className="meetingroom" type="small" />
          <text x="320" y="365" fill="white" fontSize="12px">MeetingRoom 4</text>
          <rect x="440" y="0" width="120" height="100" className="meetingroom" type="small" />
          <text x="440" y="15" fill="white" fontSize="12px">MeetingRoom 5</text>
          <rect x="440" y="350" width="120" height="100" className="meetingroom" type="small" />
          <text x="440" y="365" fill="white" fontSize="12px">MeetingRoom 6</text>
          <rect x="600" y="0" width="200" height="450" className="diningroom"/>
          <text x="600" y="30" fill="white" fontSize="30px">DiningRoom</text>
          {cubicles()}
          <line x1="260" y1="225" x2="560" y2="225" className="line" />
          <line x1="335" y1="150" x2="335" y2="300" className="line" />
          <line x1="410" y1="150" x2="410" y2="300" className="line" />
          <line x1="485" y1="150" x2="485" y2="300" className="line" />
          <circle id="circle" cx="50" cy="50" r="6" fill="black" />
          <animate
            xlinkHref="#circle"
            attributeName="cx"
            to="200"
            dur="1s"
            fill="freeze" />
          <animate
            xlinkHref="#circle"
            attributeName="cy"
            to="200"
            dur="1s"
            fill="freeze"
          />

        </svg>
      )
    }

    const Floor2 = () => {
      return (
        <svg className="floor" width="800" height="450">
          <rect x="0" y="0" width="200" height="180" className="meetingroom" type="large" />
          <rect x="0" y="270" width="200" height="180" className="meetingroom" type="large" />
          {/* <rect x="200" y="0" width="80" height="100" className="bathroom" type="large" />
          <rect x="200" y="350" width="80" height="100" className="bathroom" type="large" />
          <rect x="360" y="0" width="100" height="100" className="meetingroom" type="small" />
          <rect x="360" y="350" width="100" height="100" className="meetingroom" type="small" />
          <rect x="460" y="0" width="100" height="100" className="meetingroom" type="small" />
          <rect x="460" y="350" width="100" height="100" className="meetingroom" type="small" />
          <rect x="600" y="0" width="200" height="450" className="diningroom"/>
          <rect x="260" y="150" width="300" height="150" className="cubicle"/>
          <line x1="260" y1="225" x2="560" y2="225" className="line" />
          <line x1="335" y1="150" x2="335" y2="300" className="line" />
          <line x1="410" y1="150" x2="410" y2="300" className="line" />
          <line x1="485" y1="150" x2="485" y2="300" className="line" /> */}
        </svg>
      )
    }

    const Floor3 = () => {
      return (
        <svg className="floor" width="800" height="450">
          {/* <rect x="0" y="0" width="200" height="180" className="meetingroom" type="large" />
          <rect x="0" y="270" width="200" height="180" className="meetingroom" type="large" />
          <rect x="200" y="0" width="80" height="100" className="bathroom" type="large" />
          <rect x="200" y="350" width="80" height="100" className="bathroom" type="large" />
          <rect x="360" y="0" width="100" height="100" className="meetingroom" type="small" />
          <rect x="360" y="350" width="100" height="100" className="meetingroom" type="small" />
          <rect x="460" y="0" width="100" height="100" className="meetingroom" type="small" />
          <rect x="460" y="350" width="100" height="100" className="meetingroom" type="small" />
          <rect x="600" y="0" width="200" height="450" className="diningroom"/>
          <rect x="260" y="150" width="300" height="150" className="cubicle"/> */}
          <line x1="260" y1="225" x2="560" y2="225" className="line" />
          <line x1="335" y1="150" x2="335" y2="300" className="line" />
          <line x1="410" y1="150" x2="410" y2="300" className="line" />
          <line x1="485" y1="150" x2="485" y2="300" className="line" />
        </svg>
      )
    }


    return (
      <div className="FloorPlan">
        {SelectFloor()}
        <div className="canvas">
          {this.state.floor === 1 && <Floor1 />}
          {this.state.floor === 2 && <Floor2 />}
          {this.state.floor === 3 && <Floor3 />}
        </div>
      </div>
    )
  } 
}

export default FloorPlan;


