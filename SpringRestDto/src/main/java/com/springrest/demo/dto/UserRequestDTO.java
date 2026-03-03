package com.springrest.demo.dto;

import jakarta.validation.constraints.*;

public class UserRequestDTO {

	@NotBlank(message = "Name is required")
	private String name;

	@Email(message = "Invalid email format")
	private String email;

	@Size(min = 6, message = "Password must be at least 6 characters")
	private String password;

	public UserRequestDTO() {

	}

	public UserRequestDTO(@NotBlank(message = "Name is required") String name,
			@Email(message = "Invalid email format") String email,
			@Size(min = 6, message = "Password must be at least 6 characters") String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserRequestDTO [name=" + name + ", email=" + email + ", password=" + password + "]";
	}

}
