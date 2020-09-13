package com.assignment.organisation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.organisation.domain.Employee;

/**
 * Repository interface for accessing the person.
 * 
 * @author daveH
 *
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}