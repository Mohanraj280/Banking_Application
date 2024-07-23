<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Form</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <script>
        function validateForm() {
            var username = document.forms["loginForm"]["user"].value;
            var password = document.forms["loginForm"]["pass"].value;
            if (username == "" || password == "") {
                alert("Both fields must be filled out");
                return false;
            }
            return true;
        }

        function showForm(formId) {
            document.getElementById('adminLoginForm').style.display = 'none';
            document.getElementById('customerLoginForm').style.display = 'none';
            document.getElementById(formId).style.display = 'block';
        }
    </script>
    
    <style>
		   .background {
			position: fixed;
			top: 0;
			left: 0;
			width: 100%;
			height: 100%;
			background: url('https://static.vecteezy.com/system/resources/previews/010/518/840/original/digital-finance-and-banking-service-in-futuristic-background-bank-building-with-online-payment-transaction-secure-money-and-financial-innovation-technology-vector.jpg') no-repeat
				center center fixed;
			background-size: cover;
			filter: blur(3px);
			z-index: -1;
		}
    
        body {
            background-size: cover;
            background: linear-gradient(135deg, #036cb1, #0d0011);
            
        }

		body, html {
			margin: 0;
			padding: 0;
			width: 100%;
			height: 100%;
			display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            font-family:cursive;
            color: #fff;
			font-family: 'Arial', sans-serif;
		}
        .login-container {
            width: 100%;
            max-width: 400px;
            padding: 30px;
            background: rgba(255, 255, 255,0.1);
            border-radius: 10px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
            animation: fadeIn 1s ease-in-out;
            border : 2px solid white;
            backdrop-filter: blue(10px);
        }

        @keyframes fadeIn {
            from {
                opacity: 0;
                transform: translateY(-20px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        .login-container legend {
            margin-bottom: 20px;
            color:black;
        }

        .login-container .form-group label {
            font-weight: bold;
            color: black;
        }

        .login-container input {
            margin-bottom: 10px;
            background: rgba(255, 255, 25, 0.1);
            border: none;
            border-radius: 5px;
            color: #fff;
        }

        .login-container .btn {
            margin-top: 20px;
            border: none;
            transition: background 0.3s ease;
        }

        .login-container .btn:hover {
            background: #6a11cb;
        }

        .btn-toggle {
            margin: 5px;
            background: rgba(0, 0, 0, 0.1);
            color: #fff;
            border: none;
            transition: background 0.3s ease;
        }


        .input-group-text {
            background: rgba(0, 0, 0, 0.1);
            border: none;
            color: #fff;
        }
    </style>
</head>
<body>
<div class="background">
</div>

    <div class="login-container">
        <div class="text-center mb-4">
            <button class="btn btn-info btn-toggle" onclick="showForm('adminLoginForm')">Admin Login</button>
            <button class="btn btn-success btn-toggle" onclick="showForm('customerLoginForm')">Customer Login</button>
        </div>
        
        <!-- Admin Login Form -->
        <form id="adminLoginForm" name="loginForm" action="getMohan" method="post" onsubmit="return validateForm()" style="display: none;">
            <fieldset>
                <legend class="text-center"><h2 class="text-light">Admin Login<hr></h2></legend>
                <div class="form-group">
                    <label for="adminUsername" class="text-light">Username</label>
                    <div class="input-group">
                     <input type="text" name="user" id="adminUsername" class="form-control" placeholder="Enter your username">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fas fa-user"></i></span>
                        </div>
                       
                    </div>
                </div>
                <div class="form-group">
                    <label for="adminPassword" class="text-light">Password</label>
                    <div class="input-group">
                         <input type="password" name="pass" id="adminPassword" class="form-control" placeholder="Enter your password">
                  
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fas fa-lock"></i></span>
                        </div>
                     </div>
                </div>
                <button type="submit" class="btn btn-primary btn-block">Login</button>
            </fieldset>
        </form>

        <!-- Customer Login Form -->
        <form id="customerLoginForm" name="loginForm" action="getCustomer" method="post" style="display: none;">
            <fieldset>
                <legend class="text-center"><h2 class="text-light">Customer Login<hr></h2></legend>
                <div class="form-group">
                    <label for="customerUsername" class="text-light">Account Number</label>
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fas fa-user"></i></span>
                        </div>
                        <input type="text" name="accnum" id="customerUsername" class="form-control" placeholder="Enter your Account Number" required>
                    </div>
                </div>
                <div class="form-group">
                    <label for="customerPassword" class="text-light">Password</label>
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fas fa-lock"></i></span>
                        </div>
                        <input type="password" name="pass" id="customerPassword" class="form-control" placeholder="Enter your password" required>
                    </div>
                </div>
                <button type="submit" class="btn btn-info btn-block">Login</button>
            </fieldset>
        </form>
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script>
        
        showForm('adminLoginForm');
    </script>
</body>
</html>
