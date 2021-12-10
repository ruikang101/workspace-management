import axios from 'axios';

const server = require("../../config/server");

class RoomService {
    //!!!
    async updateRoom(id, data) {
        await axios.put(server.api + "rooms/" + id, data)
            .then((res) => {
                console.log(res);
        });
    }

    async fetchRoomList() {
        return await axios.get(server.api + "rooms/")
            .then(res => res.data);
    }

    async getRoom(id) {
        return await axios.get(server.api + "rooms/" + id)
            .then(res => res.data);
    }

    async deleteRoom(id) {
        await axios.delete(server.api + "rooms/" + id)
            .then(res => console.log(res));
    }
}

export default RoomService