<%@page import="com.mohan.model.Mohan"%>
<%@page import="java.util.List"%>
<%@page import="com.mohan.model.DAO.CustomerDAO"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // Check session for authentication
    Mohan adminUser = (Mohan) session.getAttribute("user");
    if (adminUser == null) {
        response.sendRedirect("index.jsp");
        return;// Redirect to login if not authenticated
    }
%><!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
    .background {
			position: fixed;
			top: 0;
			left: 0;
			width: 100%;
			height: 100%;
			background: url('https://static.vecteezy.com/system/resources/previews/026/610/002/original/interbank-currency-exchange-vector.jpg') no-repeat
				center center fixed;
			background-size: cover;
			filter: blur(6px);
			z-index: -1;
		}
        body {
            background: linear-gradient(to right, #6a11cb, #2575fc); 
            min-height: 100vh;
            display: flex;
            flex-direction: column;
            font-family:monospace;
            color: #fff; 
        }
        .header {
            margin-top: 65px;
            margin-bottom: 30px;
            text-align: center;
        }
        .registration-link {
            font-size: 18px;
            color: #fff;
            text-decoration: none; 
            margin-bottom: 10px;
            display: inline-block;
            border-bottom: 1px dashed #fff;
        }
        .registration-link:hover {
            color: #ccc; 
		
			
        }
        .search-form {
            margin-bottom: 20px;
            text-align: center;
        }
        .search-form input[type="text"] {
            width: 300px;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            margin-right: 10px;
        }
        .search-form button {
            padding: 10px 20px;
            border: none;
            background-color: #007bff;
            color: white;
            border-radius: 5px;
            cursor: pointer;
        }
        .search-form button:hover {
            background-color: #0056b3;
        }
        
        .back
        {
         border : 2px solid white;
         backdrop-filter: blue(10px);
        }
    </style>
</head>
<body>
<div class="background back">
</div>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">Admin Dashboard</a>
    <ul class="navbar-nav ml-auto">
        <li class="nav-item">
            <a class="nav-link" href="logout.jsp">Logout</a>
        </li>
    </ul>
</nav>

<div class="container">
    <div class="header mb-4" >
        <h1>Welcome, <%= adminUser.getUsername() %></h1>
        <a href="Customerregister.jsp" class="registration-link mt-5 nav-link"><h2>Register New Customer</h2></a>
    </div>

   
    <div class="header">
        <a href="DashBoard.jsp" class="registration-link mt-3 nav-link"><h2>View All Customers</h2></a>
    </div>
    
     <!-- Search Form -->
    <form class="search-form" method="post" action="ViewCustomerServlet">
        <input type="text" name="accnumber" placeholder="Enter Account Number" required>
        <button type="submit">Search</button>
    </form>
  
</div>

<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
