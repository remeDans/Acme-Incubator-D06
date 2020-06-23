
package acme.entities.notice;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.hibernate.validator.constraints.URL;

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
public class Notice extends DomainEntity {

	// Serialsation identifier --------------------------------

	private static final long	serialVersionUID	= 1L;

	//  Atributes -----------------------------------------------------

	@NotBlank
	@URL
	private String				headerPicture;

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
	private String				body;

	@URL
	private String				optionalLinks;

}
