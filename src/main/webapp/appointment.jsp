<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="com.dental.model.Appointment"%>

<%@ page import="com.dental.model.User"%>

<%
User loggedInUser = (User) session.getAttribute("user");

if (loggedInUser == null) {
    response.sendRedirect("login.jsp");
    return;
}
%>

<%
Appointment appointment=(Appointment)request.getAttribute("appointment");

String success=request.getParameter("success");
String error=request.getParameter("error");
%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>Appointment Management</title>

<script>

<%
if(success!=null){
%>

alert("<%=success%>");

<%
}
%>

<%
if(error!=null){
%>

alert("<%=error%>");

<%
}
%>

</script>

<style>

*{
margin:0;
padding:0;
box-sizing:border-box;
font-family:'Segoe UI',Arial,sans-serif;
}

body{

background: linear-gradient(135deg, #0f766e 0%, #134e4a 100%);
min-height:100vh;
padding:1px;

}

.container{

width:750px;
margin:40px auto;
background:#fff;
border-radius:14px;
box-shadow:0 10px 30px rgba(0,0,0,.25);
padding:35px;
border-top:5px solid #14b8a6;

}

.page-header{

text-align:center;
margin-bottom:20px;

}

.logo-badge{
width:55px;
height:55px;
background:#e6fbf8;
border-radius:50%;
margin:0 auto 10px auto;
display:flex;
align-items:center;
justify-content:center;
font-size:26px;
}

h2{

color:#0f766e;
margin-bottom:20px;
border-left:5px solid #14b8a6;
padding-left:12px;

}

label{

font-weight:bold;
display:block;
margin-top:12px;
color:#374151;
font-size:14px;

}

input{

width:100%;
padding:12px;
margin-top:5px;
border:1px solid #d1d5db;
border-radius:8px;
font-size:15px;
background:#f9fafb;
transition:border-color .2s, box-shadow .2s;

}

input:focus{

border-color:#14b8a6;
outline:none;
box-shadow:0 0 0 3px rgba(20,184,166,.15);
background:#ffffff;

}

button{

width:100%;
padding:13px;
margin-top:20px;
border:none;
background:#0f766e;
color:white;
font-size:16px;
font-weight:600;
border-radius:8px;
cursor:pointer;
transition:background .2s, transform .1s;

}

button:hover{

background:#0c5c55;

}

button:active{

transform:scale(0.98);

}

button[value="update"]{

background:#2563eb;

}

button[value="update"]:hover{

background:#1d4ed8;

}

button[value="delete"]{

background:#dc2626;

}

button[value="delete"]:hover{

background:#b91c1c;

}

.dashboard{

background:#6b7280;
width:auto;
padding:10px 25px;

}

.dashboard:hover{

background:#4b5563;

}

.success{

background:#d1fae5;
color:#065f46;
padding:10px;
border-radius:8px;
margin-bottom:15px;
border:1px solid #a7f3d0;

}

.error{

background:#fef2f2;
color:#991b1b;
padding:10px;
border-radius:8px;
margin-bottom:15px;
border:1px solid #fecaca;

}

hr{

margin:30px 0;
border:none;
border-top:1px solid #e5e7eb;

}

</style>

</head>

<body>

<div class="container">

<div class="page-header">
<div class="logo-badge">🦷</div>
</div>

<h2>Search Appointment</h2>

<form action="Appointment" method="get">

<label>Appointment ID</label>

<input
type="number"
name="appointmentId"
placeholder="Enter Appointment ID"
required>

<button type="submit">

Search Appointment

</button>

</form>

<hr>

<h2>Appointment Form</h2>

<%
if(success!=null){
%>

<div class="success">

<%=success%>

</div>

<%
}
%>

<%
if(error!=null){
%>

<div class="error">

<%=error%>

</div>

<%
}
%>

<form action="Appointment" method="post">

<label>Appointment ID</label>

<input
type="number"
name="appointmentId"
value="<%=appointment!=null?appointment.getAppointmentId():""%>"
required>

<label>Patient Name</label>

<input
type="text"
name="patientName"
value="<%=appointment!=null?appointment.getPatientName():""%>"
required>

<label>Address</label>

<input
type="text"
name="address"
value="<%=appointment!=null?appointment.getAddress():""%>"
required>

<label>Contact Number</label>

<input
type="text"
name="contact"
value="<%=appointment!=null?appointment.getContact():""%>"
required>

<label>Dentist Name</label>

<input
type="text"
name="dentistName"
value="<%=appointment!=null?appointment.getDentistName():""%>"
required>

<label>Treatment Type</label>

<input
type="text"
name="treatmentType"
value="<%=appointment!=null?appointment.getTreatmentType():""%>"
required>

<label>Appointment Date</label>

<input
type="date"
name="appointmentDate"
value="<%=appointment!=null?appointment.getAppointmentDate():""%>"
required>

<label>Appointment Time</label>

<input
type="time"
name="appointmentTime"
value="<%=appointment!=null?appointment.getAppointmentTime():""%>"
required>


<button type="submit" name="action" value="save">
    Save Appointment
</button>

<button type="submit" name="action" value="update">
    Update Appointment
</button>

<button type="submit"
        name="action"
        value="delete"
        onclick="return confirm('Are you sure you want to delete this appointment?');">
    Delete Appointment
</button>

<br><br>

<div style="text-align:center;">

<button
type="button"
class="dashboard"
onclick="location.href='dashboard.jsp'">

← Back to Dashboard

</button>

</div>

</form>

</div>

</body>

</html>
