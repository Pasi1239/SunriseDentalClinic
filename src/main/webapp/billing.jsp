<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="com.dental.model.Appointment"%>
<%@ page import="com.dental.model.Bill"%>

<%@ page import="com.dental.model.User"%>

<%
User loggedInUser = (User) session.getAttribute("user");

if (loggedInUser == null) {
    response.sendRedirect("login.jsp");
    return;
}
%>


<%
Appointment appointment =
(Appointment)request.getAttribute("appointment");

Bill bill =
(Bill)request.getAttribute("bill");

String success=request.getParameter("success");
String error=request.getParameter("error");
%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>Sunrise Dental Clinic - Billing</title>

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

width:900px;
margin:30px auto;
background:#fff;
padding:30px;
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
margin-bottom:15px;
border-left:5px solid #14b8a6;
padding-left:12px;

}

table{

width:100%;
border-collapse:collapse;

}

td{

padding:10px;

}

table[border="1"] td, table[border="1"] th{

border:1px solid #e5e7eb;
padding:10px;
text-align:left;

}

table[border="1"] th{

background:#e6fbf8;
color:#0f766e;
width:35%;

}

input{

width:100%;
padding:10px;
border:1px solid #d1d5db;
border-radius:8px;
background:#f9fafb;
transition:border-color .2s, box-shadow .2s;

}

input:focus{

outline:none;
border-color:#14b8a6;
box-shadow:0 0 0 3px rgba(20,184,166,.15);
background:#ffffff;

}

input[readonly]{

background:#f3f4f6;
color:#6b7280;

}

button{

padding:12px 18px;
border:none;
border-radius:8px;
cursor:pointer;
color:white;
font-weight:600;
transition:opacity .2s, transform .1s;

}

.search{

background:#0f766e;

}

.calculate{

background:#d97706;

}

.save{

background:#16a34a;

}

.print{

background:#7c3aed;

}

.home{

background:#6b7280;

}

button:hover{

opacity:.88;

}

button:active{

transform:scale(0.98);

}

.success{

background:#d1fae5;
padding:10px;
margin-bottom:15px;
color:#065f46;
border-radius:8px;
border:1px solid #a7f3d0;

}

.error{

background:#fef2f2;
padding:10px;
margin-bottom:15px;
color:#991b1b;
border-radius:8px;
border:1px solid #fecaca;

}


.dashboard{
    background:#6b7280;
    color:white;
    padding:10px 20px;
    border:none;
    border-radius:8px;
    cursor:pointer;
    font-weight:600;
}

.dashboard:hover{
    background:#4b5563;
}

hr{

border:none;
border-top:1px solid #e5e7eb;

}

</style>

<script>

function calculateTotal(){

let consultation =
parseFloat(document.getElementById("consultationFee").value)||0;

let treatment =
parseFloat(document.getElementById("treatmentFee").value)||0;

document.getElementById("totalAmount").value =
consultation+treatment;

}

function printBill(){

window.print();

}

</script>

</head>

<body>

<div class="container">

<div class="page-header">
<div class="logo-badge">🦷</div>
</div>

<div style="text-align:right; margin-bottom:20px;">

    <button
        type="button"
        class="dashboard"
        onclick="location.href='dashboard.jsp'">

        ← Dashboard

    </button>

</div>



<h2>Search Appointment</h2>

<form action="Billing" method="get">

<table>

<tr>

<td width="30%">

Appointment ID

</td>

<td>

<input
type="number"
name="appointmentId"
placeholder="Enter Appointment ID"
value="<%=appointment!=null?appointment.getAppointmentId():""%>"
required>

</td>

<td width="170">

<button
class="search"
type="submit">

Search

</button>

</td>

</tr>

</table>

</form>

<hr><br>

<%
if(success!=null){
%>

<div class="success">

Bill saved successfully.

</div>

<%
}
%>

<%
if(error!=null){
%>

<div class="error">

Unable to save bill.

</div>

<%
}
%>

<h2>Create Bill</h2>

<form action="Billing" method="post">

<table>

<tr>

<td width="30%">Appointment ID</td>

<td>

<input
type="number"
name="appointmentId"
value="<%=appointment!=null?appointment.getAppointmentId():""%>"
readonly>

</td>

</tr>

<tr>

<td>Patient Name</td>

<td>

<input
type="text"
name="patientName"
value="<%=appointment!=null?appointment.getPatientName():""%>"
readonly>

</td>

</tr>

<tr>

<td>Dentist Name</td>

<td>

<input
type="text"
name="dentistName"
value="<%=appointment!=null?appointment.getDentistName():""%>"
readonly>

</td>

</tr>

<tr>

<td>Treatment Type</td>

<td>

<input
type="text"
name="treatmentType"
value="<%=appointment!=null?appointment.getTreatmentType():""%>"
readonly>

</td>

</tr>

<tr>

<td>Consultation Fee</td>

<td>

<input
type="number"
id="consultationFee"
name="consultationFee"
onkeyup="calculateTotal()"
required>

</td>

</tr>

<tr>

<td>Treatment Fee</td>

<td>

<input
type="number"
id="treatmentFee"
name="treatmentFee"
onkeyup="calculateTotal()"
required>

</td>

</tr>

<tr>

<td>Total Amount</td>

<td>

<input
type="number"
id="totalAmount"
name="totalAmount"
readonly>

</td>

</tr>

<tr>

<td>Bill Date</td>

<td>

<input
type="date"
name="billDate"
value="<%=java.time.LocalDate.now()%>"
required>

</td>

</tr>

<tr>

<td colspan="2" align="center">

<button
type="button"
class="calculate"
onclick="calculateTotal()">

Calculate Total

</button>

<button
type="submit"
class="save">

Save Bill

</button>

</td>

</tr>

</table>

</form>

<br>
<hr>
<br>

<h2>Search Bill</h2>

<form action="Billing" method="get">

<table>

<tr>

<td width="30%">Bill ID</td>

<td>

<input
type="number"
name="billId"
placeholder="Enter Bill ID"
required>

</td>

<td width="170">

<button
type="submit"
class="search">

Search Bill

</button>

</td>

</tr>

</table>

</form>

<%
if(bill!=null){
%>

<br>
<hr>
<br>

<h2>Bill Details</h2>

<table border="1">

<tr>
<th>Bill ID</th>
<td><%=bill.getBillId()%></td>
</tr>

<tr>
<th>Appointment ID</th>
<td><%=bill.getAppointmentId()%></td>
</tr>

<tr>
<th>Patient Name</th>
<td><%=bill.getPatientName()%></td>
</tr>

<tr>
<th>Dentist Name</th>
<td><%=bill.getDentistName()%></td>
</tr>

<tr>
<th>Treatment Type</th>
<td><%=bill.getTreatmentType()%></td>
</tr>

<tr>
<th>Consultation Fee</th>
<td><%=bill.getConsultationFee()%></td>
</tr>

<tr>
<th>Treatment Fee</th>
<td><%=bill.getTreatmentFee()%></td>
</tr>

<tr>
<th>Total Amount</th>
<td><%=bill.getTotalAmount()%></td>
</tr>

<tr>
<th>Bill Date</th>
<td><%=bill.getBillDate()%></td>
</tr>

</table>

<br>

<div style="text-align:center;">

<button
type="button"
class="print"
onclick="printBill()">

Print Bill

</button>

<button
type="button"
class="home"
onclick="location.href='dashboard.jsp'">

Home

</button>

</div>

<%
}
%>



</div>


</body>

</html>
