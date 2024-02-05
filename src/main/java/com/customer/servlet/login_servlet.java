package com.customer.servlet;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.customer.daoImpl.login_infoDAOImpl;

@WebServlet("/LoginServlet")
public class login_servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Getting login credentials from the form
        String login_id = request.getParameter("login_id");
        String password = request.getParameter("Password");

        // Creating an instance of the DAO
        login_infoDAOImpl loginDAO = new login_infoDAOImpl();

        // Checking authentication against the database
        boolean isAuthenticated = loginDAO.authenticate(login_id, password);

        if (isAuthenticated) {
            // If authentication is successful, create a session
            HttpSession session = request.getSession(true);

            // Example: Fetch customer name from the database using login_id
            // (You need to implement a method in your DAO to get customer details by login_id)
            String customerName = loginDAO.getCustomerName(login_id);

            // Setting attributes in the session
            session.setAttribute("login_id", login_id);
            session.setAttribute("customerName", customerName);

            // Redirecting to home.jsp
            response.sendRedirect("index.jsp");
        } else {
            // If authentication fails, redirect to UnsuccessfulLogin.jsp
            response.sendRedirect("Unsuccessfull_Login.jsp");
        }
    }
}

