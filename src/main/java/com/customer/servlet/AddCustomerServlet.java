package com.customer.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.customer.dao.customer_infoDAO;
import com.customer.daoImpl.customer_info_Impl;
import com.customer.model.customerInfo_model;

@WebServlet("/AddCustomerServlet")
public class AddCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Retrieve customer details from the form
            String firstName = request.getParameter("first_name");
            String lastName = request.getParameter("last_name");
            String street = request.getParameter("street");
            String address = request.getParameter("address");
            String city = request.getParameter("city");
            String state = request.getParameter("state");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");

            // Create a customer model
            customerInfo_model newCustomer = new customerInfo_model();
            newCustomer.setFirst_name(firstName);
            newCustomer.setLast_name(lastName);
            newCustomer.setStreet(street);
            newCustomer.setAddress(address);
            newCustomer.setCity(city);
            newCustomer.setState(state);
            newCustomer.setEmail(email);
            newCustomer.setPhone(phone);

            // Create an instance of the DAO and add the customer
            customer_infoDAO customerDAO = new customer_info_Impl();
            boolean success = customerDAO.addCustomer(newCustomer);

            if (success) {
                // Redirect to the home page after successful addition
                response.sendRedirect("index.jsp");
            } else {
                // Handle the case when the addition fails
                response.getWriter().println("Failed to add customer.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle other exceptions if needed
            response.getWriter().println("An error occurred.");
        }
    }
}
