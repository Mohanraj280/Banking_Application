package com.mohan;

import java.io.IOException;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.mohan.model.Mohan;
import com.mohan.model.DAO.MohanDAO;

public class getMohan extends HttpServlet {
    private static final int MAX_ATTEMPTS = 3;
    private static final int LOCKOUT_TIME = 30; // 30 seconds

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("user");
        String password = request.getParameter("pass");

        HttpSession session = request.getSession();
        Integer attemptCount = (Integer) session.getAttribute("attemptCount");
        Long lockoutTime = (Long) session.getAttribute("lockoutTime");

        if (lockoutTime != null && System.currentTimeMillis() < lockoutTime) {
            long remainingTime = (lockoutTime - System.currentTimeMillis()) / 1000;
            request.setAttribute("error", "Account locked. Try again in " + remainingTime + " seconds.");
            request.setAttribute("remainingTime", remainingTime);
            RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
            rd.forward(request, response);
            return;
        }

        if (attemptCount == null) {
            attemptCount = 0;
        }

        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            request.setAttribute("error", "Username and Password cannot be empty");
            RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
            rd.forward(request, response);
            return;
        }

        MohanDAO dao = new MohanDAO();
        Mohan m1 = dao.getMohan(username);
        if (m1 == null || !m1.getPassword().equals(password)) {
            attemptCount++;
            session.setAttribute("attemptCount", attemptCount);

            if (attemptCount >= MAX_ATTEMPTS) {
                long lockoutEndTime = System.currentTimeMillis() + (LOCKOUT_TIME * 1000);
                session.setAttribute("lockoutTime", lockoutEndTime);
                request.setAttribute("error", "Account locked due to too many failed attempts. Try again in 30 seconds.");
                request.setAttribute("remainingTime", LOCKOUT_TIME);
            } else {
                request.setAttribute("error", "Invalid username or password. Attempt " + attemptCount + " of " + MAX_ATTEMPTS);
            }

            RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
            rd.forward(request, response);
            return;
        }

        // Successful login, reset the attempt counter
        session.setAttribute("attemptCount", 0);
        session.setAttribute("lockoutTime", null);
        
        // Create a session and set user attributes
        session.setAttribute("user", m1);
        
        RequestDispatcher rd = request.getRequestDispatcher("AdminLanding.jsp");
        rd.forward(request, response);
    }
}
