import React, { Component, useContext } from 'react';
// import RoomService from './services/RoomService';
import './RoomList.css';
import roomList1 from '../public/Room_data';
import { GlobalContext } from './Context/GlobalState';
import { DataGrid, GridToolbar } from '@mui/x-data-grid';
import Button from "@material-ui/core/Button";
import RoomService from './services/RoomService';

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
        { 
          field: '',
          headerName: 'Action',
          sortable: false,
          filterable: false,
          width: 200,
          renderCell: (params) => (
            <Button
              onClick={async() => {
                let clonedrooms = JSON.parse(JSON.stringify(roomList));
                console.log(params.row);
                const id = params.row.id
                clonedrooms.splice(id-1, 1);
                updateRoomList(clonedrooms);
                
                // await RoomService.deleteRoom(params.row.id);
              }}
            >
              Delete
            </Button>
          ) 
        }
    ]

    // const rooms = roomList.map(room => ({
    //     id: room.id,
    //     status: room.status,
    //     density: room.density,
    //     capacity: room.capacity,
    //     type: room.type,
    //     action: `<button>delete</button>`
    // }))

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