<%@page import="com.mohan.model.Mohan"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor="cyan">
<h1>Admin DashBoard</h1>
	Hello <%
		Mohan m1= (Mohan)session.getAttribute("user");
		out.println(m1.getUsername());
	%>
	
	<a href="Customerregister.jsp">Customer Registration</a>
</body>
</html>