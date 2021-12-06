import React, { Component } from 'react';
import './RealTimeFloor.css';

// test data

const initialCoordinates = [
    {x: 20, y: 225},
    {x: 297.5, y: 262.5},
    {x: 447.5, y: 187.5},
    {x: 480, y: 400},
    {x: 520, y: 400},
]

const movements = [
    {
        username: "testuser1",
        id: 1,
        d: "6s",
        sequence: [
            {
                x: 230,
                y: 225,
            },
            {
                x: 230,
                y: 125,
            },
            {
                x: 340,
                y: 125,
            },
            {
                x: 340,
                y: 30,
            },
        ]
    },
    {
        username: "testuser2",
        id:2,
        d: "4s",
        sequence: [
            {
                x: 230,
                y: 262.5,
            },
            {
                x: 230,
                y: 125,
            },
            {
                x: 380,
                y: 125,
            },
            {
                x: 380,
                y: 30,
            },
        ]
    },
    {
        username: "testuser3",
        id: 3,
        d: "2.5s",
        sequence: [
            {
                x: 447.5,
                y: 125,
            },
            {
                x: 420,
                y: 125,
            },
            {
                x: 420,
                y: 30,
            },
        ]
    },
    {
        username: "testuser4",
        id: 4,
        d: "8s",
        sequence: [
            {
                x: 580,
                y: 400,
            },
            {
                x: 580,
                y: 125,
            },
            {
                x: 350,
                y: 125,
            },
            {
                x: 350,
                y: 70,
            },
        ]
    },
    {
        username: "testuser5",
        id: 5,
        d: "8s",
        sequence: [
            {
                x: 580,
                y: 400,
            },
            {
                x: 580,
                y: 125,
            },
            {
                x: 410,
                y: 125,
            },
            {
                x: 410,
                y: 70,
            },
        ]
    },
]

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
        density: 0.32,
        type: "meetingroom",
        capacity: 50,
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
        density: 0.6,
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
        capacity: 6,
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
        capacity: 6,
        floor: 1
    },
    {
        id: 9,
        x: 600,
        y: 0,
        width: 200,
        height: 450,
        status: "empty",
        density: 0.15,
        type: "diningroom",
        capacity: 100,
        floor: 1
    },
]

export default class RealTimeFloor extends Component {
    constructor() {
        super();
        this.state = {
            roomList: [],
        }
    }

    async componentDidMount() {
        // get roomList
    }

    render() {
        const points = () => {
            let p = [];
            let i = 1;
            initialCoordinates.forEach(point => {
                p.push(
                    <circle id={i} cx={point.x} cy={point.y} r="5" fill="black" />
                )
                i++;
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
            console.log(roomList);
            let rooms = [];
            let color = "";
            let brightness = 0.0;
            roomList.forEach((room) => {
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
            movements.forEach(point => {
                let path = "M0,0";
                point.sequence.forEach(move => {
                    path += " " + (move.x-initialCoordinates[point.id-1].x) + "," + (move.y-initialCoordinates[point.id-1].y)
                })
                m.push(
                    <animateMotion
                        xlinkHref={"#"+point.id}
                        dur={point.d}
                        path={path}
                        fill="freeze"
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
                <div className="canvas">
                    <svg className="floor" width="800" height="450">
                        {roomComponents()}
                        {cubicles()}
                        {points()}
                        {movementComponents()}
                        {/* <animateMotion
                            xlinkHref="#1"
                            dur="2s"
                            path="M0,0 210,0 210,-100 320,-100 320,-185"
                            fill="freeze"
                        /> */}
                        
                        {/* <animate
                            xlinkHref="#1"
                            attributeName="cx"
                            to="230"
                            dur="1s"
                            fill="freeze" />
                        <animate
                            xlinkHref="#2"
                            attributeName="cx"
                            to="230"
                            dur="0.5s"
                            fill="freeze"
                        />
                        <animate
                            xlinkHref="#2"
                            attributeName="cy"
                            to="125"
                            dur="1s"
                            fill="freeze"
                        /> */}
                    </svg>
                </div>
            </div>
        )
    }
}
