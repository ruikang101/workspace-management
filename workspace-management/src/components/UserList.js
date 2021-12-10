import React, { Component, useContext, useState, useEffect } from 'react';
import UserService from './services/UserService';
import './UserList.css';
import userList1 from '../public/User_data';
import roomList1 from '../public/Room_data';
import { GlobalContext } from './Context/GlobalState';
import { DataGrid, GridToolbar } from '@mui/x-data-grid';
import Button from "@material-ui/core/Button";
import {Button as B} from 'antd';

import axios from 'axios';
const server = require("../config/server");

const UserList = () => {
    // const {userList, updateUserList} = useContext(GlobalContext);
    // const {roomList, updateRoomList} = useContext(GlobalContext);
    // if (roomList.length === 0) {
    //     updateRoomList(roomList1);
    // }

    // if (userList.length === 0) {
    //     updateUserList(userList1);
    // }

    // const users = userList.map(user => ({
    //     id: user.id,
    //     username: user.username,
    //     department: user.department,
    //     position: user.roomId === 0 ? "Not in any room" : "In room " + user.roomId + " (" + roomList1[user.roomId-1].type + ")"
    // }))

    const [userList, setUserList] = useState([]);

    const [isFormVisible, setIsvisible] = useState(false);
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [email, setEmail] = useState("");
    const [department, setDepartment] = useState("");

    useEffect(async() => {
        let users = await axios.get(server.api + "users/")
            .then(res => res.data);
        let rooms = await axios.get(server.api + "rooms/")
            .then(res => res.data);
        let userList = users.map(user => ({
            id: user.id,
            username: user.username,
            department: user.department,
            email: user.email,
            location: user.roomId === 0 ? "Not in any room" : "In room " + user.roomId + " (" + rooms.filter(room => room.id === user.roomId)[0].name + ")"
        }))
        setUserList(userList);
    })

    const columns = [
        { field: 'id', headerName: 'ID', width: 200 },
        { field: 'username', headerName: 'Username', width: 250 },
        { field: 'department', headerName: 'Department', width: 200 },
        { field: 'email', headerName: 'Email', width: 250 },
        { field: 'location', headerName: 'Location', width: 300 },
        { 
            field: '',
            headerName: 'Action',
            sortable: false,
            filterable: false,
            width: 200,
            renderCell: (params) => (
              <Button
                onClick={async() => {
                //   let clonedusers = JSON.parse(JSON.stringify(userList));
                //   console.log(params.row);
                //   const id = params.row.id
                //   clonedusers.splice(id-1, 1);
                //   updateUserList(clonedusers);
                  
                  await UserService.deleteUser(params.row.id);
                }}
              >
                Delete
              </Button>
            ) 
        }
    ]

    const handleSubmit = async(e) => {
        e.preventDefault();
        // let clonedusers = JSON.parse(JSON.stringify(userList));
        // console.log(clonedusers);
        // clonedusers.push(
        //     {
        //         id: clonedusers[clonedusers.length-1].id + 1,
        //         username: username,
        //         password: password,
        //         department: department,
        //         roomId: 0
        //     }
        // )
        // updateUserList(clonedusers);

        setIsvisible(false);
        const newUser = {
            id: userList[userList.length-1].id+1,
            username: username,
            password: password,
            email: email,
            department: department,
            privilege: 1,
            floor: 1,
            location_x: 0,
            location_y: 0
        };
        UserService.createUser(newUser);

        // waiting to be tested
        // await UserService.login(username, password);
        
        // navigate('/personalcenter');
    }
        
    return(
        
        <div className="userlist" style={{ height: 630, width: '100%' }}>
            <h1 style={{marginLeft: "10px"}}>UserList</h1>
            <B style={{marginLeft: "10px"}} type="primary" size="large" onClick={()=>setIsvisible(!isFormVisible)}>Create a user</B>
            <br />
            <br />
            {isFormVisible 
            ? 
            <form className="createuserform" onSubmit={handleSubmit}>
                <div className="textbox">
                    <input type="text" placeholder="Username" name="username" onChange={(e) => {setUsername(e.target.value)}} />
                </div>
                <div className="textbox">
                    <input type="password" placeholder="Password" name="password" onChange={(e) => {setPassword(e.target.value)}} />
                </div>
                <div className="textbox">
                    <input type="text" placeholder="Email" name="email" onChange={(e) => {setEmail(e.target.value)}} />
                </div>
                <select className="textbox" name="department" placeholder="Department" onChange={(e) => {setDepartment(e.target.value)}} >
                  <option value="" disabled selected className="default">Select your department</option>
                  <option value="IT">IT</option>
                  <option value="Ads">Ads</option>
                  <option value="Cloud">Cloud</option>
                </select>
                <br/>
                <button className="btn" type="submit">Submit</button>
            </form>
            : 
            <div></div>}
            <DataGrid
                rows={userList}
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