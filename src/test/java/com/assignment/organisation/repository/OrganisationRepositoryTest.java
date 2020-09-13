package com.assignment.organisation.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.assignment.organisation.domain.Organisation;

/**
 * Test cases for the OrganisationRepository.
 * 
 * @author daveH
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrganisationRepositoryTest {

	/** Test values. */
	private static final long TEST_ORG_ID = Long.valueOf(11);
	private static final String TEST_ORG_NAME = "TEST_ORG_NAME";

	/** Expected exception thrown by a test. */
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	/** The organisation repository. */
	@Autowired
	private OrganisationRepository organisationRepository;

	/**
	 * Show that an organisation can be found when using id.
	 * 
	 */
	@Test
	@Transactional
	public void shouldSuccessfullyFindOrganisationById() {

		Organisation organisation = Organisation.builder().id(TEST_ORG_ID).organisationName(TEST_ORG_NAME).build();

		Long id = organisationRepository.saveAndFlush(organisation).getId();

		Optional<Organisation> savedOrganisation = organisationRepository.findById(id);

		assertTrue("saved organisation should be present", savedOrganisation.isPresent());
		assertEquals("organisaton name should match expected value", TEST_ORG_NAME,
				savedOrganisation.get().getOrganisationName());
	}

	/**
	 * Show that an organisation can deleted.
	 * 
	 */
	@Test
	@Transactional
	public void shouldSuccessfullyDeleteAnOrganisation() {

		Organisation organisation = Organisation.builder().id(TEST_ORG_ID).organisationName(TEST_ORG_NAME).build();

		Long id = organisationRepository.saveAndFlush(organisation).getId();

		Optional<Organisation> organisationDb = organisationRepository.findById(id);

		this.organisationRepository.delete(organisationDb.get());

		organisationDb = organisationRepository.findById(id);

		assertFalse("saved organisation should have been removed", organisationDb.isPresent());
	}

}
