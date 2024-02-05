package com.customer.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.customer.daoImpl.customer_info_Impl;
import com.customer.model.customerInfo_model;

@WebServlet("/ShowUpdateFormServlet")
public class ShowUpdateFormServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int customerId = Integer.parseInt(request.getParameter("customerId"));

            customer_info_Impl customerDAO = new customer_info_Impl();
            customerInfo_model customer = customerDAO.getCustomerById(customerId);

            if (customer != null) {
                request.setAttribute("customer", customer);

                // Check for filter parameters
                String filterBy = request.getParameter("filterBy");
                String filterValue = request.getParameter("filterValue");

                if (filterBy != null && filterValue != null && !filterValue.isEmpty()) {
                    // Filtering is requested, set filter parameters for use in AddUpdate.jsp
                    request.setAttribute("filterBy", filterBy);
                    request.setAttribute("filterValue", filterValue);
                }

                request.getRequestDispatcher("AddUpdate.jsp").forward(request, response);
            } else {
                // Handle customer not found
                response.getWriter().println("Customer not found.");
            }
        } catch (NumberFormatException e) {
            // Handle the case when the customer ID cannot be parsed
            response.getWriter().println("Invalid customer ID format");
        }
    }
}

