Banking Application
Overview
This Banking Application is designed to enhance customer experience with managing their bank accounts. It provides functionalities for both admin and customers, ensuring a seamless and secure banking experience.

**Features**
Admin Roles
Admin can login with username and password.
Admin can register a customer with the following details:
Full name
Address
Mobile No
Email id
Type of account – Either Saving Account or Current Account
Initial Balance (minimum 1000)
Date of Birth
ID proof
Customer registration generates an account number and a temporary password.
Admin can add, delete, modify, and view customer details (excluding password and balance).

**Customer Roles**
Customers are registered by the admin.
Customers receive their account number and temporary password from the admin.
Customers can set up a new password using their account number and temporary password.
Customers can log in using their account number and new password.
Customers can log out.
Customers can view their account details and balance on the dashboard.
Customers can view the last 10 transactions in increasing or decreasing order of date.
Customers can deposit money, increasing their balance, and the transaction is saved in the database.
Customers can withdraw money, decreasing their balance, and the transaction is saved in the database (balance cannot go below 0).
Customers can close their account after withdrawing all the money.
Bonus: Customers can download a PDF of the last 10 transactions.

BOM (Bill of Materials)
Software
Eclipse IDE
Apache Tomcat 9
MySQL Server
MySQL Workbench
Java JDK (latest version)
JDBC Connector for MySQL
PDF generation library (e.g., iText)

Installation
Setup MySQL Database

Install MySQL Server and MySQL Workbench.
Create a new database named banking.
Run the provided SQL script to create the necessary tables.
Configure Eclipse IDE

Install Eclipse IDE.
Install Apache Tomcat 9 in Eclipse.
Create a new Dynamic Web Project for the banking application.
Add Libraries

Add the JDBC connector library to the project.
Add the PDF generation library to the project.
Deploy the Application

Write and deploy the necessary servlets, JSPs, and Java classes for the application.
Ensure the application is connected to the MySQL database using JDBC.
Run the Application

Start the Tomcat server.
Access the application via the web browser.


Database Schema
Tables
Admin

id (Primary Key)
username
password
Customer

id (Primary Key)
full_name
address
mobile_no
email_id
account_type
initial_balance
dob
id_proof
account_no
password (hashed)
Transactions

id (Primary Key)
account_no
type (Deposit/Withdraw/Balance/Fund Transfer)
amount
date
