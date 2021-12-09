import React, { Component } from 'react';
import { Descriptions } from 'antd';
import './PersonalInfo.css';
import { Switch } from 'antd';

export default class PersonalInfo extends Component {

    render() {
        function onChange(checked) {
            console.log(`switch to ${checked}`);
        }
        return (
            <div className="personalinfo">
                <h2>Personal Center</h2>
                <br />
                <span style={{fontSize: "15px"}}>Sharing preference   </span><Switch defaultChecked onChange={onChange} />
                <br />
                <br />
                <Descriptions className="descriptions" title="User Info" style={{fontSize: "20px"}} >
                    <Descriptions.Item label="UserName"><span style={{fontSize: "20px"}}>Zhou Maomao</span></Descriptions.Item>
                    <Descriptions.Item label="Telephone">1810000000</Descriptions.Item>
                    <Descriptions.Item label="Live">Hangzhou, Zhejiang</Descriptions.Item>
                    <Descriptions.Item label="Remark">empty</Descriptions.Item>
                    <Descriptions.Item label="Address">
                    No. 18, Wantang Road, Xihu District, Hangzhou, Zhejiang, China
                    </Descriptions.Item>
                </Descriptions>
            </div>
        )
    }
}
