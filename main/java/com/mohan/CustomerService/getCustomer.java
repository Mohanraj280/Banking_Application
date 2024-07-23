package com.mohan.CustomerService;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mohan.model.Customer;
import com.mohan.model.Mohan;
import com.mohan.model.DAO.CustomerDAO;
import com.mohan.model.DAO.MohanDAO;

/**
 * Servlet implementation class getCustomer
 */
public class getCustomer extends HttpServlet {
	
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String accnum = request.getParameter("accnum");
	        String password = request.getParameter("pass");

	        if (accnum == null || accnum.isEmpty() || password == null || password.isEmpty()) {
	            request.setAttribute("error", "Username and Password cannot be empty");
	            RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
	            rd.forward(request, response);
	            return;
	        }

	        CustomerDAO dao = new CustomerDAO();
	        Customer cus = dao.getByAccountNumber(accnum);
	        if (cus == null || !cus.getPassword().equals(password)) {
	            request.setAttribute("error", "Invalid username or password");
	            RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
	            rd.forward(request, response);
	            return;
	        }

	        // Create a session and set user attributes
	        HttpSession session = request.getSession();
	        session.setAttribute("user", cus);

	        RequestDispatcher rd = request.getRequestDispatcher("CustomerDashBoard.jsp");
	        rd.forward(request, response);
	    }

}
