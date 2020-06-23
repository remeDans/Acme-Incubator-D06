
package acme.features.administrator.chart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.Chart;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorChartShowService implements AbstractShowService<Administrator, Chart> {

	@Autowired
	private AdministratorChartRepository repository;


	@Override
	public boolean authorise(final Request<Chart> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Chart> request, final Chart entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "totalNumberTechnologiesGroupByActivitySector", "ratioOpensourceTechnologiesVersusTotalTechnologies", "ratioClosedsourceTechnologiesVersusTotalTechnologies", "totalNumberToolGroupByActivitySector",
			"ratioOpensourceToolsVersusTotalTools", "ratioClosedsourceToolsVersusTotalTools");

		//chart 5

		request.unbind(entity, model, "ratioSeedInvestmentRoundGroupByKind", "ratioAngelInvestmentRoundGroupByKind", "ratioSeriesAInvestmentRoundGroupByKind");
		request.unbind(entity, model, "ratioSeriesBInvestmentRoundGroupByKind", "ratioSeriesCInvestmentRoundGroupByKind", "ratioBridgeInvestmentRoundGroupByKind");

		//chart 6
		request.unbind(entity, model, "ratioPendingApplicationsGroupByStatus", "ratioAcceptedApplicationsGroupByStatus", "ratioRejectedApplicationsGroupByStatus");

	}

	@Override
	public Chart findOne(final Request<Chart> request) {
		Chart res = new Chart();

		// Chart 1
		Object[] totalNumberTechnologiesGroupByActivitySector = this.repository.getTotalNumberTechnologiesGroupByActivitySector();
		Object[] activitySectorTechnologiesGroupByActivitySector = this.repository.getActivitySectorTechnologiesGroupByActivitySector();
		// Chart 2
		Double ratioOpensourceTechnologiesVersusTotalTechnologies = this.repository.getRatioOpensourceTechnologiesVersusTotalTechnologies();
		Double ratioClosedsourceTechnologiesVersusTotalTechnologies = this.repository.getRatioClosedsourceTechnologiesVersusTotalTechnologies();

		// Chart 3
		Object[] totalNumberToolGroupByActivitySector = this.repository.getTotalNumberToolGroupByActivitySector();
		Object[] activitySectorToolsGroupByActivitySector = this.repository.getActivitySectorToolsGroupByActivitySector();

		// Chart 4

		Float ratioOpensourceToolsVersusTotalTools = this.repository.getRatioOpensourceToolsVersusTotalTools();
		Float ratioClosedsourceToolsVersusTotalTools = this.repository.getRatioClosedsourceToolsVersusTotalTools();

		// Chart 5

		Float ratioSeedInvestmentRoundGroupByKind = this.repository.getRatioSeedInvestmentRoundGroupByKind();
		Float ratioAngelInvestmentRoundGroupByKind = this.repository.getRatioAngelInvestmentRoundGroupByKind();
		Float ratioSeriesAInvestmentRoundGroupByKind = this.repository.getRatioSeriesAInvestmentRoundGroupByKind();
		Float ratioSeriesBInvestmentRoundGroupByKind = this.repository.getRatioSeriesBInvestmentRoundGroupByKind();
		Float ratioSeriesCInvestmentRoundGroupByKind = this.repository.getRatioSeriesCInvestmentRoundGroupByKind();
		Float ratioBridgeInvestmentRoundGroupByKind = this.repository.getRatioBridgeInvestmentRoundGroupByKind();

		// Chart 6
		Float ratioPendingApplicationsGroupByStatus = this.repository.getRatioPendingApplicationsGroupByStatus();
		Float ratioAcceptedApplicationsGroupByStatus = this.repository.getRatioAcceptedApplicationsGroupByStatus();
		Float ratioRejectedApplicationsGroupByStatus = this.repository.getRatioRejectedApplicationsGroupByStatus();

		// ----------------------------------------------------------------------------------------------

		// Chart 1

		res.setTotalNumberTechnologiesGroupByActivitySector(totalNumberTechnologiesGroupByActivitySector);
		res.setActivitySectorTechnologiesGroupByActivitySector(activitySectorTechnologiesGroupByActivitySector);

		// Chart 2

		res.setRatioOpensourceTechnologiesVersusTotalTechnologies(ratioOpensourceTechnologiesVersusTotalTechnologies);
		res.setRatioClosedsourceTechnologiesVersusTotalTechnologies(ratioClosedsourceTechnologiesVersusTotalTechnologies);

		// Chart 3

		res.setTotalNumberToolGroupByActivitySector(totalNumberToolGroupByActivitySector);
		res.setActivitySectorToolsGroupByActivitySector(activitySectorToolsGroupByActivitySector);

		// Chart 4

		res.setRatioOpensourceToolsVersusTotalTools(ratioOpensourceToolsVersusTotalTools);
		res.setRatioClosedsourceToolsVersusTotalTools(ratioClosedsourceToolsVersusTotalTools);

		// Chart 5

		res.setRatioSeedInvestmentRoundGroupByKind(ratioSeedInvestmentRoundGroupByKind);
		res.setRatioAngelInvestmentRoundGroupByKind(ratioAngelInvestmentRoundGroupByKind);
		res.setRatioSeriesAInvestmentRoundGroupByKind(ratioSeriesAInvestmentRoundGroupByKind);
		res.setRatioSeriesBInvestmentRoundGroupByKind(ratioSeriesBInvestmentRoundGroupByKind);
		res.setRatioSeriesCInvestmentRoundGroupByKind(ratioSeriesCInvestmentRoundGroupByKind);
		res.setRatioBridgeInvestmentRoundGroupByKind(ratioBridgeInvestmentRoundGroupByKind);

		// Chart 6

		res.setRatioPendingApplicationsGroupByStatus(ratioPendingApplicationsGroupByStatus);
		res.setRatioAcceptedApplicationsGroupByStatus(ratioAcceptedApplicationsGroupByStatus);
		res.setRatioRejectedApplicationsGroupByStatus(ratioRejectedApplicationsGroupByStatus);

		//-----------------------------------------------------------------------------------------------------

		return res;
	}

}
