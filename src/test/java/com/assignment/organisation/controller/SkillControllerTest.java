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

import com.assignment.organisation.domain.Skill;
import com.assignment.organisation.service.SkillService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Test cases for the SkillController class.
 * 
 * @author daveH
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = SkillController.class)
public class SkillControllerTest {

	/** The API url. */
	private static final String API_URL = "/api/skill";

	/** The mock mvc. */
	@Autowired
	private MockMvc mockMvc;

	/** The skill service. */
	@MockBean
	private SkillService skillService;

	@Mock
	private Skill mockSkill;

	@Test
	public void shouldSuccessfullyCreateSkill() throws Exception {
		Skill skill = new Skill();
		this.mockMvc.perform(
				post(API_URL + "/add").contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonString(skill)))
				.andDo(print()).andExpect(status().isOk());
	}

	/**
	 * Show that an skill can be successfully retrieved.
	 * 
	 * @throws Exception
	 */
	@Test
	public void shouldSuccessfullyGetASkill() throws Exception {
		when(skillService.getSkillById(isA(Long.class))).thenReturn(Skill.builder().build());

		this.mockMvc.perform(get(API_URL + "/find/" + Long.MAX_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isOk());

		verify(skillService, times(1)).getSkillById(eq(Long.MAX_VALUE));
		verifyNoMoreInteractions(skillService);
	}

	/**
	 * Show that a skill which does not exist results in the correct response.
	 * 
	 * @throws Exception
	 */
	@Test
	public void shouldReturn404WhenSkillNotFound() throws Exception {
		when(skillService.getSkillById(isA(Long.class))).thenReturn(null);

		this.mockMvc.perform(get(API_URL + "/find/" + Long.MAX_VALUE)).andDo(print()).andExpect(status().isNotFound());

		verify(skillService, times(1)).getSkillById(Long.valueOf(Long.MAX_VALUE));
		verifyNoMoreInteractions(skillService);
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
