
package acme.entities.challenges;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
/*
 * @Table(indexes = {
 *
 * @Index(columnList = "deadline")
 * })
 */
public class Challenge extends DomainEntity {

	// Serialsation identifier --------------------------------

	private static final long	serialVersionUID	= 1L;

	//  Atributes -----------------------------------------------------

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String				title;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				deadline;

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String				description;

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String				goalRookieLevel;

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String				goalAverageLevel;

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String				goalExpertLevel;

	@NotNull
	@Valid
	private Money				rewardRookieLevel;

	@NotNull
	@Valid
	private Money				rewardAverageLevel;

	@NotNull
	@Valid
	private Money				rewardExpertLevel;

}
