import React, { Component } from 'react';
// import RoomService from './services/RoomService';
import './RoomList.css';
import roomList1 from '../public/Room_data';
import RoomContext from './RoomContext';

export default class RoomList extends Component {
    constructor() {
        super();
        this.state = {
            rooms: []
        }
        this.updateRoom = this.updateRoom.bind();
    }

    async componentDidMount() {
        // !!!get data
        // let roomList = await roomService.fetchroomList();
        // this.setState({
        //     rooms: roomList
        // })
        // if (this.state.rooms.length === 0) {
        //     this.setState({
        //         rooms: roomList
        //     })
        // }
    }

    updateRoom(roomsToUpdate) {
        roomsToUpdate.forEach(room => {
            roomList1[room.id-1].status = room.status;
            roomList1[room.id-1].density = room.density;
        });
    }

    render() {
        
        return(
            <RoomContext.Consumer>
                {({ roomsToUpdate, update }) => {
                    this.updateRoom(roomsToUpdate);
                    return (
                        <div className="roomlist">
                            <table>
                                <tr className= "column">
                                    <th>ID</th>
                                    <th>Status</th>
                                    <th>Density</th>
                                    <th>Capacity</th>
                                    <th>Type</th>
                                </tr>
                                {roomList1.map(room => 
                                    <tr>
                                        <td>{room.id}</td>
                                        <td>{room.status}</td>
                                        <td>{room.density}</td>
                                        <td>{room.capacity}</td>
                                        <td>{room.type}</td>
                                    </tr>
                                )}
                            </table>
                        </div>
                    )
                }}
            </RoomContext.Consumer>
        )
    }
}
