package com.customer.model;


public class customerInfo_model {

	private int customer_id;
	private String first_name;
	private String last_name;
	private String street;
	private String address;
	private String city;
	private String state;
	private String email;
	private String phone;
	
	//zero parameterized constructor
	public customerInfo_model() {
		super();
	}
	
//parameterized constructor
	public customerInfo_model(int customer_id, String first_name, String last_name, String street, String address,
			String city, String state, String email, String phone) {
		super();
		this.customer_id = customer_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.street = street;
		this.address = address;
		this.city = city;
		this.state = state;
		this.email = email;
		this.phone = phone;
	}
	//adding getters and setters
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "customerInfo [customer_id=" + customer_id + ", first_name=" + first_name + ", last_name=" + last_name
				+ ", street=" + street + ", address=" + address + ", city=" + city + ", state=" + state + ", email="
				+ email + ", phone=" + phone + "]";
	}

	// Constructors (you can customize as needed)

}
