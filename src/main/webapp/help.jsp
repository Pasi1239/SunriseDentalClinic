<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.dental.model.User" %>

<%
User user = (User) session.getAttribute("user");

if(user == null){
    response.sendRedirect("login.jsp");
    return;
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Help - Sunrise Dental Clinic</title>

<style>

*{
    box-sizing:border-box;
}

body{
    font-family:'Segoe UI',Arial,sans-serif;
    background: linear-gradient(135deg, #0f766e 0%, #134e4a 100%);
    min-height:100vh;
    margin:0;
    padding:1px;
}

.container{
    width:800px;
    margin:40px auto;
    background:white;
    padding:35px;
    border-radius:14px;
    box-shadow:0 10px 30px rgba(0,0,0,.25);
    border-top:5px solid #14b8a6;
}

.logo-badge{
    width:55px;
    height:55px;
    background:#e6fbf8;
    border-radius:50%;
    margin:0 auto 12px auto;
    display:flex;
    align-items:center;
    justify-content:center;
    font-size:26px;
}

h2{
    color:#0f766e;
    text-align:center;
    margin-bottom:5px;
}

.subtitle{
    text-align:center;
    color:#6b7280;
    font-size:13px;
    margin:0 0 10px 0;
}

hr{
    border:none;
    border-top:1px solid #e5e7eb;
    margin:20px 0;
}

ol{
    font-size:16px;
    line-height:1.9;
    color:#374151;
    padding-left:25px;
}

ol li{
    margin-bottom:6px;
}

ol li::marker{
    color:#0f766e;
    font-weight:700;
}

ol b{
    color:#0f766e;
}

.button-area{
    text-align:center;
    margin-top:30px;
}

button{
    padding:12px 25px;
    background:#0f766e;
    color:white;
    border:none;
    border-radius:8px;
    cursor:pointer;
    font-size:15px;
    font-weight:600;
    transition:background .2s, transform .1s;
}

button:hover{
    background:#0c5c55;
}

button:active{
    transform:scale(0.98);
}

</style>

</head>

<body>

<div class="container">

<div class="logo-badge">🦷</div>

<h2>Sunrise Dental Clinic - Help</h2>
<p class="subtitle">Step-by-step guide for staff</p>

<hr>

<ol>

<li>Login using your username and password.</li>

<li>Click <b>Appointment Management</b> to register a new appointment.</li>

<li>Enter Appointment ID and click <b>Search</b> to view appointment details.</li>

<li>Update or Delete appointments when necessary.</li>

<li>Open the <b>Billing</b> page to calculate and save patient bills.</li>

<li>Search bills using the Bill ID.</li>

<li>Click <b>Print Bill</b> to print the patient's receipt.</li>

<li>Click <b>Dashboard</b> to return to the main menu.</li>

<li>Click <b>Logout</b> when you finish using the system.</li>

</ol>

<div class="button-area">

<button type="button"
onclick="location.href='dashboard.jsp'">

← Back to Dashboard

</button>

</div>

</div>

</body>
</html>
