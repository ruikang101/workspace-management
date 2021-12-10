import React, { Component, useContext } from 'react';
import UserService from './services/UserService';
import './UserList.css';
import userList1 from '../public/User_data';
import roomList1 from '../public/Room_data';
import { GlobalContext } from './Context/GlobalState';
import { DataGrid, GridToolbar } from '@mui/x-data-grid';
import Button from "@material-ui/core/Button";

const meetings = [
    {id: 1, start: "2021-12-10 09:15", end: "2021-12-10 10:15", room: 1},
    {id: 2, start: "2021-12-10 11:15", end: "2021-12-10 11:45", room: 1},
    {id: 3, start: "2021-12-10 10:00", end: "2021-12-10 10:30", room: 3},
    {id: 4, start: "2021-12-10 14:00", end: "2021-12-10 17:15", room: 4},
    {id: 5, start: "2021-12-10 13:30", end: "2021-12-10 14:30", room: 6},
    {id: 6, start: "2021-12-10 16:15", end: "2021-12-10 16:50", room: 3},
]

const MeetingList = () => {
    const {userList, updateUserList} = useContext(GlobalContext);
    const {roomList, updateRoomList} = useContext(GlobalContext);
    if (roomList.length === 0) {
        updateRoomList(roomList1);
    }
    console.log(userList)

    if (userList.length === 0) {
        updateUserList(userList1);
    }
    console.log(userList)

    const meetingList = meetings.map(meeting => ({
        id: meeting.id,
        start: meeting.start,
        end: meeting.end,
        room: "Meetingroom " + meeting.room
    }))

    const columns = [
        { field: 'id', headerName: 'ID', width: 200 },
        { field: 'start', headerName: 'Start', width: 300 },
        { field: 'end', headerName: 'End', width: 300 },
        { field: 'room', headerName: 'Room', width: 250 },
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
                  
                  // await MeetingService.deleteMeeting(params.row.id);
                }}
              >
                Delete
              </Button>
            ) 
        }
    ]

        
    return(
        
        <div style={{ height: 630, width: '100%' }}>
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