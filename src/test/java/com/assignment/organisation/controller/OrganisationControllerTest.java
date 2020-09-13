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

import com.assignment.organisation.domain.Organisation;
import com.assignment.organisation.service.OrganisationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Test cases for the OrganisationController class.
 * 
 * @author daveH
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = OrganisationController.class)
public class OrganisationControllerTest {

	/** The API url. */
	private static final String API_URL = "/api/organisation";

	/** The mock mvc. */
	@Autowired
	private MockMvc mockMvc;

	/** The organisation service. */
	@MockBean
	private OrganisationService organisationService;

	@Mock
	private Organisation mockOrganisation;

	@Test
	public void shouldSuccessfullyCreateOrganisation() throws Exception {
		Organisation organisation = new Organisation();
		this.mockMvc.perform(post(API_URL + "/add").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(asJsonString(organisation))).andDo(print()).andExpect(status().isOk());
	}

	/**
	 * Show that an organisation can be successfully retrieved.
	 * 
	 * @throws Exception
	 */
	@Test
	public void shouldSuccessfullyGetAnOrganisation() throws Exception {
		when(organisationService.getOrganisationById(isA(Long.class))).thenReturn(Organisation.builder().build());

		this.mockMvc.perform(get(API_URL + "/find/" + Long.MAX_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isOk());

		verify(organisationService, times(1)).getOrganisationById(eq(Long.MAX_VALUE));
		verifyNoMoreInteractions(organisationService);
	}

	/**
	 * Show that a organisation which does not exist results in the correct
	 * response.
	 * 
	 * @throws Exception
	 */
	@Test
	public void shouldReturn404WhenOrganisationNotFound() throws Exception {
		when(organisationService.getOrganisationById(isA(Long.class))).thenReturn(null);

		this.mockMvc.perform(get(API_URL + "/find/" + Long.MAX_VALUE)).andDo(print()).andExpect(status().isNotFound());

		verify(organisationService, times(1)).getOrganisationById(Long.valueOf(Long.MAX_VALUE));
		verifyNoMoreInteractions(organisationService);
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
