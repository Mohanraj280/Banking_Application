<%@page import="com.mohan.model.Customer"%>
<%@page import="com.mohan.model.DAO.CustomerDAO"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Customer</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(to right, #6a11cb, #2575fc);
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            font-family: 'Roboto', sans-serif;
        }
        .update-container {
            width: 100%;
            max-width: 900px;
            padding: 30px;
            border : 2px solid white;
            backdrop-filter: blue(10px);
             background:rgba(255,255,255,0.1);
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
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
        .update-container h2 {
            margin-bottom: 20px;
            font-size: 32px;
            font-family: 'Georgia', serif;
            color: #6a11cb;
        }
        .form-group label {
            font-size: 18px;
            font-weight: bold;
            color: #fff;
        }
        .form-control {
            font-size: 16px;
        }
        .btn-primary {
            background-color: #6a11cb;
            border: none;
            font-size: 18px;
        }
        .btn-primary:hover {
            background-color: #5a0fbf;
        }
    </style>
</head>
<body>
<div class="background">
</div>
    <div class="container">
        <div class="update-container">
            <h2 class="d-flex justify-content-between align-items-center ">
			  <span >Update Customer</span>
			  <span><a class="nav-link" href="AdminLanding.jsp">Back to Admin Page</a></span>
			  </h2> <%
                String accountNumber = request.getParameter("accountNumber");
                CustomerDAO dao = new CustomerDAO();
                Customer customer = dao.getCustomerByAccountNumber(accountNumber);
            %>
            <form action="UpdateCustomerServlet" method="post">
                <input type="hidden" name="accountNumber" value="<%= customer.getAccountNumber() %>" />
                <div class="form-row">
                    <div class="form-group col-md-6 text-light">
                        <label for="fullName">Full Name</label>
                        <input type="text" class="form-control" id="fullName" name="fullName" value="<%= customer.getFullName() %>" required>
                    </div>
                    <div class="form-group col-md-6 text-light">
                        <label for="address">Address</label>
                        <textarea class="form-control" id="address" name="address" rows="1" required><%= customer.getAddress() %></textarea>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6 text-light">
                        <label for="mobileNo">Mobile No</label>
                        <input type="text" class="form-control" id="mobileNo" name="mobileNo" value="<%= customer.getMobileNo() %>" required>
                    </div>
                    <div class="form-group col-md-6 text-light">
                        <label for="email">Email ID</label>
                        <input type="email" class="form-control" id="email" name="email" value="<%= customer.getEmail() %>" required>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6 text-light">
                        <label for="dob">Date of Birth</label>
                        <input type="date" class="form-control" id="dob" name="dob" value="<%= customer.getDob() %>" required>
                    </div>
                    <div class="form-group col-md-6 text-light">
                        <label for="idProof">ID Proof</label>
                        <input type="text" class="form-control" id="idProof" name="idProof" value="<%= customer.getIdProof() %>" required>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6 text-light">
                        <label for="aadharNo">ID Number</label>
                        <input type="text" class="form-control" id="aadharNo" name="aadharNo" value="<%= customer.getAadharNo() %>" required>
                    </div>
                    <div class="form-group col-md-6 text-light">
                        <label>Gender</label><br>
                        <div class="form-check form-check-inline text-light">
                            <input class="form-check-input" type="radio" name="gender" id="genderMale" value="Male" <%= customer.getGender().equals("Male") ? "checked" : "" %> required>
                            <label class="form-check-label" for="genderMale">Male</label>
                        </div>
                        <div class="form-check form-check-inline text-light">
                            <input class="form-check-input" type="radio" name="gender" id="genderFemale" value="Female" <%= customer.getGender().equals("Female") ? "checked" : "" %> required>
                            <label class="form-check-label" for="genderFemale">Female</label>
                        </div>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary btn-block">Update</button>
            </form>
        </div>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
