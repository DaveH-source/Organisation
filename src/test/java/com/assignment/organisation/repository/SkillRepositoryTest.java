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

import com.assignment.organisation.domain.Skill;

/**
 * Test cases for the SkillRepository.
 * 
 * @author daveH
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SkillRepositoryTest {

	/** Test values. */
	private static final long TEST_SKILL_ID = Long.valueOf(1);

	private static final String TEST_SKILL_1 = "TEST_SKILL_1";

	/** Expected exception thrown by a test. */
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	/** The skill repository. */
	@Autowired
	private SkillRepository skillRepository;

	/**
	 * Show that an skill can be found when using id.
	 * 
	 */
	@Test
	@Transactional
	public void shouldSuccessfullyFindSkillById() {

		Skill skill = Skill.builder().id(TEST_SKILL_ID).skillName(TEST_SKILL_1).build();

		Long id = skillRepository.saveAndFlush(skill).getId();

		Optional<Skill> savedSkill = skillRepository.findById(id);

		assertTrue("saved skill should be present", savedSkill.isPresent());
		assertEquals("skill name name should match expected value", TEST_SKILL_1, savedSkill.get().getSkillName());
	}

	/**
	 * Show that an skill can deleted.
	 * 
	 */
	@Test
	@Transactional
	public void shouldSuccessfullyDeleteAnSkill() {

		Skill skill = Skill.builder().id(TEST_SKILL_ID).skillName(TEST_SKILL_1).build();

		Long id = skillRepository.saveAndFlush(skill).getId();

		Optional<Skill> skillDb = skillRepository.findById(id);

		this.skillRepository.delete(skillDb.get());

		skillDb = skillRepository.findById(id);

		assertFalse("saved skill should have been removed", skillDb.isPresent());
	}

}
