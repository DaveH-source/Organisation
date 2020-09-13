package com.assignment.organisation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.organisation.domain.Employee;
import com.assignment.organisation.service.EmployeeService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController implements EmployeeContract {

	@Autowired
	private EmployeeService employeeService;

	@Override
	@GetMapping("/getAll")
	public ResponseEntity<List<Employee>> retrieveAllEmployees() {
		return ResponseEntity.ok().body(employeeService.getAllEmployee());
	}

	@Override
	@GetMapping("/find/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		return ResponseEntity.ok().body(employeeService.getEmployeeById(id));
	}

	@Override
	@DeleteMapping("/delete/{id}")
	public HttpStatus deleteEmployee(@PathVariable Long id) {
		this.employeeService.deleteEmployee(id);
		return HttpStatus.OK;
	}

	@Override
	@PostMapping("/add")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
		return ResponseEntity.ok().body(this.employeeService.createEmployee(employee));
	}

	@Override
	@PutMapping("/update/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
		employee.setId(id);
		return ResponseEntity.ok().body(this.employeeService.updateEmployee(employee));
	}
}