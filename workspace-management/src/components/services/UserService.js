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
        await axios.post(server.api + "users/", data)
            .then((res) => {
                console.log(res);
        });
    }

    // xml
    async login(username, password) {
        const xml = `<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:wsdl="http://tcss556/services/models/wsdl">
            <soapenv:Header/>
            <soapenv:Body>
                <wsdl:LoginRequest>
                    <wsdl:name>${username}</wsdl:name>
                    <wsdl:password>${password}</wsdl:password>
                </wsdl:LoginRequest>
            </soapenv:Body>
        </soapenv:Envelope>`;
        const token = username+"_"+username;
        
        const headers = {'x-authorization-token': token, 'Content-Type': 'text/xml'};
        await axios.post(server.api + "user/login/", xml, {headers})
            .then(res => {
                console.log(res);
            })
    }

    async fetchUserList() {
        const res = await axios.get(server.api + "users/");
        return res.data;
    }

    //!!!
    async updateUser(id, roomId) {
        const data = {roomId: roomId}
        await axios.put(server.api + "users/" + id, data)
            .then(res => {
                console.log(res);
            })
    }

    async deleteUser(id) {
        await axios.delete(server.api + "users/" + id)
            .then(res => console.log(res));
    }
}

export default UserService