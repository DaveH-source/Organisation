package com.assignment.organisation.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class EmployeeSkillId implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long employeeId;
	private Long skillId;
}