package com.assignment.organisation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.organisation.domain.Organisation;

/**
 * Repository interface for accessing the organisation.
 * 
 * @author daveH
 *
 */
@Repository
public interface OrganisationRepository extends JpaRepository<Organisation, Long> {
}