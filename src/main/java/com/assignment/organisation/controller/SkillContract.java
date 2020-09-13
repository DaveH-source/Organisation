package com.assignment.organisation.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.assignment.organisation.domain.Skill;

/**
 * This interface defines the API contract definition and associated methods
 * 
 * @author daveH
 */
@RequestMapping("/api/skill")
public interface SkillContract {

	/**
	 * Get skills by their Id.
	 * 
	 * @param request The skill id
	 * @return A response entity containing the Skill entity
	 */
	ResponseEntity<Skill> getSkillById(Long id);

	/**
	 * Get all skills.
	 * 
	 * @param request N/A
	 * @return A response entity containing a list of skills
	 */
	ResponseEntity<List<Skill>> retrieveAllSkills();

	/**
	 * Delete an skill.
	 * 
	 * @param Skill id
	 * @return An http status
	 */
	HttpStatus deleteSkill(@PathVariable Long id);

	/**
	 * Create an skill.
	 * 
	 * @param An skill entity
	 * @return A response entity containing an skill
	 */
	ResponseEntity<Skill> createSkill(@RequestBody Skill skill);

	/**
	 * Update and skill.
	 * 
	 * @param The skill Id, Skill entity
	 * @return Response entity containing updated skill entity
	 */
	ResponseEntity<Skill> updateSkill(@PathVariable Long id, @RequestBody Skill skill);

}
