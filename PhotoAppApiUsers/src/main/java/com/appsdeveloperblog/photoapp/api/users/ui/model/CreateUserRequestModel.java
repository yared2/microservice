package com.appsdeveloperblog.photoapp.api.users.ui.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateUserRequestModel {
	
	
	@NotNull(message ="First name canot be null")
	@Size(min=2, message="First name must be less than two characters")
	private String firstName;
	
	@NotNull(message ="Last name canot be null")
	@Size(min=2, message="Last name must not be less than two characters")
	private String lastName;
	
	@NotNull(message ="Password  canot be null")
	@Size(min=2, message="Password must not be equal or  greater 8 characters")
	private String password;
	
	@NotNull(message ="Email  canot be null")
	@Email
	private String email;
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

	
}
