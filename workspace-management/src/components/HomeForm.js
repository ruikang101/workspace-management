import React, { useContext, useState, Component } from 'react';
import '../App.css';
import './HomeForm.css';
import LoginForm from './LoginForm';
import SignupForm from './SignupForm';

class HomeForm extends Component {

  constructor(props) {
    super(props);
    this.state = {
      active: "signin",

    };
    this.switchToSignin = this.switchToSignin.bind(this);
    this.switchToSignup = this.switchToSignup.bind(this);
  }

  switchToSignup = () => {
    this.setState({active:"signup"})
  };

  switchToSignin = () => {
    this.setState({active:"signin"})
  };

  render() {
    return (
      <div>
        {this.state.active === "signin" && <LoginForm  onClick={this.switchToSignup}/>}
        {this.state.active === "signup" && <SignupForm onClick={this.switchToSignin}/>}
      </div>
    );
  }
}

export default HomeForm;
