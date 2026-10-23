package com.project.EmployeeMgmt.Repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.EmployeeMgmt.Entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
	Optional<Employee> findByEmail(String email);
	@Query("""
			SELECT e FROM Employee e
			WHERE (
			  LOWER(CONCAT(e.firstName, ' ', e.lastName))
			    LIKE LOWER(CONCAT('%', :name,'%'))
			  OR LOWER(e.firstName)
			  	LIKE LOWER(CONCAT('%', :name,'%'))
			  )
			  AND LOWER(e.status) LIKE LOWER(CONCAT('%', :status,'%'))
			""")
	Page<Employee> findByNameAndStatus(
			@Param("name") String name,
			@Param("status") String status,
			Pageable pageable);

}
