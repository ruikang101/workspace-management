import React from 'react';
 
export default (state, action) => {
   switch(action.type) {
       case 'UPDATE_ROOM':
           return {
            ...state,
            roomList: action.payload
           }
       case 'UPDATE_USER':
           return {
            ...state,
            userList: action.payload
           }
       default:
           return state;
   }
}