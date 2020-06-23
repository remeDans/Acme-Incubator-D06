
package acme.entities.activity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import acme.entities.investmentRound.InvestmentRound;
import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Activity extends DomainEntity {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	//  Atributes -----------------------------------------------------

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String				title;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				startMoment;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				endMoment;

	//Tenga en cuenta que el presupuesto de las actividades debe sumar la cantidad de dinero en la ronda de inversión correspondiente.
	@NotNull
	@Valid
	private Money				budget;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private InvestmentRound		investmentRound;
}
