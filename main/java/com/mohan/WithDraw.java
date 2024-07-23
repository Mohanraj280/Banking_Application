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
import javax.servlet.http.HttpSession;

import com.mohan.CustomerService.CustomerService;
import com.mohan.CustomerServiceDAO.CustomerServiceDAO;
import com.mohan.model.Customer;
import com.mohan.model.DAO.CustomerDAO;

/**
 * Servlet implementation class WithDraw
 */
public class WithDraw extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	    String accNumber = request.getParameter("accNumber");
	    String password = request.getParameter("password");
	    String amountStr = request.getParameter("amountwithdraw");
	    
	    String amounttoDepositStr = request.getParameter("amountDeposit");
	    CustomerServiceDAO customer=new CustomerServiceDAO();
	    if (accNumber == null || accNumber.isEmpty() || password == null || password.isEmpty()) {
	        request.setAttribute("error", "Username and Password cannot be empty");
	        RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
	        rd.forward(request, response);
	        return;
	    }

	    CustomerDAO dao = new CustomerDAO();
	    Customer cus = dao.getByAccountNumber(accNumber);
	    if (cus == null || !cus.getPassword().equals(password)) {
	        request.setAttribute("error", "Invalid username or password");
	        RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
	        rd.forward(request, response);
	        return;
	    }

	    BigDecimal initialBalance = cus.getInitialBalance();
	    BigDecimal amount = BigDecimal.ZERO;
	    BigDecimal amounttoDeposit = BigDecimal.ZERO;

	    if (amountStr != null && !amountStr.isEmpty()) {
	        amount = new BigDecimal(amountStr);
	       
	        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
	            request.setAttribute("error", "Invalid withdrawal amount");
	            RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
	            rd.forward(request, response);
	            return;
	        }
	       
	        if (initialBalance.compareTo(amount) < 0) {
	            request.setAttribute("error", "Insufficient balance");
	            RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
	            rd.forward(request, response);
	            return;
	        }
	        String transactionId = generateTransactionId();
	        String action="WithDraw";

	        BigDecimal balance = new BigDecimal(amountStr);
	        CustomerService c = new CustomerService();
            c.setTransactionId(transactionId);
            c.setAccountNumber(accNumber);
            c.setActions(action);
            c.setTransactionDate(new Timestamp(System.currentTimeMillis()));
            c.setAmount(balance);
            try {
				customer.storeTransaction(c);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        initialBalance = initialBalance.subtract(amount);
	    }

	    if (amounttoDepositStr != null && !amounttoDepositStr.isEmpty()) {
	        amounttoDeposit = new BigDecimal(amounttoDepositStr);
	       
	        if (amounttoDeposit.compareTo(BigDecimal.ZERO) <= 0) {
	            request.setAttribute("error", "Invalid deposit amount");
	            RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
	            rd.forward(request, response);
	            return;
	        }
	        String transactionId = generateTransactionId();
	        String action="Deposit";

	        BigDecimal balance = new BigDecimal(amounttoDepositStr);
	        CustomerService c = new CustomerService();
	        
            c.setTransactionId(transactionId);
            c.setAccountNumber(accNumber);
            c.setActions(action);
            c.setTransactionDate(new Timestamp(System.currentTimeMillis()));
            c.setAmount(balance);
            try {
				customer.storeTransaction(c);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        initialBalance = initialBalance.add(amounttoDeposit);
	    }

	 
	    cus.setInitialBalance(initialBalance);
	    dao.updateCustomer(cus);

	    // Set success message and forward to success page
	    HttpSession session = request.getSession();
	    session.setAttribute("success", "Transaction successful. New Balance: " + cus.getInitialBalance());
	    RequestDispatcher rd = request.getRequestDispatcher("success.jsp");
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
