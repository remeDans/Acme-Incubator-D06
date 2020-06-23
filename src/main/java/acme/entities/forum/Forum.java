
package acme.entities.forum;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import acme.entities.investmentRound.InvestmentRound;
import acme.framework.entities.Authenticated;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Forum extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	//  Atributes -----------------------------------------------------

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String				title;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Authenticated		creator;

	@NotNull
	@Valid
	@OneToOne(optional = true)
	private InvestmentRound		investmentRound;

}
