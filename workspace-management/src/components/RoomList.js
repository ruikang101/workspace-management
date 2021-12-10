import React, { Component, useContext } from 'react';
// import RoomService from './services/RoomService';
import './RoomList.css';
import roomList1 from '../public/Room_data';
import { GlobalContext } from './Context/GlobalState';
import { DataGrid, GridToolbar } from '@mui/x-data-grid';

const RoomList = () => {
    
    const {roomList, updateRoomList} = useContext(GlobalContext);
    console.log(roomList);
    
    if (roomList.length === 0) {
        updateRoomList(roomList1);
    }

    const columns = [
        { field: 'id', headerName: 'ID', width: 200 },
        { field: 'status', headerName: 'Status', width: 200 },
        { field: 'density', headerName: 'Density', width: 200 },
        { field: 'capacity', headerName: 'Capaticy', width: 200 },
        { field: 'type', headerName: 'Type', width: 200 },
    ]

    return(
        
        <div style={{ height: 630, width: '100%' }}>
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