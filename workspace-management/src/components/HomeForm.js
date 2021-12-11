import React, { useContext, useState, Component } from 'react';
import '../App.css';
import './HomeForm.css';
import LoginForm from './LoginForm';
import SignupForm from './SignupForm';
import { useNavigate } from 'react-router-dom';

// Parent component of the login form and the signup form
class InnerHomeForm extends Component {

  constructor(props) {
    super(props);
    this.state = {
      active: "signup",

    };
  }

  switchToSignup = () => {
    this.setState({active:"signup"});
  };

  switchToSignin = () => {
    this.setState({active:"signin"});

  };

  render() {
    return (
      <div>
        {this.state.active === "signin" ? <LoginForm onClick={this.switchToSignup} navigate={this.props.navigate}/> : <SignupForm onClick={this.switchToSignin} navigate={this.props.navigate}/>}
      </div>
    );
  }
}

function HomeForm() {
  const navigate = useNavigate();
  return <InnerHomeForm navigate={navigate} />
}

export default HomeForm;
