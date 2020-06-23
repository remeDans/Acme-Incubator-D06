
package acme.entities.banner;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.hibernate.validator.constraints.URL;

import acme.entities.roles.Patron;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Banner extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@URL
	private String				picture;

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String				slogan;

	@NotBlank
	@URL
	private String				targetUrl;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Patron				patron;

	/*
	 * //@NotNull
	 *
	 * @Valid
	 *
	 * @OneToOne(optional = true)
	 * // private CreditCard creditCard;
	 */

}
