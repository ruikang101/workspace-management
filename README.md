# workspace-management

## To view the UI
```
cd workspace-management
npm install
npm start
```

- modifications
    - Users
        - roomId
        - UserEntity: +email, groupId-privilege, +floor, -roomId, coordinates-locationX&Y, -sharing
        - createUser: based on entity
        - login token: authcontext to track token & username & id
    - Rooms
        - RoomEntity: +floor, -density, type-name, -status
        - createroom: just create a button

