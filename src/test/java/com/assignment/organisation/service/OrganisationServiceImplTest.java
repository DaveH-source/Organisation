package com.assignment.organisation.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.assignment.organisation.domain.Organisation;
import com.assignment.organisation.repository.OrganisationRepository;

/**
 * Test cases for the OrganisationServiceImpl class.
 * 
 * @author daveH
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class OrganisationServiceImplTest {

	private static final String TEST_ORG_NAME = "Daves Org";
	private static final Long TEST_ORG_ID = 100L;
	private static final String TEST_ORG_NEW_NAME = "Daves New Org";

	/** Test values. */

	/** Expected exception thrown by a test. */
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	/** The organisation repository. */
	@Mock
	private OrganisationRepository organisationRepository;

	/** The organisation service. */
	@InjectMocks
	private OrganisationServiceImpl organisationService;

	/**
	 * Show that the service can successfully find an organisation using id
	 * reference.
	 */
	@Test
	public void shouldSuccessfullyFindOrganisationById() {
		when(organisationRepository.findById(isA(Long.class))).thenReturn(anOptionalOrganisation());

		Organisation organisationResponse = organisationService.getOrganisationById(TEST_ORG_ID);

		assertNotNull("response should be non null", organisationResponse);
		assertEquals("response name should match", TEST_ORG_NAME, organisationResponse.getOrganisationName());

		verify(organisationRepository, times(1)).findById(eq(TEST_ORG_ID));
		verifyNoMoreInteractions(organisationRepository);
	}

	/**
	 * Show that the service will fail to find an organisation using an Id when the
	 * organisation is not present in the repository.
	 */
	@Test
	public void shouldFailToFindOrganisationByIdWhenOrganisationDoesntExist() {
		Organisation emptyOrganisation = new Organisation();

		when(organisationRepository.findById(isA(Long.class))).thenReturn(Optional.of(emptyOrganisation));

		Organisation response = organisationService.getOrganisationById(TEST_ORG_ID);

		assertNotNull("response should be non null", response);
		assertNull("id should be null", response.getId());

		verify(organisationRepository, times(1)).findById(eq(TEST_ORG_ID));
		verifyNoMoreInteractions(organisationRepository);
	}

	/**
	 * Show that a new organisation can be successfully saved.
	 */
	@Test
	public void shouldSuccessfullySaveNewOrganisation() {
		when(organisationRepository.saveAndFlush(isA(Organisation.class))).thenReturn(aSavedOrganisation());
		Organisation response = organisationService
				.createOrganisation(Organisation.builder().id(null).organisationName(TEST_ORG_NAME).build());

		assertNotNull("response should be non null", response);
		assertEquals("response name should match expected value", TEST_ORG_NAME, response.getOrganisationName());

		verify(organisationRepository, times(1)).saveAndFlush(isA(Organisation.class));
		verifyNoMoreInteractions(organisationRepository);

	}

	/**
	 * Show that an existing organisation can be successfully saved.
	 */
	@Test
	public void shouldSuccessfullySaveExistingOrganisation() {
		when(organisationRepository.findById(isA(Long.class))).thenReturn(anOptionalOrganisation());
		when(organisationRepository.saveAndFlush(isA(Organisation.class))).thenReturn(aSavedOrganisation());

		Organisation response = organisationService
				.updateOrganisation(Organisation.builder().id(TEST_ORG_ID).organisationName(TEST_ORG_NEW_NAME).build());

		assertNotNull("response should be non null", response);
		assertEquals("response id should match expected value", TEST_ORG_ID, response.getId());
		assertEquals("response email should match expected value", TEST_ORG_NEW_NAME, response.getOrganisationName());

		verify(organisationRepository, times(1)).findById(eq(TEST_ORG_ID));
		verify(organisationRepository, times(1)).save(isA(Organisation.class));
		verifyNoMoreInteractions(organisationRepository);
	}

	/**
	 * Create an optional organisation.
	 * 
	 * @return The optional organisation
	 */
	private Optional<Organisation> anOptionalOrganisation() {
		return Optional.of(Organisation.builder().id(TEST_ORG_ID).organisationName(TEST_ORG_NAME).build());
	}

	/**
	 * Create a "saved" organisation.
	 * 
	 * @return The saved organisation
	 */
	private Organisation aSavedOrganisation() {
		return Organisation.builder().id(TEST_ORG_ID).organisationName(TEST_ORG_NAME).build();
	}

}
