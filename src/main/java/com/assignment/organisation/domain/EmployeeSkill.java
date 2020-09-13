package com.assignment.organisation.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.assignment.organisation.domain.support.SkillLevelEnum;

import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * A domain level entity associated with an employees skills and their level
 * 
 * @author daveH
 *
 */
@Entity
@Table(name = "employee_skill")
@NoArgsConstructor
@ToString
public class EmployeeSkill {

	@EmbeddedId
	private EmployeeSkillId id = new EmployeeSkillId();

	@ManyToOne
	@MapsId("employeeId")
	private Employee employee;

	@ManyToOne
	@MapsId("skillId")
	private Skill skill;

	@Column(name = "skill_level")
	private SkillLevelEnum skillLevel;

}
