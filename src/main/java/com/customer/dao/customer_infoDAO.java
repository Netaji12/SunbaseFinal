package com.customer.dao;

import java.util.List;

import com.customer.model.customerInfo_model;

public interface customer_infoDAO {

	boolean addCustomer(customerInfo_model newCustomer);

	void updateCustomer(customer_infoDAO customer);

	List<customerInfo_model> getListOfCustomers();

	customerInfo_model getCustomerById(int customerId);

	boolean deleteCustomerById(int customerId);

	boolean addCustomer(customer_infoDAO customer);
}
