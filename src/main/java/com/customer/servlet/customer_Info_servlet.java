package com.customer.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.customer.daoImpl.customer_info_Impl;
import com.customer.model.customerInfo_model;

@WebServlet("/addCustomer")
public class customer_Info_servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Getting customer details from the form
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        // Creating an instance of the DAO
        customer_info_Impl customerDAO = new customer_info_Impl();

        // Creating a customer model object
        customerInfo_model newCustomer = new customerInfo_model();
        newCustomer.setFirst_name(firstName);
        newCustomer.setLast_name(lastName);
        newCustomer.setAddress(address);
        newCustomer.setCity(city);
        newCustomer.setState(state);
        newCustomer.setEmail(email);
        newCustomer.setPhone(phone);

        // Adding the customer to the database
        boolean isCustomerAdded = customerDAO.addCustomer(newCustomer);

        // Handling success or failure
        if (isCustomerAdded) {
            // Redirecting to the paginated customer list page with success message
            response.sendRedirect("PaginatedCustomerList.jsp?success=true");
        } else {
            // Redirecting to failureAA.jsp
            response.sendRedirect("FailToAdd.jsp");
        }
    }
}
