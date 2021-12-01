import React, { useContext, useState } from 'react';
import '../App.css';
import './HomeForm.css';
import { AccountContext } from './AccountContext';
import styled from 'styled-components';

const Form = styled.form``;

function HomeForm() {
  const [isExpanded, setExpanded] = useState(false);
  const [active, setActive] = useState("signin");

  const playExpandingAnimation = () => {
    setExpanded(true);
    setTimeout(() => {
      setExpanded(false);
    });
  };

  const switchToSignup = () => {
    playExpandingAnimation();
    setTimeout(() => {
      setActive("signup");
    }, 400);
  };

  const switchToSignin = () => {
    playExpandingAnimation();
    setTimeout(() => {
      setActive("signin");
    }, 400);
  };

  const contextValue = { switchToSignup, switchToSignin };

  function LoginForm() {
    return (
      <div class="login-box">
          <h1>Login</h1>
          <div class="textbox">
              <i class="fas fa-user"></i>
              <input type="text" placeholder="Username" />
          </div>
          <div class="textbox">
              <i class="fas fa-lock"></i>
              <input type="password" placeholder="Password" />
          </div>
          <button class="btn">Sign in</button>
          Don't have an account?  <a href="#" onClick={switchToSignup}>Signup</a>
      </div>
    )
  }

  function SignupForm() {
    return (
      <Form class="login-box">
          <h1>Signup</h1>
          <div class="textbox">
              <i class="fas fa-user"></i>
              <input type="text" placeholder="Username" />
          </div>
          <div class="textbox">
              <i class="fas fa-lock"></i>
              <input type="password" placeholder="Password" />
          </div>
          <div class="textbox">
              <input type="text" placeholder="Name" />
          </div>
          <select name="department" placeholder="Department">
            <option value="" disabled selected class="default">Select your department</option>
            <option value="IT">IT</option>
            <option value="Ads">Ads</option>
            <option value="Cloud">Cloud</option>
          </select>
          <button class="btn">Sign up</button>
          Already have an account?  <a href="#" onClick={switchToSignin}>Signin</a>
      </Form>
    )
  }

  // const { switchToSignup } = useContext(AccountContext);
  return (
    <div>
      {active === "signin" ? <LoginForm /> : <SignupForm />}
    </div>
  );
}

export default HomeForm;
