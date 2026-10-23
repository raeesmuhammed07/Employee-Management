package com.project.EmployeeMgmt.Controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.EmployeeMgmt.Dto.Request.EmployeeRequest;
import com.project.EmployeeMgmt.Dto.Response.EmployeeResponse;
import com.project.EmployeeMgmt.Service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	private final EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@PostMapping
	public ResponseEntity<EmployeeResponse> createEmployee(@Valid @RequestBody EmployeeRequest employeeRequest){
		EmployeeResponse employeeResponse = employeeService.createEmployee(employeeRequest);
		return new ResponseEntity<>(employeeResponse, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<Page<EmployeeResponse>> getAllEmployees(
			@RequestParam (defaultValue = "") String name,
			@RequestParam (defaultValue = "") String status,
			@RequestParam (defaultValue = "0") int page,
			@RequestParam (defaultValue = "10") int size){
		Pageable pageable = PageRequest.of(page, size);
		Page<EmployeeResponse> employeeResponses = employeeService.getAllEmployees(name, status, pageable);
		return ResponseEntity.ok(employeeResponses);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeResponse> updateEmployee(
			@PathVariable Long id,
			@Valid @RequestBody EmployeeRequest employeeRequest){
		
        EmployeeResponse employeeResponse = employeeService.updateEmployee(id, employeeRequest);
		return ResponseEntity.ok(employeeResponse);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeResponse> getEmployeebyId(@PathVariable Long id){
		EmployeeResponse employeeresponse = employeeService.getEmployeeById(id);
		return ResponseEntity.ok(employeeresponse);
	}
	
	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable Long id) {
		employeeService.deleteEmployee(id);
	}
	
}
