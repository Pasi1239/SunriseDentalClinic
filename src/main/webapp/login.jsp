<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sunrise Dental Clinic - Login</title>

<style>

* {
    box-sizing: border-box;
}

body{
    margin:0;
    padding:0;
    min-height:100vh;
    background: linear-gradient(135deg, #0f766e 0%, #134e4a 100%);
    font-family:'Segoe UI', Arial, Helvetica, sans-serif;
    display:flex;
    align-items:center;
    justify-content:center;
}

.container{

    width:380px;
    margin:60px auto;
    background:#ffffff;
    padding:35px 30px;
    border-radius:14px;
    box-shadow:0 10px 30px rgba(0,0,0,.25);
    border-top:5px solid #14b8a6;

}

.logo-badge{
    width:60px;
    height:60px;
    background:#e6fbf8;
    border-radius:50%;
    margin:0 auto 15px auto;
    display:flex;
    align-items:center;
    justify-content:center;
    font-size:28px;
}

h2{

    text-align:center;
    color:#0f766e;
    margin:0 0 5px 0;
    font-size:22px;

}

.subtitle{
    text-align:center;
    color:#6b7280;
    font-size:13px;
    margin:0 0 10px 0;
}

input{

    width:100%;
    padding:12px 14px;
    margin-top:15px;
    border:1px solid #d1d5db;
    border-radius:8px;
    font-size:14px;
    background:#f9fafb;
    transition:border-color .2s, box-shadow .2s;

}

input:focus{
    outline:none;
    border-color:#14b8a6;
    box-shadow:0 0 0 3px rgba(20,184,166,.15);
    background:#ffffff;
}

button{

    width:100%;
    padding:12px;
    margin-top:22px;
    background:#0f766e;
    color:white;
    border:none;
    border-radius:8px;
    font-size:16px;
    font-weight:600;
    letter-spacing:.3px;
    cursor:pointer;
    transition:background .2s, transform .1s;

}

button:hover{

    background:#0c5c55;

}

button:active{
    transform:scale(0.98);
}

.error{

    color:#dc2626;
    text-align:center;
    margin-top:12px;
    background:#fef2f2;
    border:1px solid #fecaca;
    padding:8px;
    border-radius:6px;
    font-size:13px;

}

.footer-note{
    text-align:center;
    color:#9ca3af;
    font-size:11px;
    margin-top:20px;
}

</style>

</head>
<body>

<div class="container">

<div class="logo-badge">🦷</div>

<h2>Sunrise Dental Clinic</h2>
<p class="subtitle">Appointment &amp; Patient Management System</p>

<%
String error=request.getParameter("error");

if(error!=null){
%>

<p class="error"><%=error%></p>

<%
}
%>

<form action="login" method="post">

<input
type="text"
name="username"
placeholder="Enter Username"
required>

<input
type="password"
name="password"
placeholder="Enter Password"
required>

<button type="submit">

Login

</button>

</form>

<p class="footer-note">Authorized staff access only</p>

</div>

</body>
</html>
