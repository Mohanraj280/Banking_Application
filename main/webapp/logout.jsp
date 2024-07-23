<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // Invalidate the session
    session.invalidate();
    
    // Redirect to login page
    response.sendRedirect("index.jsp");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Logout</title>
</head>
<body>
    <!-- Optionally, you can display a message or loading indicator here -->
    <h1>You have been logged out. Redirecting to login page...</h1>
</body>
</html>
