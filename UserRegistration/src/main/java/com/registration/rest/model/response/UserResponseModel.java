package com.registration.rest.model.response;

import java.util.List;

public class UserResponseModel {

	private String userName;
	private String email;
	private String firstName;
	private String lastName;
	private String mobileno;
	List<AddressDetailsModel> address;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public List<AddressDetailsModel> getAddress() {
		return address;
	}
	public void setAddress(List<AddressDetailsModel> address) {
		this.address = address;
	}
}
