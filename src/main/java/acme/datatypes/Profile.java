/*
 * UserIdentity.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.datatypes;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.DomainDatatype;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@Getter
@Setter
@ToString
public class Profile extends DomainDatatype {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@URL
	private String				photo;

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String				biography;

	// Derived attributes -----------------------------------------------------

}
