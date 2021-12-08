import React from "react";

// set the defaults
const RoomContext = React.createContext({
  roomsToUpdate: [],
  update: () => {}
});

export default RoomContext;