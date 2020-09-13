package com.assignment.organisation.service;

import java.util.List;

import com.assignment.organisation.domain.Employee;

public interface EmployeeService {

	Employee createEmployee(Employee employee);

	Employee updateEmployee(Employee employee);

	List<Employee> getAllEmployee();

	Employee getEmployeeById(long employeeId);

	void deleteEmployee(long id);
}