import React, { Component } from 'react';
import UserService from './services/UserService';
import { Link } from 'react-router-dom';

class LoginForm extends Component {
    constructor() {
        super();
        this.state = {
            username: "",
            password: ""
        }
        this.handleSubmit = this.handleSubmit.bind(this);
        this.onChange = this.onChange.bind(this);
        // this.props.onClick.bind(this);
    }

    handleSubmit = async(e) => {
        e.preventDefault();
        const { username, password } = this.state;
        console.log(this.state);
        const navigate = this.props.navigate;
        // waiting to be tested
        await UserService.login({username: username, password: password})
            .then(()=>{navigate('/personalcenter');});
        // navigate('/personalcenter');
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
                Don't have an account?  <Link to="/" onClick={this.props.onClick}>Signup</Link>
            </form>
          )
    }
}


export default LoginForm
