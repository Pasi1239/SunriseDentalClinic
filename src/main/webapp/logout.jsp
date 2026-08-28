<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    session.invalidate();   // Destroy current session
    response.sendRedirect("login.jsp");   // Go to Login page
%>