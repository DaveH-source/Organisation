package com.assignment.organisation.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.assignment.organisation.domain.Employee;

/**
 * Test cases for the EmployeeRepository.
 * 
 * @author daveH
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeRepositoryTest {

	/** Test values. */
	private static final long TEST_EMP_ID = Long.valueOf(15);
	private static final String TEST_EMP_NAME = "TEST_EMP_NAME";
	private static final String TEST_EMAIL = "TEST_EMAIL";

	/** Expected exception thrown by a test. */
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	/** The employee repository. */
	@Autowired
	private EmployeeRepository employeeRepository;

	/**
	 * Show that an employee can be found when using id.
	 * 
	 */
	@Test
	@Transactional
	public void shouldSuccessfullyFindEmployeeById() {

//		Set<EmployeeSkill> mutableSet = Collections.EMPTY_SET;
//		Collections.addAll(mutableSet = new HashSet<EmployeeSkill>(), 1, 2, SkillLevelEnum.AWARENESS);

//		Set<EmployeeSkill> skills = new Set<EmployeeSkill>();

		Employee employee = Employee.builder().id(TEST_EMP_ID).name(TEST_EMP_NAME).email(TEST_EMAIL).build();

		Long id = employeeRepository.saveAndFlush(employee).getId();

		Optional<Employee> savedEmployee = employeeRepository.findById(id);

		assertTrue("saved employee should be present", savedEmployee.isPresent());
		assertEquals("employee name should match expected value", TEST_EMP_NAME, savedEmployee.get().getName());
		assertEquals("employee email should match expected value", TEST_EMAIL, savedEmployee.get().getEmail());
	}

	/**
	 * Show that an employee can deleted.
	 * 
	 */
	@Test
	@Transactional
	public void shouldSuccessfullyDeleteAnEmployee() {

		Employee employee = Employee.builder().id(TEST_EMP_ID).name(TEST_EMP_NAME).email(TEST_EMAIL).build();

		Long id = employeeRepository.saveAndFlush(employee).getId();

		Optional<Employee> employeeDb = employeeRepository.findById(id);

		this.employeeRepository.delete(employeeDb.get());

		employeeDb = employeeRepository.findById(id);

		assertFalse("saved employee should have been removed", employeeDb.isPresent());
	}

}
