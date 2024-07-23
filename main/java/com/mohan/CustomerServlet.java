package com.mohan;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mohan.model.Customer;
import com.mohan.model.DAO.CustomerDAO;

/**
 * Servlet implementation class CustomerServlet
 */
public class CustomerServlet extends HttpServlet {
    private CustomerDAO cust = new CustomerDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fullName = request.getParameter("fullName");
        String address = request.getParameter("address");
        String mobileNo = request.getParameter("mobileNo");
        String email = request.getParameter("email");
        String accountType = request.getParameter("accountType");
        String initialBalance = request.getParameter("initialBalance");
        String dob = request.getParameter("dob");
        String idProof = request.getParameter("idProof");
        String aadharNo = request.getParameter("aadharNo");
        String gender = request.getParameter("gender");

        BigDecimal balance = new BigDecimal(initialBalance);

        // Generate account number and password
        String accountNumber = generateAccountNumber();
        String password = generatePassword();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
        	  date = new Date(dateFormat.parse(dob).getTime());
        } catch (ParseException e) {
            e.printStackTrace(); 
        }

        Customer c = new Customer();
        c.setFullName(fullName);
        c.setAddress(address);
        c.setMobileNo(mobileNo);
        c.setEmail(email);
        c.setAccountType(accountType);
        c.setInitialBalance(balance);
        c.setDob(date);
        c.setIdProof(idProof);
        c.setAadharNo(aadharNo);
        c.setGender(gender);
        c.setAccountNumber(accountNumber);
        c.setPassword(password);

        try {
            cust.registerCustomer(c); 
        } catch (Exception e) {
            e.printStackTrace(); 
        }

        response.sendRedirect("AdminLanding.jsp");
    }

    private String generateAccountNumber() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            sb.append(random.nextInt(1,9));
        }
        return sb.toString();
    }

    private String generatePassword() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(4);
        for (int i = 0; i < 4; i++) {
            sb.append(random.nextInt(1,9));
        }
        return sb.toString();
    }
}
