package com.assignment.organisation.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.assignment.organisation.domain.Employee;

/**
 * This interface defines the API contract definition and associated methods
 * 
 * @author daveH
 */
@RequestMapping("/api/employee")
public interface EmployeeContract {

	/**
	 * Get employees by their Id.
	 * 
	 * @param request The employee id
	 * @return A response entity containing the Employee entity
	 */
	ResponseEntity<Employee> getEmployeeById(Long id);

	/**
	 * Get all employees.
	 * 
	 * @param request N/A
	 * @return A response entity containing a list of employees
	 */
	ResponseEntity<List<Employee>> retrieveAllEmployees();

	/**
	 * Delete an employee.
	 * 
	 * @param Employee id
	 * @return An http status
	 */
	HttpStatus deleteEmployee(@PathVariable Long id);

	/**
	 * Create an employee.
	 * 
	 * @param An employee entity
	 * @return A response entity containing an employee
	 */
	ResponseEntity<Employee> createEmployee(@RequestBody Employee employee);

	/**
	 * Update and employee.
	 * 
	 * @param The employee Id, employee entity
	 * @return Response entity containing updated employee entity
	 */
	ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee);

}
