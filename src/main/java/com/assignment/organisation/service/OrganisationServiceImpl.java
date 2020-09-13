package com.assignment.organisation.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.organisation.domain.Organisation;
import com.assignment.organisation.exception.ResourceNotFoundException;
import com.assignment.organisation.repository.OrganisationRepository;

@Service
@Transactional
public class OrganisationServiceImpl implements OrganisationService {

	@Autowired
	private OrganisationRepository organisationRepository;

	public Organisation createOrganisation(Organisation organisation) {
		return organisationRepository.save(organisation);
	}

	public Organisation updateOrganisation(Organisation organisation) {
		Optional<Organisation> organisationDb = this.organisationRepository.findById(organisation.getId());

		if (organisationDb.isPresent()) {
			Organisation organisationUpdate = organisationDb.get();
			organisationUpdate.setId(organisation.getId());
			organisationUpdate.setOrganisationName(organisation.getOrganisationName());
			organisationRepository.save(organisationUpdate);
			return organisationUpdate;
		} else {
			throw new ResourceNotFoundException("Record not found with id : " + organisation.getId());
		}
	}

	public List<Organisation> getAllOrganisation() {
		return this.organisationRepository.findAll();
	}

	public Organisation getOrganisationById(Long organisationId) {

		Optional<Organisation> organisationDb = this.organisationRepository.findById(organisationId);

		if (organisationDb.isPresent()) {
			return organisationDb.get();
		} else {
			throw new ResourceNotFoundException("Record not found with id : " + organisationId);
		}
	}

	public void deleteOrganisation(Long organisationId) {
		Optional<Organisation> organisationDb = this.organisationRepository.findById(organisationId);

		if (organisationDb.isPresent()) {
			this.organisationRepository.delete(organisationDb.get());
		} else {
			throw new ResourceNotFoundException("Record not found with id : " + organisationId);
		}

	}
}