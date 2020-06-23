
package acme.entities.toolRecord;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

@Table(indexes = {

	@Index(columnList = "activitySector"), @Index(columnList = "stars")
})
public class ToolRecord extends DomainEntity {

	// Serialsation identifier --------------------------------

	private static final long	serialVersionUID	= 1L;

	//  Atributes -----------------------------------------------------

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String				title;

	@NotBlank
	private String				activitySector;

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String				nameInventor;

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String				description;

	@NotBlank
	@URL
	private String				webSite;

	@NotBlank
	@Email
	private String				contactEmail;

	@NotNull
	private boolean				openSource;

	@Range(min = -5, max = 5)
	private int					stars;

}
