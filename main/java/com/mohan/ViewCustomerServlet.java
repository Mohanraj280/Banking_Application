package com.mohan;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mohan.model.Customer;
import com.mohan.model.DAO.CustomerDAO;

/**
 * Servlet implementation class ViewCustomersServlet
 */
public class ViewCustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchQuery = request.getParameter("accnumber");
        CustomerDAO dao = new CustomerDAO();
        List<Customer> customers;
        
        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            customers = dao.searchCustomersByAccount(searchQuery);
        } else {
            customers = dao.getAllCustomers();
        }
        
        request.setAttribute("customers", customers);
        request.getRequestDispatcher("DashBoard.jsp").forward(request, response);
    }
}
