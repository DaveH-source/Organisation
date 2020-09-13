package com.assignment.organisation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.organisation.domain.Skill;
import com.assignment.organisation.service.SkillService;

@RestController
@RequestMapping("/api/skill")
public class SkillController {

	@Autowired
	private SkillService skillService;

	@GetMapping("/getAll")
	public ResponseEntity<List<Skill>> retrieveAllSkills() {
		return ResponseEntity.ok().body(skillService.getAllSkill());
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<Skill> getSkillById(@PathVariable Long id) {
		return ResponseEntity.ok().body(skillService.getSkillById(id));
	}

	@DeleteMapping("/delete/{id}")
	public HttpStatus deleteSkill(@PathVariable Long id) {
		this.skillService.deleteSkill(id);
		return HttpStatus.OK;
	}

	@PostMapping("/add")
	public ResponseEntity<Skill> createSkill(@RequestBody Skill skill) {
		return ResponseEntity.ok().body(this.skillService.createSkill(skill));
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Skill> updateSkill(@PathVariable Long id, @RequestBody Skill skill) {
		skill.setId(id);
		return ResponseEntity.ok().body(this.skillService.updateSkill(skill));
	}
}