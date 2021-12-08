import axios from 'axios';

const server = require("../../config/server");

class RoomService {
    async updateRoom(id, status, density) {
        await axios.put(server.api + "room/" + id, { status, density })
            .then((res) => {
                console.log(res);
        });
    }

    async fetchRoomList() {
        const res = await axios.get(server.api + "rooms/");
        return res.data;
    }
}

export default RoomService