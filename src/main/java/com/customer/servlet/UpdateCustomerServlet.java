package com.customer.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.customer.daoImpl.customer_info_Impl;
import com.customer.model.customerInfo_model;

@WebServlet("/UpdateCustomerServlet")
public class UpdateCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the customer ID from the request parameter
        String customerIdParameter = request.getParameter("customerId");

        // Check if the parameter is not null and not empty
        if (customerIdParameter != null && !customerIdParameter.isEmpty()) {
            try {
                // Parse the customer ID from the parameter
                int customerId = Integer.parseInt(customerIdParameter);

                // Get other form fields
                String firstName = request.getParameter("first_name");
                String lastName = request.getParameter("last_name");
                String street = request.getParameter("street");
                String address = request.getParameter("address");
                String city = request.getParameter("city");
                String state = request.getParameter("state");
                String email = request.getParameter("email");
                String phone = request.getParameter("phone");

                // Create an instance of the customerInfo_model
                customerInfo_model updatedCustomer = new customerInfo_model();
                updatedCustomer.setCustomer_id(customerId);
                updatedCustomer.setFirst_name(firstName);
                updatedCustomer.setLast_name(lastName);
                updatedCustomer.setStreet(street);
                updatedCustomer.setAddress(address);
                updatedCustomer.setCity(city);
                updatedCustomer.setState(state);
                updatedCustomer.setEmail(email);
                updatedCustomer.setPhone(phone);

                // Instantiate customer_info_Impl and perform the update
                customer_info_Impl customerDAO = new customer_info_Impl();
                boolean success = customerDAO.updateCustomer(updatedCustomer);

                if (success) {
                    // Redirect to the customer list page upon successful update
                    response.sendRedirect("index.jsp");
                } else {
                    // Handle update failure
                    response.getWriter().println("Update failed. Please try again.");
                }
            } catch (NumberFormatException e) {
                // Handle the case when the customer ID cannot be parsed
                // You might want to redirect the user or show an error message.
                response.getWriter().println("Invalid customer ID format");
            }
        } else {
            // Handle the case when "customerId" parameter is not present or is empty.
            // You might want to redirect the user or show an error message.
            response.getWriter().println("Invalid or missing customer ID parameter");
        }
    }
}
