
package acme.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard implements Serializable {

	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;

	Integer						totalNumberNotices;
	Integer						totalNumberTechnologyRecords;
	Integer						totalNumberToolRecords;

	Double						minMoneyIntervalsActiveInquiries;
	Double						maxMoneyIntervalsActiveInquiries;
	Double						avgMoneyIntervalsActiveInquiries;
	Double						desvMoneyIntervalsActiveInquiries;

	Double						minMoneyIntervalsActiveOvertures;
	Double						maxMoneyIntervalsActiveOvertures;
	Double						avgMoneyIntervalsActiveOvertures;
	Double						desvMoneyIntervalsActiveOvertures;

	//--------------------------------

	Double						avgNumberInventmentRoundPerEntrepreneur;
	Double						avgNumberApplicationPerEntrepreneur;
	Double						avgNumberApplicationPerInvestor;

}
