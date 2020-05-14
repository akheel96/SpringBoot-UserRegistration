package com.registration.entity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "user")
public class UserEntity {

	@Id
	@Column(unique = true)
	private String id;
	@Column(name = "username", unique = true)
	@NotEmpty
	@Length(min = 5, message = "Your user name must have at least 5 characters")
	private String userName;
	@Length(min = 5, message = "Your password must have at least 5 characters")
    @NotEmpty(message = "Please provide your password")
	private String password;
	@Column(unique = true)
	@NotEmpty(message = "Please provide an email")
	@Email(message = "Please provide a valid email")
	private String email;
	@Column(name = "firstname")
	@NotEmpty(message = "Please provide your first name")
	private String firstName;
	@Column(name = "lastname")
	@NotEmpty(message = "Please provide your last name")
	private String lastName;
	private String mobileno;
	private String country;
	@Column(name = "verificationtoken")
	private String verificationToken;
	@Column(name = "verificationstatus")
	private Boolean verificationStatus = false;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Boolean getVerificationStatus() {
		return verificationStatus;
	}

	public void setVerificationStatus(Boolean verificationStatus) {
		this.verificationStatus = verificationStatus;
	}

	public String getVerificationToken() {
		return verificationToken;
	}

	public void setVerificationToken(String verificationToken) {
		this.verificationToken = verificationToken;
	}

}
