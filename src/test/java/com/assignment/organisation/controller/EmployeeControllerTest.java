package com.assignment.organisation.controller;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.assignment.organisation.domain.Employee;
import com.assignment.organisation.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Test cases for the EmployeeController class.
 * 
 * @author daveH
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = EmployeeController.class)
public class EmployeeControllerTest {

	/** The API url. */
	private static final String API_URL = "/api/employee";
	private static final String TEST_EMP_EMAIL = "dave@someplace.com";
	private static final String TEST_EMP_NAME = "dave";

	/** The mock mvc. */
	@Autowired
	private MockMvc mockMvc;

	/** The employee service. */
	@MockBean
	private EmployeeService employeeService;

	@Mock
	private Employee mockEmployee;

	@Test
	public void shouldSuccessfullyCreateEmployee() throws Exception {
		Employee employee = Employee.builder().email(TEST_EMP_EMAIL).name(TEST_EMP_NAME).build();
		this.mockMvc.perform(
				post(API_URL + "/add").contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonString(employee)))
				.andDo(print()).andExpect(status().isOk());
	}

	/**
	 * Show that an employee can be successfully retrieved.
	 * 
	 * @throws Exception
	 */
	@Test
	public void shouldSuccessfullyGetAnEmployee() throws Exception {
		when(employeeService.getEmployeeById(isA(Long.class))).thenReturn(Employee.builder().build());

		this.mockMvc.perform(get(API_URL + "/find/" + Long.MAX_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isOk());

		verify(employeeService, times(1)).getEmployeeById(eq(Long.MAX_VALUE));
		verifyNoMoreInteractions(employeeService);
	}

	/**
	 * Show that a employee which does not exist results in the correct response.
	 * 
	 * @throws Exception
	 */
	@Test
	public void shouldReturn404WhenEmployeeNotFound() throws Exception {
		when(employeeService.getEmployeeById(isA(Long.class))).thenReturn(null);

		this.mockMvc.perform(get(API_URL + "/find/" + Long.MAX_VALUE)).andDo(print()).andExpect(status().isNotFound());

		verify(employeeService, times(1)).getEmployeeById(Long.valueOf(Long.MAX_VALUE));
		verifyNoMoreInteractions(employeeService);
	}

	/**
	 * Convert an object to a json string.
	 * 
	 * @param object The object to translate
	 * @return
	 */
	private String asJsonString(final Object object) {
		try {
			return new ObjectMapper().writeValueAsString(object);
		} catch (JsonProcessingException jsonProcessingException) {
			throw new RuntimeException(jsonProcessingException);
		}
	}

}
