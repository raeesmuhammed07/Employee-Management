package com.project.EmployeeMgmt.ServiceImpl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.EmployeeMgmt.Dto.Request.EmployeeRequest;
import com.project.EmployeeMgmt.Dto.Response.EmployeeResponse;
import com.project.EmployeeMgmt.Entity.Employee;
import com.project.EmployeeMgmt.ExceptionHandlers.EmailAlreadyExistsException;
import com.project.EmployeeMgmt.ExceptionHandlers.ResourceNotFoundException;
import com.project.EmployeeMgmt.Repository.EmployeeRepository;
import com.project.EmployeeMgmt.Service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	@Override
	public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) {
		
		if(employeeRepository.findByEmail(employeeRequest.getEmail()).isPresent()) {
			throw new EmailAlreadyExistsException("email already exists");
		}
		
		Employee employee = new Employee();
		employee.setFirstName(employeeRequest.getFirstName());
		employee.setLastName(employeeRequest.getLastName());
		employee.setEmail(employeeRequest.getEmail());
		employee.setPhoneNumber(employeeRequest.getPhoneNumber());
		employee.setDept(employeeRequest.getDept());
		employee.setStatus(employeeRequest.getStatus());
		
		Employee savedEmployee = employeeRepository.save(employee);
		return convertToResponse(savedEmployee);
		
	}
	
	@Override
	public Page<EmployeeResponse> getAllEmployees(
			String name, 
			String status, 
			Pageable pageable) {
		
		return employeeRepository.findByNameAndStatus(name,status,pageable)
				.map(this::convertToResponse);
	}
	
	@Override
	public EmployeeResponse getEmployeeById(Long id) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
		return convertToResponse(employee);
	}
	
	@Override
	public  EmployeeResponse updateEmployee(Long id,EmployeeRequest employeeRequest) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
		
		if(!employee.getEmail().equals(employeeRequest.getEmail()) && 
				employeeRepository.findByEmail(employeeRequest.getEmail()).isPresent()) {
			throw new EmailAlreadyExistsException("email already exists");
		}
		
		employee.setFirstName(employeeRequest.getFirstName());
		employee.setLastName(employeeRequest.getLastName());
		employee.setEmail(employeeRequest.getEmail());
		employee.setPhoneNumber(employeeRequest.getPhoneNumber());
		employee.setDept(employeeRequest.getDept());
		employee.setStatus(employeeRequest.getStatus());
		
		Employee updatedEmployee = employeeRepository.save(employee);
		
		return convertToResponse(updatedEmployee);
		
	}
	
	@Override
	public void deleteEmployee(Long id) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
		employeeRepository.deleteById(id);
	}
	//Helper method to convert Employee entity to EmployeeResponse
	private EmployeeResponse convertToResponse(Employee employee) {
		return new EmployeeResponse(
				employee.getId(),
				employee.getFirstName(),
				employee.getLastName(),
				employee.getEmail(),
				employee.getPhoneNumber(),
				employee.getStatus()
				);
	}
}
