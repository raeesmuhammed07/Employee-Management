package com.project.EmployeeMgmt.ExceptionHandlers;

public class ResourceNotFoundException extends RuntimeException{
	public ResourceNotFoundException(String message) {
		super(message);
	}
}
