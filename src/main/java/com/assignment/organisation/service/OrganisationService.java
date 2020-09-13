package com.assignment.organisation.service;

import java.util.List;

import com.assignment.organisation.domain.Organisation;

public interface OrganisationService {

	Organisation createOrganisation(Organisation organisation);

	Organisation updateOrganisation(Organisation organisation);

	List<Organisation> getAllOrganisation();

	Organisation getOrganisationById(Long organisationId);

	void deleteOrganisation(Long id);
}