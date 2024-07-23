package com.mohan;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mohan.CustomerService.CustomerService;
import com.mohan.CustomerServiceDAO.CustomerServiceDAO;
import com.mohan.model.Customer;
import com.mohan.model.DAO.CustomerDAO;

/**
 * Servlet implementation class BalanceServlet
 */
public class BalanceServlet extends HttpServlet {
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    String accNumber = request.getParameter("accNumber");
		    String password = request.getParameter("password");

		    // Validate inputs
		    if (accNumber == null || accNumber.isEmpty() || password == null || password.isEmpty()) {
		        request.setAttribute("error", "Account Number and Password cannot be empty");
		        RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
		        rd.forward(request, response);
		        return;
		    }

		    // Retrieve customer from DAO
		    CustomerDAO dao = new CustomerDAO();
		    Customer cus = dao.getByAccountNumber(accNumber);
		    
		    // Validate customer existence and password
		    if (cus == null || !cus.getPassword().equals(password)) {
		        request.setAttribute("error", "Invalid Account Number or Password");
		        RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
		        rd.forward(request, response);
		        return;
		    }

		    // Retrieve current balance
		    BigDecimal currentBalance = cus.getInitialBalance();
		    request.setAttribute("balance", currentBalance.toString());
		   
		    String transactionId = generateTransactionId();
		    String action = "Check Balance";
		    BigDecimal value=new BigDecimal("0.0");
		    CustomerService c = new CustomerService();
		    c.setTransactionId(transactionId);
		    c.setAccountNumber(accNumber);
		    c.setActions(action);
		    c.setTransactionDate(new Timestamp(System.currentTimeMillis())); // Set current date
		    c.setAmount(value); // Assuming null because "Check Balance" doesn't involve an amount

		    // Store transaction
		    CustomerServiceDAO customerDao = new CustomerServiceDAO();
		    try {
		    	System.out.println("Check Balance");
		        customerDao.storeTransaction(c);
		    } catch (ClassNotFoundException e) {
		        e.printStackTrace();
		        // Handle ClassNotFoundException appropriately, perhaps redirect to an error page
		    }

		    // Forward to Balance.jsp to display balance
		    RequestDispatcher rd = request.getRequestDispatcher("Balance.jsp");
		    rd.forward(request, response);
		}

		 private String generateTransactionId() {
		        Random random = new Random();
		        StringBuilder sb = new StringBuilder(10);
		        for (int i = 0; i < 10; i++) {
		            sb.append(random.nextInt(1,9));
		        }
		        return sb.toString();
		    }

}
