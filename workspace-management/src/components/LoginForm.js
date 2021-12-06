import React, { Component } from 'react';
import { useNavigate } from 'react-router-dom';
import UserService from './services/UserService';

class InnerLoginForm extends Component {
    constructor() {
        super();
        this.state = {
            username: "",
            password: ""
        }
        this.handleSubmit = this.handleSubmit.bind(this);
        this.onChange = this.onChange.bind(this);
    }

    handleSubmit = async(e) => {
        e.preventDefault();
        const { username, password } = this.state;
        console.log(this.state);
        // waiting to be tested
        // await UserService.login(username, password);
        const navigate = this.props.navigate;
        navigate('/personalcenter');
    }

    onChange = (e) => {
        this.setState({ [e.target.name]: e.target.value });
    }

    render() {
        return (
            <form className="HomeForm" onSubmit={this.handleSubmit}>
                <h1>Login</h1>
                <div className="textbox">
                    <i className="fas fa-user"></i>
                    <input type="text" placeholder="Username" name="username" onChange={this.onChange} />
                </div>
                <div className="textbox">
                    <i className="fas fa-lock"></i>
                    <input type="password" placeholder="Password" name="password" onChange={this.onChange} />
                </div>
                <button className="btn">Sign in</button>
                Don't have an account?  <a href="#" onClick={this.props.onClick}>Signup</a>
            </form>
          )
    }
}

function LoginForm() {
    const navigate = useNavigate();
    return <InnerLoginForm navigate={navigate} />
}

export default LoginForm
