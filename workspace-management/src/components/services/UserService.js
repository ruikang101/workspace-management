import axios from 'axios';

const server = require("../../config/server");

class UserService {

    async signUp(data) {
        await axios.post(server.api + "users/", data)
            .then((res) => {
                console.log(res);
        });
    }

    // xml
    async login(data) {
        const xml = `<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:wsdl="http://tcss556/services/models/wsdl">
            <soapenv:Header/>
            <soapenv:Body>
                <wsdl:LoginRequest>
                    <wsdl:name>${data.username}</wsdl:name>
                    <wsdl:password>${data.password}</wsdl:password>
                </wsdl:LoginRequest>
            </soapenv:Body>
        </soapenv:Envelope>`;
        const token = data.username+"_"+data.username;
        
        const headers = {'x-authorization-token': token, 'Content-Type': 'text/xml'};
        await axios.post(server.api + "user/login/", xml, {headers})
            .then(res => {
                console.log(res);
            })
    }

    async fetchUserList() {
        return await axios.get(server.api + "users/")
            .then(res => res.data);
    }

    async getUser(id) {
        return await axios.get(server.api + "users/" + id)
            .then(res => res.data);
    }

    //!!!
    async updateUser(id, data) {
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