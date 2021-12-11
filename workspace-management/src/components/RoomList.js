import React, { Component, useContext, useState, useEffect } from 'react';
import './RoomList.css';
import roomList1 from '../public/Room_data';
import { GlobalContext } from './Context/GlobalState';
import { DataGrid, GridToolbar } from '@mui/x-data-grid';
import Button from "@material-ui/core/Button";
import RoomService from './services/RoomService';
import {Button as B} from 'antd';

import axios from 'axios';
const server = require("../config/server");

// This component contains test data with Context.
// Room list under the service tab
const RoomList = () => {
    // const {roomList, updateRoomList} = useContext(GlobalContext);
    // if (roomList.length === 0) {
    //   updateRoomList(roomList1);
    // }

    const [roomList, setRoomList] = useState([]);

    const [isFormVisible, setIsvisible] = useState(false);
    
    const columns = [
        { field: 'id', headerName: 'ID', width: 200 },
        { field: 'status', headerName: 'Status', width: 200 },
        { field: 'density', headerName: 'Density', width: 200 },
        { field: 'capacity', headerName: 'Capaticy', width: 200 },
        { field: 'name', headerName: 'Type', width: 200 },
        { 
          field: '',
          headerName: 'Action',
          sortable: false,
          filterable: false,
          width: 200,
          renderCell: (params) => (
            <Button
              onClick={async() => {
                // let clonedrooms = JSON.parse(JSON.stringify(roomList));
                // console.log(params.row);
                // const id = params.row.id
                // clonedrooms.splice(id-1, 1);
                // updateRoomList(clonedrooms);
                
                await RoomService.deleteRoom(params.row.id);
              }}
            >
              Delete
            </Button>
          ) 
        }
    ]

    return(
        
        <div style={{ height: 630, width: '100%' }}>
          <h1 style={{marginLeft: "10px"}}>RoomList</h1>
            <B style={{marginLeft: "10px"}} type="primary" size="large" onClick={()=>setIsvisible(!isFormVisible)}>Create a room</B>
            <br />
            <br />
            <DataGrid
                rows={roomList}
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

export default RoomList