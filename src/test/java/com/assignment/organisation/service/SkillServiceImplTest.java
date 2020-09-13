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

import com.assignment.organisation.domain.Skill;
import com.assignment.organisation.repository.SkillRepository;

/**
 * Test cases for the SkillServiceImpl class.
 * 
 * @author daveH
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class SkillServiceImplTest {

	private static final String TEST_SKILL_NAME = "Writing";
	private static final Long TEST_SKILL_ID = 100L;
	private static final String TEST_NEW_SKILL_NAME = "Extended Writing";

	/** Test values. */

	/** Expected exception thrown by a test. */
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	/** The skill repository. */
	@Mock
	private SkillRepository skillRepository;

	/** The skill service. */
	@InjectMocks
	private SkillServiceImpl skillService;

	/**
	 * Show that the service can successfully find an skill using id reference.
	 */
	@Test
	public void shouldSuccessfullyFindSkillById() {
		when(skillRepository.findById(isA(Long.class))).thenReturn(anOptionalSkill());

		Skill skillResponse = skillService.getSkillById(TEST_SKILL_ID);

		assertNotNull("response should be non null", skillResponse);
		assertEquals("response name should match", TEST_SKILL_NAME, skillResponse.getSkillName());

		verify(skillRepository, times(1)).findById(eq(TEST_SKILL_ID));
		verifyNoMoreInteractions(skillRepository);
	}

	/**
	 * Show that the service will fail to find an skill using an Id when the skill
	 * is not present in the repository.
	 */
	@Test
	public void shouldFailToFindSkillByIdWhenSkillDoesntExist() {
		Skill emptySkill = new Skill();

		when(skillRepository.findById(isA(Long.class))).thenReturn(Optional.of(emptySkill));

		Skill response = skillService.getSkillById(TEST_SKILL_ID);

		assertNotNull("response should be non null", response);
		assertNull("id should be null", response.getId());

		verify(skillRepository, times(1)).findById(eq(TEST_SKILL_ID));
		verifyNoMoreInteractions(skillRepository);
	}

	/**
	 * Show that a new skill can be successfully saved.
	 */
	@Test
	public void shouldSuccessfullySaveNewSkill() {
		when(skillRepository.saveAndFlush(isA(Skill.class))).thenReturn(aSavedSkill());
		Skill response = skillService.createSkill(Skill.builder().id(null).skillName(TEST_SKILL_NAME).build());

		assertNotNull("response should be non null", response);
		assertEquals("response name should match expected value", TEST_SKILL_NAME, response.getSkillName());

		verify(skillRepository, times(1)).saveAndFlush(isA(Skill.class));
		verifyNoMoreInteractions(skillRepository);

	}

	/**
	 * Show that an existing skill can be successfully saved.
	 */
	@Test
	public void shouldSuccessfullySaveExistingSkill() {
		when(skillRepository.findById(isA(Long.class))).thenReturn(anOptionalSkill());
		when(skillRepository.saveAndFlush(isA(Skill.class))).thenReturn(aSavedSkill());

		Skill response = skillService
				.updateSkill(Skill.builder().id(TEST_SKILL_ID).skillName(TEST_NEW_SKILL_NAME).build());

		assertNotNull("response should be non null", response);
		assertEquals("response id should match expected value", TEST_SKILL_ID, response.getId());
		assertEquals("response email should match expected value", TEST_NEW_SKILL_NAME, response.getSkillName());

		verify(skillRepository, times(1)).findById(eq(TEST_SKILL_ID));
		verify(skillRepository, times(1)).save(isA(Skill.class));
		verifyNoMoreInteractions(skillRepository);
	}

	/**
	 * Create an optional skill.
	 * 
	 * @return The optional skill
	 */
	private Optional<Skill> anOptionalSkill() {
		return Optional.of(Skill.builder().id(TEST_SKILL_ID).skillName(TEST_SKILL_NAME).build());
	}

	/**
	 * Create a "saved" skill.
	 * 
	 * @return The saved skill
	 */
	private Skill aSavedSkill() {
		return Skill.builder().id(TEST_SKILL_ID).skillName(TEST_SKILL_NAME).build();
	}

}
