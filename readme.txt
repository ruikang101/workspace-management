Front end

To start the front end, please open Workspace Management directory in the terminal and execute following commands
    cd workspace-Management
    npm instal
    npm start

The front end is implemented with react, using hooks to manage state and global context.

Structure of directory workspace-management/src

.
├── components - containing all UI components used to establish the front end
│   ├── Background.js
│   ├── FloorPlan.js
│   ├── LoginForm.js
│   ...
│   ├── Context - containing all global context
│   ├── pages - containing all main pages based on each route of pages
│   │   ├── Demo.js
│   │   ├── Home.js
│   │   ├── Map.js
│   │   ...
│   └── services - containing all services managing api calls and establishing connection with the server
│       ├── MeetingService.js
│       ├── RoomService.js
│       └── UserService.js
├── config - some configrations for modular use
│   ├── history.js
│   └── server.js
├── public - containing data used for initialization
│   ├── RFID_data.js
│   ├── RFID_data.json
│   ├── Room_data.js
│   ├── User_data.js
│   └── videos
