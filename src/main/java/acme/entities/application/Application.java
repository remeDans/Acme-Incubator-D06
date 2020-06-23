
package acme.entities.application;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
import acme.entities.roles.Investor;
import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {

	@Index(columnList = "ticker"), @Index(columnList = "creationMoment")
})
public class Application extends DomainEntity {

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

	@NotBlank
	@Pattern(regexp = "PENDING|ACCEPTED|REJECTED")
	private String				status;

	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String				justification;

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String				statement;

	@NotNull
	@Valid
	private Money				investmentMoneyOffer;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Investor			investor;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private InvestmentRound		investmentRound;

}
