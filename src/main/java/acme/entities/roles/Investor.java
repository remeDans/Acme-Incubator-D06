
package acme.entities.roles;

import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import acme.datatypes.Profile;
import acme.framework.entities.UserRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Investor extends UserRole {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String				firmName;

	@NotBlank
	private String				activitySector;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

	@Valid
	@NotNull
	private Profile				profile;

}
