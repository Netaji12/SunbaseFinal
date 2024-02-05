package com.customer.model;


public class LoginInfo_model {

	private String login_id;
	private String password;

// zero parameterized constructor
	public LoginInfo_model() {
		super();
	}

// parameterized constructor
	public LoginInfo_model(String login_id, String password) {
		super();
		this.login_id = login_id;
		this.password = password;
	}

	public String getLogin_id() {
		return login_id;
	}

	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

// to string part

	@Override
	public String toString() {
		return "LoginInfo [login_id=" + login_id + ", password=" + password + "]";
	}

}