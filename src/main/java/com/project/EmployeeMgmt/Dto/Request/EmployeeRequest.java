package com.project.EmployeeMgmt.Dto.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class EmployeeRequest {
	
	@NotBlank(message = "First name is required")
	@Size(max = 20, message = "First name must not exceed 20 characters")
	private String firstName;
	
	@NotBlank(message = "Last name is required")
	@Size(max = 20, message = "Last name must not exceed 20 characters")
	private String lastName;
	
	@NotBlank(message = "Email is required")
	@Email(message = "Email format is incorrect")
	private String email;
	
	@NotBlank(message = "Phone number is required")
	@Size(max = 10,min = 10, message = "Phone number must be exactly 10 digits")
	private String phoneNumber;
	
	@NotBlank(message = "Department is required")
	private String dept;
	
	@NotBlank(message = "Status is required")
	@Pattern(regexp = "Active|Inactive", message = "Status must be either 'Active' or 'Inactive'")
	private String status;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public EmployeeRequest(
			@NotBlank(message = "First name is required") @Size(max = 20, message = "First name must not exceed 20 characters") String firstName,
			@NotBlank(message = "Last name is required") @Size(max = 20, message = "Last name must not exceed 20 characters") String lastName,
			@NotBlank(message = "Email is required") @Email(message = "Email format is incorrect") String email,
			@NotBlank(message = "Phone number is required") @Size(max = 10, min = 10, message = "Phone number must be exactly 10 digits") String phoneNumber,
			@NotBlank(message = "Department is required") String dept,
			@NotBlank(message = "Status is required") @Pattern(regexp = "Active|Inactive", message = "Status must be either 'Active' or 'Inactive'") String status) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.dept = dept;
		this.status = status;
	}

	public EmployeeRequest() {
		super();
	}
}
