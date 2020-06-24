
package acme.datatypes;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

import acme.framework.datatypes.DomainDatatype;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
//@ToString
public class Email extends DomainDatatype {

	//Serualisation identifier ----------------------------------

	private static final long	serialVersionUID	= 1L;

	// Atributes -------------------------------------------

	private String				displayName;

	@NotBlank
	private String				user;

	@NotBlank
	private String				domainName;

	@NotBlank
	private String				domainExtension;


	// Object interface -----------------------------------------------

	@Override
	public String toString() {

		String resultado;
		StringBuilder result;

		result = new StringBuilder();

		if (this.displayName != null) {
			result.append("<");
			result.append(this.displayName);
			result.append(">");
		}
		result.append(this.user);
		result.append("@");
		result.append(this.domainName);
		result.append(".");
		result.append(this.domainExtension);

		resultado = result.toString();

		return resultado;
	}

}
