// Login.js

import React, { useState } from "react";
import "./Login.css";
const Login = ({ history }) => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const handleLogin = async () => {
   /* const jwtToken = localStorage.getItem("token");

    //try {
      const response = await axios.post("/api/auth/login", {
        email:email,
        password:password
      })
      .then(response => {
        if (response.status === 200) {
          // The request was successful (status code 200)
          console.log("Success");
          // You can also access the response data
          //console.log(response.data);
          const { body, token } = response.data;

      // Now you can use the body and token as needed
      console.log("Response body:", body);
      console.log("JWT token:", token);
      localStorage.setItem("token", token);

      history.push({
        pathname: "/app",
        state: { responseData: body }
      });


        } else {
          // Handle other status codes
          console.log("Request failed with status code: " + response.status);
        }
      })
      .catch(error => {
        // Handle errors
        console.error("Error:", error);
      });
*/
      
  
  

    // Store the token in localStorage or a secure storage mechanism
   



      if (!email || !password) {
        setError("Email and password are required.");
        return;
      }
      const response= fetch('http://localhost:8080/Login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({email:email,password:password})
    })

    const data= (await (await response).text());
    console.log(data);
       
    if(data==='Invalid User email or Password')
    {
    setError("Invalid User email or Password");
    return;
    }
      // Save the JWT token in local storage or cookies
    // localStorage.setItem("token", response.data.token);

      // Redirect to a protected route or home page
      
      history.push({
        pathname: "/app",
        state: { responseData: data }
      });

      
};

  const handleSignup = () => {
    // Redirect to the signup page
    history.push('/signup');
  };

  return (
    <div className="container">
      <h2 className="heading">Get Started</h2>
      
     {/* <div className="form-group">
        <label className="label">First Name:</label>
        <input
          className="input"
        />
      </div>
      <div className="form-group">
        <label className="label">Last Name:</label>
        <input
          className="input"
        />
      </div> */}
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
      <div className="error-message">{error}</div>
      <div className="button-container">
      <button onClick={handleLogin} className="button">
        Login
      </button>
      <button onClick={handleSignup} className="button2">
          Signup
        </button>
      </div>
    </div>
  );
};

export default Login;
