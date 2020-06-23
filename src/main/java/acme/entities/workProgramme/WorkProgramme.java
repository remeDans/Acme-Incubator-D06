
package acme.entities.workProgramme;

import javax.persistence.Entity;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class WorkProgramme extends DomainEntity {

	// Serialisation identifier -----------------------------------------------

	private static final long serialVersionUID = 1L;

	//  Atributes -----------------------------------------------------

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

	/*
	 * @NotNull
	 *
	 * @Valid
	 *
	 * @OneToOne(optional = false)
	 * private InvestmentRound investmentRound;
	 */

}
