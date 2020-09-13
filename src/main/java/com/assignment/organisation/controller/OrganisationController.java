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

import com.assignment.organisation.domain.Organisation;
import com.assignment.organisation.service.OrganisationService;

@RestController
@RequestMapping("/api/organisation")
public class OrganisationController implements OrganisationContract {

	@Autowired
	private OrganisationService organisationService;

	@Override
	@GetMapping("/getAll")
	public ResponseEntity<List<Organisation>> retrieveAllOrganisations() {
		return ResponseEntity.ok().body(organisationService.getAllOrganisation());
	}

	@Override
	@GetMapping("/find/{id}")
	public ResponseEntity<Organisation> getOrganisationById(@PathVariable Long id) {
		return ResponseEntity.ok().body(organisationService.getOrganisationById(id));
	}

	@Override
	@DeleteMapping("/delete/{id}")
	public HttpStatus deleteOrganisation(@PathVariable Long id) {
		this.organisationService.deleteOrganisation(id);
		return HttpStatus.OK;
	}

	@Override
	@PostMapping("/add")
	public ResponseEntity<Organisation> createOrganisation(@RequestBody Organisation organisation) {
		return ResponseEntity.ok().body(this.organisationService.createOrganisation(organisation));
	}

	@Override
	@PutMapping("/update/{id}")
	public ResponseEntity<Organisation> updateOrganisation(@PathVariable Long id,
			@RequestBody Organisation organisation) {
		organisation.setId(id);
		return ResponseEntity.ok().body(this.organisationService.updateOrganisation(organisation));
	}
}