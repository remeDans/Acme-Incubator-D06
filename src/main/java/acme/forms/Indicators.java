
package acme.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Indicators implements Serializable {

	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;

	Object[]					pendingApplicationsPerDay;
	Object[]					acceptedApplicationsPerDay;
	Object[]					rejectedApplicationsPerDay;

}
