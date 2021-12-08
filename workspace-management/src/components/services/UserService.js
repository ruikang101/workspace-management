import axios from 'axios';

const server = require("../../config/server");

class UserService {
    async signUp(username, password, name, department) {
        await axios.post(server.api + "user/", { username, password, name, department })
            .then((res) => {
                console.log(res);
        });
    }

    async login(username, password) {
        await axios.post(server.api + "user/login/", { username, password })
            .then(res => {
                console.log(res);
            })
    }

    async fetchUserList() {
        const res = await axios.get(server.api + "users/");
        return res.data;
    }

    async updateUser(id, roomId, ) {
        await axios.put(server.api + "user/" + id, { roomId })
            .then(res => {
                console.log(res);
            })
    }
}

export default UserService