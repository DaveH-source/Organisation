package com.assignment.organisation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.organisation.domain.Skill;

/**
 * Repository interface for accessing the skills.
 * 
 * @author daveH
 *
 */
@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
}