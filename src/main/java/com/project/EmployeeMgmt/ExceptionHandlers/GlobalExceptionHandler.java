package com.project.EmployeeMgmt.ExceptionHandlers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	//Exception Handling for ResourceNotFoundException
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Map<String, Object>> handleNotFound(ResourceNotFoundException e){
		return buildResponse(e.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	//Exception Handling for EmailAlreadyExistsException
	@ExceptionHandler(EmailAlreadyExistsException.class)
	public ResponseEntity<Map<String, Object>> handleEmailAlreadyExists(EmailAlreadyExistsException e){
		return buildResponse(e.getMessage(),HttpStatus.CONFLICT);
	}

	//Exception Handling for MethodArgumentNotValidException
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException e){
		
		Map<String, Object> body = new HashMap<>();
		
		body.put("timestamp", LocalDateTime.now());
		body.put("status", HttpStatus.BAD_REQUEST.value());
		
		Map<String, String> errs = new HashMap<>();
		
		e.getBindingResult().getFieldErrors().forEach(err ->
		  errs.put(err.getField(),err.getDefaultMessage()));
		body.put("errors", errs);
	
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	
	//Body
	private ResponseEntity<Map<String, Object>> buildResponse(String message, HttpStatus status) {
		
		Map<String, Object> body = new HashMap<>();
		
		body.put("timestamp", LocalDateTime.now());
		body.put("status", HttpStatus.BAD_REQUEST.value());
		body.put("error", message);
		body.put("message", message);
		
		return new ResponseEntity<>(body, status);
	}
}
