package com.assignment.organisation.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.assignment.organisation.domain.Organisation;

/**
 * This interface defines the API contract definition and associated methods
 * 
 * @author daveH
 */
@RequestMapping("/api/organisation")
public interface OrganisationContract {

	/**
	 * Get organisations by their Id.
	 * 
	 * @param request The organisation id
	 * @return A response entity containing the Organisation entity
	 */
	ResponseEntity<Organisation> getOrganisationById(Long id);

	/**
	 * Get all organisations.
	 * 
	 * @param request N/A
	 * @return A response entity containing a list of organisations
	 */
	ResponseEntity<List<Organisation>> retrieveAllOrganisations();

	/**
	 * Delete an organisation.
	 * 
	 * @param Organisation id
	 * @return An http status
	 */
	HttpStatus deleteOrganisation(@PathVariable Long id);

	/**
	 * Create an organisation.
	 * 
	 * @param An organisation entity
	 * @return A response entity containing an organisation
	 */
	ResponseEntity<Organisation> createOrganisation(@RequestBody Organisation organisation);

	/**
	 * Update and organisation.
	 * 
	 * @param The organisation Id, Organisation entity
	 * @return Response entity containing updated organisation entity
	 */
	ResponseEntity<Organisation> updateOrganisation(@PathVariable Long id, @RequestBody Organisation organisation);

}
