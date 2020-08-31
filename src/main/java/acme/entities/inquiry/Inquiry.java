
package acme.entities.inquiry;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
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
public class Inquiry extends DomainEntity {

	// Serialsation identifier --------------------------------

	private static final long	serialVersionUID	= 1L;

	//  Atributes -----------------------------------------------------

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String				title;

	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	private Date				creationMoment;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				deadline;

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	@Length(max = 1024)
	private String				paragraphsDescription;

	@NotNull
	@Valid
	private Money				maxIntervalMoney;

	@NotNull
	@Valid
	private Money				minIntervalMoney;

	@NotBlank
	@Email
	private String				contactEmail;

	//@NotBlank
	//private String				intervalMoney;

}
