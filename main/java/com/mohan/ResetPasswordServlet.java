package com.mohan;

import java.io.IOException;
import java.math.BigDecimal;
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
 * Servlet implementation class ResetPasswordServlet
 */
public class ResetPasswordServlet extends HttpServlet {
	  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        
	        String newPassword = request.getParameter("password");
	        String confirmPassword = request.getParameter("confirmPassword");
	        String accountNumber = request.getParameter("accNumber");
	
	        if (newPassword.length() != 4 || confirmPassword.length() != 4) {
	            request.setAttribute("error", "Length of the password should be 4.");
	            RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
	            rd.forward(request, response);
	            return;
	        }
	        

	        if (!newPassword.equals(confirmPassword)) {
	            request.setAttribute("error", "New Password & Confirm Password is mismatched.");
	            RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
	            rd.forward(request, response);
	            return;
	        }
	        
	      
	        
	        Customer customer = new Customer();
	        customer.setPassword(confirmPassword);
	        customer.setAccountNumber(accountNumber);
	        
	        CustomerDAO dao = new CustomerDAO();
	        boolean isUpdated = dao.updatePassword(customer);
	        
	        if (isUpdated) {
	        	
	            response.sendRedirect("success.jsp");
	        } else {
	            response.sendRedirect("error.jsp");
	        }
	    }

}
