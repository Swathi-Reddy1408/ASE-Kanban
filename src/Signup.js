// Login.js

import React, { useState } from "react";
import axios from "./Components/axios";
import "./Signup.css";



const Signup = ({ history }) => {
    const [firstname, setFirstname] = useState("");
    const [lastname, setLastname] = useState("");
 
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState('');

  const handleSignup = async () => {
    setError('');
    // Validate email format
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
      setError('Invalid email format');
      return;
    }

    // Validate password length
    if (password.length < 8) {
      setError('Password must be at least 8 characters long');
      return;
    }
   /*try {
      const response = await axios.post("http://localhost:8080/Signup", {
        firstname,
        lastname,
        email,
        password,
      });
     

      // Save the JWT token in local storage or cookies
      localStorage.setItem("token", response.data.token);

      // Redirect to a protected route or home page
     // history.push("/dashboard");
    } catch (error) {
      console.error("Signup failed", error);
      // Handle login failure
    }*/
   /*const response= fetch('http://127.0.0.1:8080/api/auth/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ firstName: firstname,lastName:lastname,email:email,password:password})
    })*/
    const response = await axios.post("http://127.0.0.1:8080/Signup", {
        firstName: firstname,lastName:lastname,email:email,password:password
      });

      const message = response.data;
      console.log(message);
      const jwtToken = response.data.token;

    // Store the token in localStorage or a secure storage mechanism
    localStorage.setItem("token", jwtToken);
    if(message==="This email already exists")
     alert('This email already exists')
    //setError('This email already exists');
    else
    {
        alert('User account created successfully. Please login ')
    history.push('/login');
    }

  };
  const handleLogin  = () => {
    // Redirect to the signup page
    history.push('/login');
  };

  return (
    <div className="container">
      <h2 className="heading">Create a new account!</h2>
      <div className="form-group">
        <label className="label">First Name:</label>
        <input
          type="firstname"
          value={firstname}
          onChange={(e) => setFirstname(e.target.value)}
          className="input"
        />
      </div>
      <div className="form-group">
        <label className="label">Last Name:</label>
        <input
          type="lastname"
          value={lastname}
          onChange={(e) => setLastname(e.target.value)}
          className="input"
        />
      </div> 
      <div className="form-group">
        <label className="label">Email:</label>
        <input
          type="email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          className="input"
        />
      </div>
      <div className="form-group">
        <label className="label">Password:</label>
        <input
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          className="input"
        />
      </div>
      <div className="button-container">
      <button onClick={handleLogin} className="button">
        Login
        
      </button>
      
      <button  onClick={handleSignup} className="button2">
        Signup
      </button>
      </div>
      {error && <p style={{ color: 'red' }}>{error}</p>}
    </div>
  );
};

export default Signup;
