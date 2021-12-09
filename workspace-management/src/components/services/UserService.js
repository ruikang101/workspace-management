import axios from 'axios';

const server = require("../../config/server");

class UserService {
    async signUp(username, password, name, department) {
        const data = {
            username: username,
            password: password,
            name: name,
            department: department
        }
        await axios.post(server.api + "user/", data)
            .then((res) => {
                console.log(res);
        });
    }

    // xml
    async login(username, password) {
        const xml = `<?xml version="1.0" encoding="UTF-8" ?>
        <root>
          <username>${username}</username>
          <password>${password}</password>
        </root>`;
        const headers = {'x-authorization-token': 'loggedin', 'Content-Type': 'text/xml'};
        await axios.post(server.api + "user/login/", xml, {headers})
            .then(res => {
                console.log(res);
            })
    }

    async fetchUserList() {
        const res = await axios.get(server.api + "users/");
        return res.data;
    }

    async updateUser(id, roomId, ) {
        const data = {roomId: roomId}
        await axios.put(server.api + "user/" + id, data)
            .then(res => {
                console.log(res);
            })
    }
}

export default UserService