import React, { Component, useContext } from 'react';
import UserService from './services/UserService';
import './UserList.css';
import userList1 from '../public/User_data';
import roomList1 from '../public/Room_data';
import { GlobalContext } from './Context/GlobalState';
import { DataGrid, GridToolbar } from '@mui/x-data-grid';

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
    ]

    const users = userList.map(user => ({
        id: user.id,
        username: user.username,
        department: user.department,
        position: user.roomId === 0 ? "Not in any room" : "In room " + user.roomId + " (" + roomList1[user.roomId-1].type + ")"
    }))
        
    return(
        // <div className="userlist">
        //     <table>
        //         <tr className= "column">
        //             <th>ID</th>
        //             <th>Username</th>
        //             <th>Department</th>
        //             <th>Position</th>
        //         </tr>
        //         {userList1.map(user => 
        //             <tr>
        //                 <td>{user.id}</td>
        //                 <td>{user.username}</td>
        //                 <td>{user.department}</td>
        //                 <td>{user.roomId === 0 ? "Not in any room" : "In room " + user.roomId + " (" + roomList1[user.roomId-1].type + ")"}</td>
        //             </tr>
        //         )}
        //     </table>
        // </div>
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