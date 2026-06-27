import "../styles/Login.css";
import { useState } from "react";
import logo from "../assets/AppLogo.jpeg";
import React from "react";
import {useNavigate} from "react-router-dom";

function Login() {

  const [data,setData] = useState({
    email:"",
    password:""
  });

  const navigate=useNavigate();

  const handleChange=(e)=>{
    setData({...data,[e.target.name]:e.target.value});
  };

  const handleSubmit=(e)=>{
    e.preventDefault();
    console.log(data);
    navigate("/dashboard");
  };

return (

<div className="login-container">

<div className="login-header">
<img src={logo} alt="Career Metrics Logo" className="login-logo"/>
<h2 className="login-title">Career Metrics</h2>
</div>

<p className="login-subtitle">Login to your dashboard</p>

<form className="login-form" onSubmit={handleSubmit}>

<input
className="login-input"
type="email"
placeholder="Email"
name="email"
onChange={handleChange}
value={data.email}
/>

<input
className="login-input"
type="password"
placeholder="Password"
name="password"
onChange={handleChange}
value={data.password}
/>

<button className="login-button" type="submit">
Login
</button>

<p className="login-footer">
Don't have an account? <span>Register</span>
</p>

</form>

</div>

);

}

export default Login;