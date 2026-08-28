<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.dental.model.User"%>
<%@ page import="com.dental.model.Appointment"%>
<%@ page import="java.util.List"%>

<%
User loggedInUser = (User) session.getAttribute("user");

if (loggedInUser == null) {
    response.sendRedirect("login.jsp");
    return;
}
%>

<%
List<Appointment> appointmentList = (List<Appointment>) request.getAttribute("appointmentList");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reports - Sunrise Dental Clinic</title>

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
    width:950px;
    margin:40px auto;
    background:white;
    padding:35px;
    border-radius:14px;
    box-shadow:0 10px 30px rgba(0,0,0,.25);
    border-top:5px solid #14b8a6;
}

.page-header{
    text-align:center;
    margin-bottom:10px;
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
    text-align:center;
    margin-bottom:5px;
}

.subtitle{
    text-align:center;
    color:#6b7280;
    font-size:13px;
    margin:0 0 20px 0;
}

table{
    width:100%;
    border-collapse:collapse;
    margin-top:15px;
}

th, td{
    border:1px solid #e5e7eb;
    padding:10px;
    text-align:left;
    font-size:13px;
}

th{
    background:#0f766e;
    color:white;
    font-weight:600;
}

tr:nth-child(even){
    background:#f9fafb;
}

tr:hover{
    background:#e6fbf8;
}

.empty-message{
    text-align:center;
    color:#6b7280;
    padding:30px;
    font-size:15px;
}

.summary-bar{
    background:#e6fbf8;
    color:#0f766e;
    padding:12px 15px;
    border-radius:8px;
    margin-top:15px;
    font-weight:600;
    text-align:center;
}

.button-area{
    text-align:center;
    margin-top:25px;
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
    margin:0 5px;
    transition:background .2s, transform .1s;
}

button:hover{
    background:#0c5c55;
}

.print-btn{
    background:#7c3aed;
}

.print-btn:hover{
    background:#6d28d9;
}

</style>

<script>
function printReport(){
    window.print();
}
</script>

</head>

<body>

<div class="container">

<div class="page-header">
<div class="logo-badge">🦷</div>
</div>

<h2>All Appointments Report</h2>
<p class="subtitle">Complete list of registered patient appointments</p>

<%
if (appointmentList != null && !appointmentList.isEmpty()) {
%>

<table>
<tr>
<th>Appointment ID</th>
<th>Patient Name</th>
<th>Address</th>
<th>Contact</th>
<th>Dentist</th>
<th>Treatment Type</th>
<th>Date</th>
<th>Time</th>
</tr>

<%
for (Appointment a : appointmentList) {
%>
<tr>
<td><%= a.getAppointmentId() %></td>
<td><%= a.getPatientName() %></td>
<td><%= a.getAddress() %></td>
<td><%= a.getContact() %></td>
<td><%= a.getDentistName() %></td>
<td><%= a.getTreatmentType() %></td>
<td><%= a.getAppointmentDate() %></td>
<td><%= a.getAppointmentTime() %></td>
</tr>
<%
}
%>

</table>

<div class="summary-bar">
Total Appointments: <%= appointmentList.size() %>
</div>

<%
} else {
%>

<div class="empty-message">
No appointments found in the system.
</div>

<%
}
%>

<div class="button-area">

<button type="button" class="print-btn" onclick="printReport()">
Print Report
</button>

<button type="button" onclick="location.href='dashboard.jsp'">
← Back to Dashboard
</button>

</div>

</div>

</body>
</html>
