<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(to right, #ff5f6d, #ffc371);
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .error-container {
            max-width: 500px;
            padding: 30px;
            background: #fff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        .error-container h2 {
            margin-bottom: 20px;
            color: #dc3545;
        }
        .error-container p {
            margin-bottom: 20px;
            color: #6c757d;
        }
        .btn-primary {
            background-color: #dc3545;
            border: none;
        }
        .btn-primary:hover {
            background-color: #ff5f6d;
        }
    </style>
</head>
<body>
    <div class="error-container">
        <h2>Error</h2>
        <p><%
            String errorMessage = (String) request.getAttribute("error");
            if (errorMessage != null) {
                out.println(errorMessage);
            } else {
                out.println("An error occurred. Please try again.");
            }
        %></p>
        <input type="hidden" id="remainingTime" value="<%= request.getAttribute("remainingTime") != null ? request.getAttribute("remainingTime") : 0 %>">
        <a id="backButton" href="index.jsp" class="btn btn-primary">Go back to Login</a>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script>
        (function() {
            var remainingTime = parseInt(document.getElementById('remainingTime').value);
            var backButton = document.getElementById('backButton');

            if (remainingTime > 0) {
                backButton.style.display = 'none';

                var interval = setInterval(function() {
                    remainingTime--;
                    if (remainingTime <= 0) {
                        clearInterval(interval);
                        backButton.style.display = 'block';
                    }
                }, 1000);
            }
        })();
    </script>
</body>
</html>
