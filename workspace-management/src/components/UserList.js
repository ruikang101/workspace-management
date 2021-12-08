import React, { Component } from 'react';
import UserService from './services/UserService';
import './UserList.css';


export default class UserList extends Component {
    constructor() {
        super();
        this.state = {
            users: []
        }
    }

    async componentDidMount() {
        // get data
        // let userList = await UserService.fetchUserList();
        // this.setState({
        //     users: userList
        // })
        if (this.state.users.length === 0) {
            this.setState({
                users: [
                    {
                        id: 1,
                        username: "testuser1",
                        department: "IT",
                        position: "meetingroom1"
                    },
                    {
                        id: 2,
                        username: "testuser2",
                        department: "IT",
                        position: "not in any room"
                    },
                    {
                        id: 3,
                        username: "testuser3",
                        department: "Cloud",
                        position: "meetingroom2"
                    },
                    {
                        id: 4,
                        username: "testuser4",
                        department: "IT",
                        position: "diningroom"
                    },
                ]
            })
        }
    }

    render() {
        
        return(
            <div className="userlist">
                <table>
                    <tr className= "column">
                        <th>ID</th>
                        <th>Username</th>
                        <th>Department</th>
                        <th>Position</th>
                    </tr>
                    {this.state.users.map(user => 
                        <tr>
                            <td>{user.id}</td>
                            <td>{user.username}</td>
                            <td>{user.department}</td>
                            <td>{user.position}</td>
                        </tr>
                    )}
                </table>
            </div>
        )
    }
}
