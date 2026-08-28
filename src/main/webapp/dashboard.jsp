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
<title>Dashboard - Sunrise Dental Clinic</title>

<style>

* {
    box-sizing: border-box;
}

body{
    font-family:'Segoe UI', Arial, sans-serif;
    background: linear-gradient(135deg, #0f766e 0%, #134e4a 100%);
    margin:0;
    min-height:100vh;
    padding:1px;
}

.container{
    width:700px;
    margin:60px auto;
    background:white;
    padding:35px 30px;
    border-radius:14px;
    box-shadow:0 10px 30px rgba(0,0,0,.25);
    border-top:5px solid #14b8a6;
    text-align:center;
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

h1{
    color:#0f766e;
    margin:0 0 5px 0;
    font-size:26px;
}

h2{
    color:#333;
    font-size:19px;
    font-weight:500;
    margin-top:5px;
}

p{
    font-size:15px;
    color:#374151;
}

.role-badge{
    display:inline-block;
    background:#e6fbf8;
    color:#0f766e;
    padding:4px 14px;
    border-radius:20px;
    font-weight:600;
    font-size:13px;
}

hr{
    border:none;
    border-top:1px solid #e5e7eb;
    margin:20px 0;
}

.btn{
    width:240px;
    padding:15px;
    margin:8px;
    font-size:15px;
    font-weight:600;
    letter-spacing:.3px;
    border:none;
    border-radius:8px;
    cursor:pointer;
    color:white;
    transition:transform .1s, opacity .2s;
}

.appointment{
    background:#0f766e;
}

.billing{
    background:#2563eb;
}

.help{
    background:#d97706;
}

.report{
    background:#0891b2;
}

.logout{
    background:#dc2626;
}

.btn:hover{
    opacity:0.88;
}

.btn:active{
    transform:scale(0.98);
}

.footer-note{
    text-align:center;
    color:#9ca3af;
    font-size:11px;
    margin-top:25px;
}

</style>

</head>

<body>

<div class="container">

<div class="logo-badge">🦷</div>

<h1>Sunrise Dental Clinic</h1>

<h2>Welcome, <%= user.getUsername() %></h2>

<p><b>Role :</b> <span class="role-badge"><%= user.getRole() %></span></p>

<hr><br>

<button class="btn appointment"
onclick="location.href='appointment.jsp'">
Appointment Management
</button>

<br>

<button class="btn billing"
onclick="location.href='billing.jsp'">
Billing Management
</button>

<br>

<button class="btn help"
onclick="location.href='help.jsp'">
Help
</button>

<br>

<button class="btn report"
onclick="location.href='Report'">
Appointments Report
</button>

<br>

<button class="btn logout"
        onclick="location.href='logout.jsp'">
    Logout
</button>

<p class="footer-note">Sunrise Dental Clinic &mdash; Patient Management System</p>

</div>

</body>
</html>
