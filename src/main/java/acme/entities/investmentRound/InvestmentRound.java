
package acme.entities.investmentRound;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.hibernate.validator.constraints.URL;

import acme.entities.roles.Entrepreneur;
import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class InvestmentRound extends DomainEntity {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	//  Atributes -----------------------------------------------------

	//SSS-YY-NNNNNN (mayus activitySector-ultimosdigitos anño creac-rango(000000-999999)
	@Pattern(regexp = "^[A-Z]{3}-[0-9]{2}-[0-9]{6}$")
	@NotBlank
	@Column(unique = true)
	private String				ticker;

	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	private Date				creationMoment;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				deadline;

	@Pattern(regexp = "SEED|ANGEL|SERIES-A|SERIES-B|SERIES-C|BRIDGE")
	@NotBlank
	private String				kindOfRound;

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String				title;

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	@Length(max = 1024)
	private String				description;

	@NotBlank
	@Pattern(regexp = "DRAFT|PUBLISHED")
	private String				status;

	@URL
	private String				link;

	@NotNull
	@Valid
	private Money				amountOfMoney;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Entrepreneur		entrepreneur;

}
