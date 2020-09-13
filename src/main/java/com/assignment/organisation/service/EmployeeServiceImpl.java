package com.assignment.organisation.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.organisation.domain.Employee;
import com.assignment.organisation.exception.ResourceNotFoundException;
import com.assignment.organisation.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public Employee updateEmployee(Employee employee) {
		Optional<Employee> employeeDb = this.employeeRepository.findById(employee.getId());

		if (employeeDb.isPresent()) {
			Employee employeeUpdate = employeeDb.get();
			employeeUpdate.setId(employee.getId());
			employeeUpdate.setName(employee.getName());
			employeeUpdate.setEmail(employee.getEmail());
			employeeRepository.save(employeeUpdate);
			return employeeUpdate;
		} else {
			throw new ResourceNotFoundException("Record not found with id : " + employee.getId());
		}
	}

	public List<Employee> getAllEmployee() {
		return this.employeeRepository.findAll();
	}

	public Employee getEmployeeById(long employeeId) {

		Optional<Employee> employeeDb = this.employeeRepository.findById(employeeId);

		if (employeeDb.isPresent()) {
			return employeeDb.get();
		} else {
			throw new ResourceNotFoundException("Record not found with id : " + employeeId);
		}
	}

	public void deleteEmployee(long employeeId) {
		Optional<Employee> employeeDb = this.employeeRepository.findById(employeeId);

		if (employeeDb.isPresent()) {
			this.employeeRepository.delete(employeeDb.get());
		} else {
			throw new ResourceNotFoundException("Record not found with id : " + employeeId);
		}

	}
}