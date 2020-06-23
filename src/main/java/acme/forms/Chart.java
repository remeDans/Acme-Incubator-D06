
package acme.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Chart implements Serializable {

	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;

	//Total number of technologies grouped by activity sector.
	private Object[]			totalNumberTechnologiesGroupByActivitySector;
	private Object[]			activitySectorTechnologiesGroupByActivitySector;

	//Ratio of open-source technologies versus closed-source technologies.
	private Double				ratioOpensourceTechnologiesVersusTotalTechnologies;
	private Double				ratioClosedsourceTechnologiesVersusTotalTechnologies;

	//Total number of tools grouped by activity sector
	private Object[]			totalNumberToolGroupByActivitySector;
	private Object[]			activitySectorToolsGroupByActivitySector;

	//Ratio of open-source tools versus closed-source tools
	private Float				ratioOpensourceToolsVersusTotalTools;
	private Float				ratioClosedsourceToolsVersusTotalTools;

	//Ratio of investment rounds grouped by kind

	private Float				ratioSeedInvestmentRoundGroupByKind;
	private Float				ratioAngelInvestmentRoundGroupByKind;
	private Float				ratioSeriesAInvestmentRoundGroupByKind;
	private Float				ratioSeriesBInvestmentRoundGroupByKind;
	private Float				ratioSeriesCInvestmentRoundGroupByKind;
	private Float				ratioBridgeInvestmentRoundGroupByKind;

	//Ratio of applications grouped by status.

	private Float				ratioPendingApplicationsGroupByStatus;
	private Float				ratioAcceptedApplicationsGroupByStatus;
	private Float				ratioRejectedApplicationsGroupByStatus;

}
