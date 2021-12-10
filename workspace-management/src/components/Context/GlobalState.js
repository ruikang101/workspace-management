import React, { createContext, useReducer } from 'react';
import AppReducer from './AppReducer';
import roomList1 from '../../public/Room_data';

const initialState = {
   roomList: [],
   userList: []
}

export const GlobalContext = createContext(initialState);

export const GlobalProvider = ({ children }) => {
   const [state, dispatch] = useReducer(AppReducer, initialState);

   // Actions for changing state

   function updateRoomList(item) {
       dispatch({
           type: 'UPDATE_ROOM',
           payload: item
       });
   }
   function updateUserList(item) {
       dispatch({
           type: 'UPDATE_USER',
           payload: item
       });
   }

   return(
    <GlobalContext.Provider value = {{roomList: state.roomList, userList: state.userList, updateRoomList, updateUserList}}> 
        {children} 
   </GlobalContext.Provider>
   )
}