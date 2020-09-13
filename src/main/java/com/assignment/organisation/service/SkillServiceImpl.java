package com.assignment.organisation.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.organisation.domain.Skill;
import com.assignment.organisation.exception.ResourceNotFoundException;
import com.assignment.organisation.repository.SkillRepository;

@Service
@Transactional
public class SkillServiceImpl implements SkillService {

	@Autowired
	private SkillRepository skillRepository;

	public Skill createSkill(Skill skill) {
		return skillRepository.save(skill);
	}

	public Skill updateSkill(Skill skill) {
		Optional<Skill> skillDb = this.skillRepository.findById(skill.getId());

		if (skillDb.isPresent()) {
			Skill skillUpdate = skillDb.get();
			skillUpdate.setId(skill.getId());
			skillUpdate.setSkillName(skill.getSkillName());
			skillRepository.save(skillUpdate);
			return skillUpdate;
		} else {
			throw new ResourceNotFoundException("Record not found with id : " + skill.getId());
		}
	}

	public List<Skill> getAllSkill() {
		return this.skillRepository.findAll();
	}

	public Skill getSkillById(Long skillId) {

		Optional<Skill> skillDb = this.skillRepository.findById(skillId);

		if (skillDb.isPresent()) {
			return skillDb.get();
		} else {
			throw new ResourceNotFoundException("Record not found with id : " + skillId);
		}
	}

	public void deleteSkill(Long skillId) {
		Optional<Skill> skillDb = this.skillRepository.findById(skillId);

		if (skillDb.isPresent()) {
			this.skillRepository.delete(skillDb.get());
		} else {
			throw new ResourceNotFoundException("Record not found with id : " + skillId);
		}

	}
}