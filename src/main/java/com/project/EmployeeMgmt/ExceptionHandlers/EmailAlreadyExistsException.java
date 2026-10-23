package com.project.EmployeeMgmt.ExceptionHandlers;

public class EmailAlreadyExistsException extends RuntimeException {
	public EmailAlreadyExistsException(String message) {
		super(message);
	}
}
