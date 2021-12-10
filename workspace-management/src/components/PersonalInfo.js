import React, { Component, useEffect, useState } from 'react';
import { Descriptions } from 'antd';
import './PersonalInfo.css';
import { Switch } from 'antd';
import axios from 'axios';

const server = require("../config/server");

const test01 = {
    username: "test01",
    name: "ruikang",
    groupId: 1,
    department: "IT"
}

const PersonalInfo = () => {

    const [wea, setWea] = useState({});
    useEffect(() => {
        async function update() {
            let weather = await axios.get(server.api + "weathers/").then(res => res.data);
            console.log(weather);
            let current = {
                last_updated: "2021-12-09 00:15",
                temp_c: 4.4,
                temp_f: 39.9,
                humidity: 76
            };
            current.last_updated = weather.current.last_updated;
            current.temp_c = weather.current.temp_c;
            current.temp_f = weather.current.temp_f;
            current.humidity = weather.current.humidity;
            setWea(current);
        }
        update();
        
        // get user and weather data
        // , {headers: {'x-authorization-token': 'loggedin'}}
    })

    function onChange(checked) {
        console.log(`switch to ${checked}`);
    }
    return (
        <div className="personalinfo">
            <div><span style={{fontSize: "30px"}}>Personal Center - {test01.username}</span></div>
            <br/>
            <br />
            <div className="row">
                <div className="col">
                    <div className="info">username: {test01.username}</div>
                </div>
                <div className="col">
                    <div className="info">name: {test01.name}</div>
                </div>
            </div>
            <div className="row">
                <div className="col">
                    <div className="info">group: {test01.groupId}</div>
                </div>
                <div className="col">
                    <div className="info">department: {test01.department}</div>
                </div>
            </div>
            <div className="row">
                <div className="col">
                    <span>sharing preference</span>
                    <span class="tooltip"><i class="fas fa-question-circle"></i>  
                        <span class="tooltiptext">Turn it off if you do not want anyone to find you.</span>
                    </span>
                    
                </div>
                <div className="col">
                    <Switch defaultChecked onChange={onChange} />
                </div>
            </div>
            <br/>
            <div>
                <span style={{fontSize: "20px"}}>Local Weather</span>
                <div className="row">
                    <div className="col">
                        <div className="info">last update: {wea.last_updated}</div>
                    </div>
                    <div className="col">
                        <div className="info">humidity: {wea.humidity}</div>
                    </div>
                </div>
                <div className="row">
                    <div className="col">
                        <div className="info">temperature(C): {wea.temp_c}</div>
                    </div>
                    <div className="col">
                        <div className="info">temperature(F): {wea.temp_f}</div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default PersonalInfo
