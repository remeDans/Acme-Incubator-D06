
package acme.entities.accountingRecord;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import acme.entities.investmentRound.InvestmentRound;
import acme.entities.roles.Bookkeeper;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AccountingRecord extends DomainEntity {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	//  Atributes -----------------------------------------------------

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String				title;

	@NotBlank
	@Pattern(regexp = "DRAFT|PUBLISHED")
	private String				status;

	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	private Date				creationMoment;

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String				body;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Bookkeeper			bookkeeper;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private InvestmentRound		investmentRound;

}
