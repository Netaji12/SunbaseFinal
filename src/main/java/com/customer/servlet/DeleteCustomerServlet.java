package com.customer.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.customer.daoImpl.customer_info_Impl;

@WebServlet("/DeleteCustomerServlet")
public class DeleteCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Get the customer ID from the request
		int customerId = Integer.parseInt(request.getParameter("customerId"));

		// Create an instance of the DAO
		customer_info_Impl customerDAO = new customer_info_Impl();

		// Delete the customer by ID
		boolean deletionSuccess = customerDAO.deleteCustomerById(customerId);

		// Determine the destination page based on the deletion success
		String destinationPage = deletionSuccess ? "index.jsp" : "failToDelete.jsp";

		// Redirect to the appropriate page
		response.sendRedirect(destinationPage);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}

