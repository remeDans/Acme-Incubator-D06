
package acme.entities.bulletins;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DansBulletin extends DomainEntity {

	// Serialsation identifier --------------------------------

	private static final long	serialVersionUID	= 1L;

	//  Atributes -----------------------------------------------------

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String				author;

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String				text;

	@Temporal(TemporalType.TIMESTAMP)
	private Date				moment;

}
