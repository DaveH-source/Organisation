package com.assignment.organisation.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.assignment.organisation.domain.Employee;
import com.assignment.organisation.repository.EmployeeRepository;

/**
 * Test cases for the EmployeeServiceImpl class.
 * 
 * @author daveH
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceImplTest {

	private static final String TEST_EMP_NAME = "Dave";
	private static final Long TEST_EMP_ID = 100L;
	private static final String TEST_EMP_EMAIL = "Dave@somewhere.co.uk";
	private static final String TEST_EMP_NEW_EMAIL = "Dave@elsewhere.co.uk";

	/** Test values. */

	/** Expected exception thrown by a test. */
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	/** The employee repository. */
	@Mock
	private EmployeeRepository employeeRepository;

	/** The employee service. */
	@InjectMocks
	private EmployeeServiceImpl employeeService;

	/**
	 * Show that the service can successfully find an employee using id reference.
	 */
	@Test
	public void shouldSuccessfullyFindEmployeeById() {
		when(employeeRepository.findById(isA(Long.class))).thenReturn(anOptionalEmployee());

		Employee employeeResponse = employeeService.getEmployeeById(TEST_EMP_ID);

		assertNotNull("response should be non null", employeeResponse);
		assertEquals("response name should match", TEST_EMP_NAME, employeeResponse.getName());

		verify(employeeRepository, times(1)).findById(eq(TEST_EMP_ID));
		verifyNoMoreInteractions(employeeRepository);
	}

	/**
	 * Show that the service will fail to find an employee using an Id when the
	 * employee is not present in the repository.
	 */
	@Test
	public void shouldFailToFindEmployeeByIdWhenEmployeeDoesntExist() {
		Employee emptyEmployee = new Employee();

		when(employeeRepository.findById(isA(Long.class))).thenReturn(Optional.of(emptyEmployee));

		Employee response = employeeService.getEmployeeById(TEST_EMP_ID);

		assertNotNull("response should be non null", response);
		assertNull("id should be null", response.getId());

		verify(employeeRepository, times(1)).findById(eq(TEST_EMP_ID));
		verifyNoMoreInteractions(employeeRepository);
	}

	/**
	 * Show that a new employee can be successfully saved.
	 */
	@Test
	public void shouldSuccessfullySaveNewEmployee() {
		when(employeeRepository.saveAndFlush(isA(Employee.class))).thenReturn(aSavedEmployee());
		Employee response = employeeService
				.createEmployee(Employee.builder().id(null).email(TEST_EMP_EMAIL).name(TEST_EMP_NAME).build());

		assertNotNull("response should be non null", response);
		assertEquals("response name should match expected value", TEST_EMP_NAME, response.getName());

		verify(employeeRepository, times(1)).saveAndFlush(isA(Employee.class));
		verifyNoMoreInteractions(employeeRepository);

	}

	/**
	 * Show that an existing employee can be successfully saved.
	 */
	@Test
	public void shouldSuccessfullySaveExistingEmployee() {
		when(employeeRepository.findById(isA(Long.class))).thenReturn(anOptionalEmployee());
		when(employeeRepository.saveAndFlush(isA(Employee.class))).thenReturn(aSavedEmployee());

		Employee response = employeeService
				.updateEmployee(Employee.builder().id(TEST_EMP_ID).email(TEST_EMP_NEW_EMAIL).build());

		assertNotNull("response should be non null", response);
		assertEquals("response id should match expected value", TEST_EMP_ID, response.getId());
		assertEquals("response email should match expected value", TEST_EMP_NEW_EMAIL, response.getEmail());

		verify(employeeRepository, times(1)).findById(eq(TEST_EMP_ID));
		verify(employeeRepository, times(1)).save(isA(Employee.class));
		verifyNoMoreInteractions(employeeRepository);
	}

	/**
	 * Create an optional employee.
	 * 
	 * @return The optional employee
	 */
	private Optional<Employee> anOptionalEmployee() {
		return Optional.of(Employee.builder().email(TEST_EMP_EMAIL).id(TEST_EMP_ID).name(TEST_EMP_NAME).build());
	}

	/**
	 * Create a "saved" employee.
	 * 
	 * @return The saved employee
	 */
	private Employee aSavedEmployee() {
		return Employee.builder().email(TEST_EMP_EMAIL).id(TEST_EMP_ID).name(TEST_EMP_NAME).build();
	}

}
