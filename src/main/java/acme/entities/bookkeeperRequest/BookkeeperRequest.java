
package acme.entities.bookkeeperRequest;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import acme.framework.entities.DomainEntity;
import acme.framework.entities.UserAccount;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class BookkeeperRequest extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String				firmName;

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	@Length(max = 1024)
	private String				responsibilityStatement;

	@NotBlank
	@Pattern(regexp = "PENDING|ACCEPTED|REJECTED")
	private String				status;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private UserAccount			userAccount;

}
