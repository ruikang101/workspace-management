import React, { Component, useContext, useState, useEffect } from 'react';
import UserService from './services/UserService';
import RoomService from './services/RoomService';
import MeetingService from './services/MeetingService';
import './UserList.css';
import userList1 from '../public/User_data';
import roomList1 from '../public/Room_data';
import { GlobalContext } from './Context/GlobalState';
import { DataGrid, GridToolbar } from '@mui/x-data-grid';
import Button from "@material-ui/core/Button";
import {Button as B} from 'antd';
import './MeetingList.css';

import axios from 'axios';
const server = require("../config/server");


// !!!data sample
// const meetings = [
//     {id: 1, startTime: "2021-12-10 09:15", endTime: "2021-12-10 10:15", roomId: 1, hostId:1, guests: [2,3,4,5]},
//     {id: 2, startTime: "2021-12-10 11:15", endTime: "2021-12-10 11:45", roomId: 1, hostId:12, guests: [2,3,4,5]},
//     {id: 3, startTime: "2021-12-10 10:00", endTime: "2021-12-10 10:30", roomId: 3, hostId:13, guests: [2,3,4,5]},
//     {id: 4, startTime: "2021-12-10 14:00", endTime: "2021-12-10 17:15", roomId: 4, hostId:15, guests: [2,3,4,5]},
//     {id: 5, startTime: "2021-12-10 13:30", endTime: "2021-12-10 14:30", roomId: 6, hostId:1, guests: [2,3,4,5]},
//     {id: 6, startTime: "2021-12-10 16:15", endTime: "2021-12-10 16:50", roomId: 3, hostId:14, guests: [2,3,4,5]},
// ]

const MeetingList = () => {

    // uncomment to test frontend only
    // const {userList, updateUserList} = useContext(GlobalContext);
    // const {roomList, updateRoomList} = useContext(GlobalContext);
    // if (roomList.length === 0) {
    //     updateRoomList(roomList1);
    // }

    // if (userList.length === 0) {
    //     updateUserList(userList1);
    // }

    // const meetingList = meetings.map(meeting => ({
        // id: meeting.id,
        // startTime: meeting.startTime,
        // endTime: meeting.endTime,
        // roomId: "Meetingroom " + meeting.roomId
    // }))

    const [meetingList, setMeetingList] = useState([]);

    const [isFormVisible, setIsvisible] = useState(false);
    const [startTime, setStart] = useState("");
    const [endTime, setEnd] = useState("");
    const [roomId, setRoom] = useState(0);
    const [hostId, setHost] = useState(0);

    useEffect(async() => {
        let users = await axios.get(server.api + "users/")
            .then(res => res.data);
        let meetings = await axios.get(server.api + "meetings/")
            .then(res => res.data);
        let meetingList = meetings.map(meeting => ({
            id: meeting.id,
            startTime: meeting.startTime,
            endTime: meeting.endTime,
            room: "Meetingroom " + meeting.roomId,
            host: users.filter(user => user.id == meeting.hostId)[0].username
        }))
        setMeetingList(meetingList);
    })

    const columns = [
        { field: 'id', headerName: 'ID', width: 180 },
        { field: 'startTime', headerName: 'Start Time', width: 280 },
        { field: 'endTime', headerName: 'End Time', width: 280 },
        { field: 'room', headerName: 'Room', width: 250 },
        { field: 'host', headerName: 'Host', width: 200 },
        { 
            field: '',
            headerName: 'Action',
            sortable: false,
            filterable: false,
            width: 200,
            renderCell: (params) => (
              <Button
                onClick={async() => {
                //   let clonedMeetingList = JSON.parse(JSON.stringify(meetingList));
                //   console.log(params.row);
                //   const id = params.row.id
                //   clonedMeetingList.splice(id-1, 1);
                //   updateMeetingList(clonedMeetingList);
                  
                  await MeetingService.deleteMeeting(params.row.id);
                }}
              >
                Delete
              </Button>
            ) 
        }
    ]

    const handleSubmit = async(e) => {
        e.preventDefault();
        // let clonedMeetings = JSON.parse(JSON.stringify(meetings));
        // console.log(clonedMeetings);
        // clonedMeetings.push(
        //     {
        //         id: clonedMeetings[clonedMeetings.length-1].id + 1,
        //         username: username,
        //         password: password,
        //         name: name,
        //         department: department,
        //         roomId: 0
        //     }
        // )
        // setU(clonedMeetings);
        // meetingList.push({
            // id: meetingList[meetingList.length-1].id+1,
            // start: start,
            // end: end,
            // room: room
        // })
        setIsvisible(false);
        const newMeeting = {
            id: meetingList[meetingList.length-1].id+1,
            startTime: startTime,
            endTime: endTime,
            roomId: roomId,
            hostId: hostId,
            guests: [2,3,4,6]
        };
        MeetingService.createMeeting(newMeeting);
    }
        
    return(
        <div className="userlist" style={{ height: 630, width: '100%' }}>
            <h1 style={{marginLeft: "10px"}}>MeetingList</h1>
            <B style={{marginLeft: "10px"}} type="primary" size="large" onClick={()=>setIsvisible(!isFormVisible)}>Create a meeting</B>
            <br/>
            <br/>
            {isFormVisible 
            ? 
            <form className="createmeetingform" onSubmit={handleSubmit}>
                <div className="textbox">
                    <input type="text" placeholder="Start Time" name="startTime" onChange={(e) => {setStart(e.target.value)}} />
                </div>
                <div className="textbox">
                    <input type="text" placeholder="End Time" name="endTime" onChange={(e) => {setEnd(e.target.value)}} />
                </div>
                <div className="textbox">
                    <input type="text" placeholder="Room Id" name="roomId" onChange={(e) => {setRoom(e.target.value)}} />
                </div>
                <div className="textbox">
                    <input type="text" placeholder="Host Id" name="hostId" onChange={(e) => {setHost(e.target.value)}} />
                </div>
                <br/>
                <button className="btn" type="submit">Submit</button>
            </form>
            : 
            <div></div>}
            <DataGrid
                rows={meetingList}
                columns={columns}
                components={{
                    Toolbar: GridToolbar,
                }}
                initialState={{
                    filter: {
                    filterModel: {
                        items: [
                        {
                            columnField: 'commodity',
                            operatorValue: 'contains',
                            value: 'rice',
                        },
                        ],
                    },
                    },
                }}
            />
        </div>
    )
}

export default MeetingList