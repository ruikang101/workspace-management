import React, { Component, useContext, useState } from 'react';
import UserService from './services/UserService';
import './UserList.css';
import userList1 from '../public/User_data';
import roomList1 from '../public/Room_data';
import { GlobalContext } from './Context/GlobalState';
import { DataGrid, GridToolbar } from '@mui/x-data-grid';
import Button from "@material-ui/core/Button";
import {Button as B} from 'antd';

const UserList = () => {
    const {userList, updateUserList} = useContext(GlobalContext);
    const {roomList, updateRoomList} = useContext(GlobalContext);
    const [isFormVisible, setIsvisible] = useState(false);
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [name, setName] = useState("");
    const [department, setDepartment] = useState("");
    const [u, setU] = useState([]);

    if (roomList.length === 0) {
        updateRoomList(roomList1);
    }

    if (userList.length === 0) {
        updateUserList(userList1);
    }

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


    const changeUsername = (e) => {
        setUsername(e.target.value)
    }

    const changePassword = (e) => {
        setPassword(e.target.value)
    }

    const changeName = (e) => {
        setName(e.target.value)
    }

    const changeDepartment = (e) => {
        setDepartment(e.target.value)
    }

    const handleSubmit = async(e) => {
        e.preventDefault();
        let clonedusers = JSON.parse(JSON.stringify(userList));
        console.log(clonedusers);
        clonedusers.push(
            {
                id: clonedusers[clonedusers.length-1].id + 1,
                username: username,
                password: password,
                name: name,
                department: department,
                roomId: 0
            }
        )
        // setU(clonedusers);
        setIsvisible(false);
        updateUserList(clonedusers);


        // waiting to be tested
        // await UserService.login(username, password);
        
        // navigate('/personalcenter');
    }
        
    return(
        
        <div className="userlist" style={{ height: 630, width: '100%' }}>
            <B style={{marginLeft: "10px"}} type="primary" size="large" onClick={()=>setIsvisible(!isFormVisible)}>Create a user</B>
            {isFormVisible 
            ? 
            <form className="createuserform" onSubmit={handleSubmit}>
                <div className="textbox">
                    <input type="text" placeholder="Username" name="username" onChange={changeUsername} />
                </div>
                <div className="textbox">
                    <input type="password" placeholder="Password" name="password" onChange={changePassword} />
                </div>
                <div className="textbox">
                    <input type="text" placeholder="Name" name="name" onChange={changeName} />
                </div>
                <select className="textbox" name="department" placeholder="Department" onChange={changeDepartment} >
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