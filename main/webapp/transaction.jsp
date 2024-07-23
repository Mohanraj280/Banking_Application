<%@page import="com.mohan.CustomerService.CustomerService"%>
<%@page import="com.mohan.CustomerServiceDAO.CustomerServiceDAO"%>
<%@page import="com.mohan.model.Mohan"%>
<%@page import="com.mohan.model.Customer"%>
<%@page import="java.util.List"%>
<%@page import="com.mohan.model.DAO.CustomerDAO"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>View Transactions</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(to right, #6a11cb, #2575fc);
            min-height: 100vh;
            display: flex;
            flex-direction: column;
            font-family:monospace;
        }
        h1 {
            font-family: 'Georgia', serif;
            color: #fff;
        }
        .navbar {
            margin-bottom: 20px;
        }
        .navbar-brand, .nav-link {
            font-size: 24px;
          
        }
        .btn-custom {
            background-color: #007bff;
            color: white;
        }
        .btn-custom:hover {
            background-color: #0056b3;
            color: white;
        }
        .table-responsive {
            overflow-x: auto;
        }
        .table th, .table td {
            vertical-align: middle;
        }
        .container-fluid {
        border : 2px solid white;
            backdrop-filter: blue(10px);
             background:rgba(255,255,255,0.1);
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            padding: 30px;
          
        }
        .form-group label {
            font-size: 18px;
            font-weight: bold;
        }
        .form-control {
            font-size: 16px;
        }
        .btn-primary {
            background-color: #6a11cb;
            border: none;
            font-size: 18px;
        }
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
        .btn-primary:hover {
            background-color: #5a0fbf;
        }
    </style>
</head>
<body>
<div class="background">
</div>

<div class="container-fluid">
<%
  
    Customer customersDetails = ((Customer) session.getAttribute("user"));
    if (customersDetails == null) {
        response.sendRedirect("index.jsp"); 
        return; 
    }
    String customerAcc = customersDetails.getAccountNumber();
%>
    <h1 class="mt-4 text-center text-light ">View Transactions</h1>
     <h2 class="d-flex justify-content-between align-items-center ">
			<div class="text-right">
        <form id="downloadForm" action="DownloadPDFServlet" method="get">
                <input type="hidden" name="accountNumber" value="<%= customerAcc %>">
                <button type="submit" class="btn btn-primary btn-custom">Download Last 10 Transactions</button>
            </form>
    </div>
			  <span><a class="nav-link" href="CustomerDashBoard.jsp">Back</a></span>
			  </h2>
    
    <div class="table-responsive mt-4">
        <table class="table table-bordered table-striped table-hover mt-2">
            <thead class="thead-dark mt-1">
                <tr class="text-center mt-4 h3">
                    <th>Transaction Id</th>
                    <th>Account Number</th>
                    <th>Actions</th>
                    <th>Date</th>
                    <th>Amount</th>
                </tr>
            </thead>
            <tbody>
                <%
                String accountNumber =  ((Customer) session.getAttribute("user")).getAccountNumber();
                    CustomerServiceDAO dao = new CustomerServiceDAO();
                    List<CustomerService> customers = dao.getAllTransactions(accountNumber);
                    for (CustomerService customer : customers) {
                %>
                <tr class="text-center text-light fw-bold h4">
                    <td><%= customer.getTransactionId() %></td>
                    <td><%= customer.getAccountNumber() %></td>
                    <td><%= customer.getActions() %></td>
                    <td><%= customer.getTransactionDate() %></td>
                    <td><%= customer.getAmount() %></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>
</div>


<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
