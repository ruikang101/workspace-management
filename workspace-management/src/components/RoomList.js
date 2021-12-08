import React, { Component } from 'react';
// import RoomService from './services/RoomService';
import './RoomList.css';

export default class RoomList extends Component {
    constructor() {
        super();
        this.state = {
            rooms: []
        }
    }

    async componentDidMount() {
        // get data
        // let roomList = await roomService.fetchroomList();
        // this.setState({
        //     rooms: roomList
        // })
        if (this.state.rooms.length === 0) {
            this.setState({
                rooms: [
                    {
                        id: 1,
                        roomname: "testroom1",
                        department: "IT",
                        position: "meetingroom1"
                    },
                    {
                        id: 2,
                        roomname: "testroom2",
                        department: "IT",
                        position: "not in any room"
                    },
                    {
                        id: 3,
                        roomname: "testroom3",
                        department: "Cloud",
                        position: "meetingroom2"
                    },
                    {
                        id: 4,
                        roomname: "testroom4",
                        department: "IT",
                        position: "diningroom"
                    },
                ]
            })
        }
    }

    render() {
        
        return(
            <div className="roomlist">
                <table>
                    <tr className= "column">
                        <th>ID</th>
                        <th>roomname</th>
                        <th>Department</th>
                        <th>Position</th>
                    </tr>
                    {this.state.rooms.map(room => 
                        <tr>
                            <td>{room.id}</td>
                            <td>{room.roomname}</td>
                            <td>{room.department}</td>
                            <td>{room.position}</td>
                        </tr>
                    )}
                </table>
            </div>
        )
    }
}
