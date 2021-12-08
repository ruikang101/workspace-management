import React, { Component } from 'react';
import './RealTimeFloor.css';
import pointInSvgPolygon from 'point-in-svg-polygon';

// test data

// empty, moderate, crowded, full
const roomList = [
    {
      id: 1,
      x: 0,
      y: 0,
      width: 200,
      height: 180,
      status: "empty",
      density: 0.0,
      type: "meetingroom",
      capacity: 50,
      floor: 1
    },
    {
        id: 2,
        x: 0,
        y: 270,
        width: 200,
        height: 180,
        status: "moderate",
        density: 0.35,
        type: "meetingroom",
        capacity: 20,
        floor: 1
    },
    {
        id: 3,
        x: 320,
        y: 0,
        width: 120,
        height: 100,
        status: "empty",
        density: 0.0,
        type: "meetingroom",
        capacity: 10,
        floor: 1
    },
    {
        id: 4,
        x: 320,
        y: 350,
        width: 120,
        height: 100,
        status: "moderate",
        density: 0.5,
        type: "meetingroom",
        capacity: 10,
        floor: 1
    },
    {
        id: 5,
        x: 440,
        y: 0,
        width: 120,
        height: 100,
        status: "crowded",
        density: 0.7,
        type: "meetingroom",
        capacity: 10,
        floor: 1
    },
    {
        id: 6,
        x: 440,
        y: 350,
        width: 120,
        height: 100,
        status: "full",
        density: 0.8,
        type: "meetingroom",
        capacity: 10,
        floor: 1
    },
    {
        id: 7,
        x: 200,
        y: 350,
        width: 60,
        height: 100,
        status: "full",
        density: 1.0,
        type: "bathroom",
        capacity: 4,
        floor: 1
    },
    {
        id: 8,
        x: 200,
        y: 0,
        width: 60,
        height: 100,
        status: "empty",
        density: 0.0,
        type: "bathroom",
        capacity: 4,
        floor: 1
    },
    {
        id: 9,
        x: 600,
        y: 0,
        width: 200,
        height: 450,
        status: "empty",
        density: 0.02,
        type: "diningroom",
        capacity: 100,
        floor: 1
    },
]

export default class RealTimeFloor extends Component {
    constructor() {
        super();
        this.state = {
            roomList: roomList,
            movements: [],
            initialCoordinates: []
        }
        this.convertRectangles = this.convertRectangles.bind(this);
        this.updateDensity = this.updateDensity.bind(this);
        this.uploadFile = this.uploadFile.bind(this);
        // this.setIntervalX = this.setIntervalX.bind(this);
        // this.interval = setInterval(this.updateDensity(), 1000);
    }


    // async componentDidMount() {
        
    // }

    startAnimation = async() => {
        const elements = document.getElementsByTagName("animateMotion");
        for (let i = 0; i < elements.length; i++) {
            elements[i].beginElement();
        }
        const sleep = (milliseconds) => {
            return new Promise(resolve => setTimeout(resolve, milliseconds))
        }
        await sleep(5000);
        this.updateDensity();
    }

    uploadFile = e => {
        const fileReader = new FileReader();
        fileReader.readAsText(e.target.files[0], "UTF-8");
        fileReader.onload = e => {
            console.log("e.target.result", e.target.result);
            this.setState({
                movements: JSON.parse(e.target.result).movements,
                initialCoordinates: JSON.parse(e.target.result).initialCoordinates
            })
        };
        
    }

    convertRectangles = (x, y, width, height) => {
        var x = parseFloat(x, 10);
        var y = parseFloat(y, 10);
        var width = parseFloat(width, 10);
        var height = parseFloat(height, 10);
    
        if (x < 0 || y < 0 || width < 0 || height < 0) {
            return '';
        }
    
        return 'M' + x + ',' + y + 'L' + (x + width) + ',' + y + ' ' + (x + width) + ',' + (y + height) + ' ' + x + ',' + (y + height) + 'z';
    }

    updateDensity = () => {
        const room = this.state.roomList[2];
        let count = room.capacity * room.density;
        let x = 0;
        let y = 0;
        let id = 1;
        this.state.movements.forEach((move) => {
            if (pointInSvgPolygon.isInside([move.sequence[move.sequence.length-1].x,move.sequence[move.sequence.length-1].y], this.convertRectangles(320,0,120,100))) count++;
        })
        room.density = count / room.capacity;
        room.status = room.density < 0.3 ? "empty" : room.density < 0.6 ? "moderate" : room.density < 0.8 ? "crowded" : "full";
        roomList[2].density = room.density;
        roomList[2].status = room.status;
        roomList[5].status = "crowded";
        roomList[5].density = 0.6;
        this.setState({
            roomList: roomList
        })
    }

    render() {

        const points = () => {
            let p = [];
            this.state.initialCoordinates.forEach(point => {
                p.push(
                    <circle id={"point"+point.id} cx={point.coordinates[0]} cy={point.coordinates[1]} r="5" fill="black" />
                )
            })
            return p;
        }

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

        const roomComponents = () => {
            let rooms = [];
            let color = "";
            let brightness = 0.0;
            this.state.roomList.forEach((room) => {
                color = room.status === "full" ? "red" : room.status === "crowded" ? "orange" : room.status === "moderate" ? "green" : "grey";
                brightness = room.status === "full" ? 1.8-room.density : room.status === "crowded" ? 1.6-room.density : room.status === "moderate" ? 1.3-room.density : 1-room.density;
                rooms.push(
                    <rect style={{x: room.x, y: room.y, width: room.width, height: room.height, fill: color, filter: "brightness("+brightness+")"}} className={room.type} />
                )
            })
            return rooms;
        }

        const movementComponents = () => {
            let m = [];
            this.state.movements.forEach(point => {
                let path = "M0,0";
                point.sequence.forEach(move => {
                    path += " " + (move.x-this.state.initialCoordinates[point.id-1].coordinates[0]) + "," + (move.y-this.state.initialCoordinates[point.id-1].coordinates[1]);
                })
                m.push(
                    <animateMotion
                        xlinkHref={"#point"+point.id}
                        dur={point.d}
                        path={path}
                        fill="freeze"
                        begin="indefinite"
                    />
                )
            })
            return m
        }

        

        // path="M20,225 230,225 230,125 340,125 340,40"
        // y-225, x-20

        // path="M0,0 210,0 210,-100 320,-100 320,-185"

        return (
            <div className="RealTimeFloor">
                <div className="fileupload">
                    Upload data file from RFID
                    <br/>
                    <input type="file" onChange={this.uploadFile} />
                    <br/>
                    <button onClick={this.startAnimation}>start</button>
                </div>
                <div className="canvas">
                    <svg className="floor" width="800" height="450">
                        {roomComponents()}
                        {cubicles()}
                        {points()}
                        {movementComponents()}
                        {/* {this.setIntervalX((this.updateDensity(), 8))} */}
                        <text x="0" y="20" fill="white" fontSize="20px">MeetingRoom 1</text>
                        <text x="0" y="290" fill="white" fontSize="20px">MeetingRoom 2</text>
                        <text x="320" y="15" fill="white" fontSize="12px">MeetingRoom 3</text>
                        <text x="320" y="365" fill="white" fontSize="12px">MeetingRoom 4</text>
                        <text x="440" y="15" fill="white" fontSize="12px">MeetingRoom 5</text>
                        <text x="440" y="365" fill="white" fontSize="12px">MeetingRoom 6</text>
                        <text x="600" y="30" fill="white" fontSize="30px">DiningRoom</text>
                        <image xlinkHref="wc.svg" x="200" y="0" height="30px" width="30px" fill="white"></image>
                        <image xlinkHref="wc.svg" x="200" y="350" height="30px" width="30px" fill="white"></image>
                    </svg>
                </div>
            </div>
        )
    }
}
