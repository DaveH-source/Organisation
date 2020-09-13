package com.assignment.organisation.service;

import java.util.List;

import com.assignment.organisation.domain.Skill;

public interface SkillService {

	Skill createSkill(Skill skill);

	Skill updateSkill(Skill skill);

	List<Skill> getAllSkill();

	Skill getSkillById(Long skillId);

	void deleteSkill(Long id);
}