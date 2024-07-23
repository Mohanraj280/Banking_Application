package com.mohan;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mohan.model.Customer;
import com.mohan.model.DAO.CustomerDAO;

/**
 * Servlet implementation class UpdateCustomerServlet
 */
public class UpdateCustomerServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String accountNumber = request.getParameter("accountNumber");
        String fullName = request.getParameter("fullName");
        String address = request.getParameter("address");
        String mobileNo = request.getParameter("mobileNo");
        String email = request.getParameter("email");
        String dob = request.getParameter("dob");
        String idProof = request.getParameter("idProof");
        String aadharNo = request.getParameter("aadharNo");
        String gender = request.getParameter("gender");
     
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = new Date(dateFormat.parse(dob).getTime());
        } catch (ParseException e) {
            e.printStackTrace(); // Handle this appropriately (e.g., show an error message)
        }
        
        Customer customer = new Customer();
        customer.setAccountNumber(accountNumber);
        customer.setFullName(fullName);
        customer.setAddress(address);
        customer.setMobileNo(mobileNo);
        customer.setEmail(email);
        customer.setDob(date);
        customer.setIdProof(idProof);
        customer.setAadharNo(aadharNo);
        customer.setGender(gender);

        // Update customer in the database
        CustomerDAO dao = new CustomerDAO();
        boolean isUpdated = dao.updateUser(customer);

        if (isUpdated) {
            response.sendRedirect("AdminLanding.jsp");
        } else {
            response.sendRedirect("error.jsp");
        }
    }
}
