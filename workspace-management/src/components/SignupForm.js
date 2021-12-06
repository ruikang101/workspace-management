import React, { Component } from 'react';
import UserService from './services/UserService';
import { useNavigate } from 'react-router-dom';

class InnerSignupForm extends Component {
    constructor() {
        super();
        this.state = {
            username: "",
            password: "",
            name: "",
            department: ""
        }
        this.handleSubmit = this.handleSubmit.bind(this);
        this.onChange = this.onChange.bind(this);
    }

    handleSubmit = async(e) => {
        e.preventDefault();
        const { username, password, name, department } = this.state;
        console.log(this.state);
        // waiting to be tested
        // await UserService.signUp(username, password, name, department);
        const navigate = this.props.navigate;
        navigate('/personalcenter');
    }

    onChange = (e) => {
        this.setState({ [e.target.name]: e.target.value });
    }

    render() {
        return (
            <form className="HomeForm" onSubmit={this.handleSubmit}>
                <h1>Signup</h1>
                <div className="textbox">
                    <i className="fas fa-user"></i>
                    <input type="text" placeholder="Username" name="username" onChange={this.onChange} />
                </div>
                <div className="textbox">
                    <i className="fas fa-lock"></i>
                    <input type="password" placeholder="Password" name="password" onChange={this.onChange} />
                </div>
                <div className="textbox">
                    <input type="text" placeholder="Name" name="name" onChange={this.onChange} />
                </div>
                <select name="department" placeholder="Department" onChange={this.onChange} >
                  <option value="" disabled selected className="default">Select your department</option>
                  <option value="IT">IT</option>
                  <option value="Ads">Ads</option>
                  <option value="Cloud">Cloud</option>
                </select>
                <button className="btn" type="submit">Sign up</button>
                Already have an account?  <a href="#" onClick={this.props.onClick}>Signin</a>
            </form>
        )
    }
}

function SignupForm() {
    const navigate = useNavigate();
    return <InnerSignupForm navigate={navigate} />
}

export default SignupForm
