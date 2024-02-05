package com.customer.dao;
public interface login_infoDAO {

	boolean authenticate(String login_id, String password);

	// Other methods related to login_info can be added as needed
}