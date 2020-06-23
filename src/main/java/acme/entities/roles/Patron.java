
package acme.entities.roles;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import acme.framework.entities.UserRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Patron extends UserRole {

	// Serialisation identifier -----------------------------------------------

	private static final long serialVersionUID = 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String organisationName;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
