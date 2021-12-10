import axios from 'axios';

const server = require("../../config/server");

class MeetingService {

    async createMeeting(data) {
        await axios.post(server.api + "meetings/", data)
            .then((res) => {
                console.log(res);
        });
    }

    async fetchMeetingList() {
        return await axios.get(server.api + "meetings/")
            .then(res => res.data);
    }

    async getMeeting(id) {
        return await axios.get(server.api + "meetings/" + id)
            .then(res => res.data);
    }

    //!!!
    async updateMeeting(id, data) {
        await axios.put(server.api + "meetings/" + id, data)
            .then(res => {
                console.log(res);
            })
    }

    async deleteMeeting(id) {
        await axios.delete(server.api + "meetings/" + id)
            .then(res => console.log(res));
    }
}

export default MeetingService