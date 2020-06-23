
package acme.entities.customisation;

import javax.persistence.Entity;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customisation extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	//  Atributes -----------------------------------------------------

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String				spamWords_En;

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String				spamWords_Es;

	@NotNull
	@DecimalMin(value = "0.0")
	@DecimalMax(value = "100.00")
	private Double				spamThreshold;

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String				activitySectors;

}
