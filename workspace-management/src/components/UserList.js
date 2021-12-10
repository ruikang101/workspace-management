import React, { Component, useContext } from 'react';
import UserService from './services/UserService';
import './UserList.css';
import userList1 from '../public/User_data';
import roomList1 from '../public/Room_data';
import { GlobalContext } from './Context/GlobalState';
import { DataGrid, GridToolbar } from '@mui/x-data-grid';
import Button from "@material-ui/core/Button";

const UserList = () => {
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


    const columns = [
        { field: 'id', headerName: 'ID', width: 200 },
        { field: 'username', headerName: 'Username', width: 250 },
        { field: 'department', headerName: 'Department', width: 250 },
        { field: 'position', headerName: 'Position', width: 300 },
        { 
            field: '',
            headerName: 'Action',
            sortable: false,
            filterable: false,
            width: 200,
            renderCell: (params) => (
              <Button
                onClick={async() => {
                  let clonedusers = JSON.parse(JSON.stringify(userList));
                  console.log(params.row);
                  const id = params.row.id
                  clonedusers.splice(id-1, 1);
                  updateUserList(clonedusers);
                  
                  // await UserService.deleteUser(params.row.id);
                }}
              >
                Delete
              </Button>
            ) 
        }
    ]

    const users = userList.map(user => ({
        id: user.id,
        username: user.username,
        department: user.department,
        position: user.roomId === 0 ? "Not in any room" : "In room " + user.roomId + " (" + roomList1[user.roomId-1].type + ")"
    }))
        
    return(
        
        <div style={{ height: 630, width: '100%' }}>
            <DataGrid
                rows={users}
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

export default UserList