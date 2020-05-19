package com.registration.rest.model.request;

import java.util.List;

import com.registration.annotation.ValidEmail;
import com.registration.annotation.ValidMobileNo;
import com.registration.annotation.ValidPassword;
import com.registration.rest.model.response.AddressDetailsModel;

public class UserSignUpRequestModel {

	private String userName;
	@ValidPassword
	private String password;
	@ValidEmail
	private String email;
	private String firstName;
	private String lastName;
	@ValidMobileNo
	private String mobileno;
	List<AddressDetailsModel> address;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public void setMobileno(String phoneno) {
		this.mobileno = phoneno;
	}

	public List<AddressDetailsModel> getAddress() {
		return address;
	}

	public void setAddress(List<AddressDetailsModel> address) {
		this.address = address;
	}

}
