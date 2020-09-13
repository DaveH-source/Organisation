package com.assignment.organisation.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * A domain level entity associated with an organisation
 * 
 * @author daveH
 *
 */
@Entity
@Table(name = "organisation")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Organisation {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "organisation_name")
	private String organisationName;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "organisation")
	private Employee employee;

}
