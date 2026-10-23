package com.project.EmployeeMgmt.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.project.EmployeeMgmt.Dto.Request.EmployeeRequest;
import com.project.EmployeeMgmt.Dto.Response.EmployeeResponse;

public interface EmployeeService {

	EmployeeResponse createEmployee(EmployeeRequest employeeRequest);
	
	Page<EmployeeResponse> getAllEmployees(
			String name, 
			String status, 
			Pageable pageable);
	
	EmployeeResponse getEmployeeById(Long id);
	
	EmployeeResponse updateEmployee(
			Long id, 
			EmployeeRequest employeeRequest);
	
	void deleteEmployee(Long id);
}
