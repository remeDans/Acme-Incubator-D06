
package acme.entities.creditCard;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import acme.entities.banner.Banner;
import acme.entities.roles.Patron;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
	@Index(columnList = "monthExpired"), @Index(columnList = "yearExpired")
})
public class CreditCard extends DomainEntity {

	// Serialsation identifier --------------------------------

	private static final long	serialVersionUID	= 1L;

	//  Atributes -----------------------------------------------------

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String				holderName;

	@NotBlank
	@CreditCardNumber
	private String				number;

	@Pattern(regexp = "VISA|MASTERCARD|DISCOVER|DINNERS|AMEX")
	@NotBlank
	private String				brand;

	@NotNull
	@Range(min = 1, max = 12)
	private int					monthExpired;

	@NotNull
	@Min(2020)
	private int					yearExpired;

	@NotNull
	@Range(min = 100, max = 999)
	private int					CVV;

	//   Relationship ---------------------------------------------------------------

	//@NotNull
	@Valid
	@OneToOne(optional = true)
	private Banner				banner;

	//@NotNull
	@Valid
	@OneToOne(optional = true)
	private Patron				patron;
}
