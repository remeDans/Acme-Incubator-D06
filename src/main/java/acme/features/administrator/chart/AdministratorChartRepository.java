
package acme.features.administrator.chart;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorChartRepository extends AbstractRepository {

	//------ CHART 1 ---------------------------------------------------------
	@Query("select t.activitySector, Count(t) from TechnologyRecord t group by t.activitySector order by t.activitySector ")
	Object[] getTotalNumberTechnologiesGroupByActivitySector();

	@Query("select t.activitySector from TechnologyRecord t group by t.activitySector order by t.activitySector")
	Object[] getActivitySectorTechnologiesGroupByActivitySector();

	//--------- CHART 2 ---------------------------------------------------------------

	@Query("select 1.0 * count(t) / (select cast(count(t1) as float ) from TechnologyRecord t1) from TechnologyRecord t where t.openSource=true")
	Double getRatioOpensourceTechnologiesVersusTotalTechnologies();

	@Query("select 1.0* count(t) / (select cast(count(t1) as float ) from TechnologyRecord t1) from TechnologyRecord t where t.openSource=false")
	Double getRatioClosedsourceTechnologiesVersusTotalTechnologies();

	//----- CHART 3 ----------------------------------------------------------------------

	@Query("Select t.activitySector, Count(t) from ToolRecord t group by t.activitySector order by t.activitySector ")
	Object[] getTotalNumberToolGroupByActivitySector();

	@Query("Select t.activitySector from ToolRecord t group by t.activitySector order by t.activitySector")
	Object[] getActivitySectorToolsGroupByActivitySector();

	//--- CHART 4 ----------------------------------------------------------------

	@Query("select 1.0 * count(t) / (select cast(count(t1) as float ) from ToolRecord t1) from ToolRecord t where t.openSource=true")
	Float getRatioOpensourceToolsVersusTotalTools();

	@Query("select 1.0* count(t) / (select cast(count(t1) as float ) from ToolRecord t1) from ToolRecord t where t.openSource=false")
	Float getRatioClosedsourceToolsVersusTotalTools();

	//--- CHART 5 -------------------------------------------------------------------

	@Query("select 1.0 * count(a) / (select count(b) from InvestmentRound b) from InvestmentRound a where a.kindOfRound = 'SEED'")
	Float getRatioSeedInvestmentRoundGroupByKind();

	@Query("select 1.0 * count(a) / (select count(b) from InvestmentRound b) from InvestmentRound a where a.kindOfRound = 'ANGEL'")
	Float getRatioAngelInvestmentRoundGroupByKind();

	@Query("select 1.0 * count(a) / (select count(b) from InvestmentRound b) from InvestmentRound a where a.kindOfRound = 'SERIES-A'")
	Float getRatioSeriesAInvestmentRoundGroupByKind();

	@Query("select 1.0 * count(a) / (select count(b) from InvestmentRound b) from InvestmentRound a where a.kindOfRound = 'SERIES-B'")
	Float getRatioSeriesBInvestmentRoundGroupByKind();

	@Query("select 1.0 * count(a) / (select count(b) from InvestmentRound b) from InvestmentRound a where a.kindOfRound = 'SERIES-C'")
	Float getRatioSeriesCInvestmentRoundGroupByKind();

	@Query("select 1.0 * count(a) / (select count(b) from InvestmentRound b) from InvestmentRound a where a.kindOfRound = 'BRIDGE'")
	Float getRatioBridgeInvestmentRoundGroupByKind();

	//--- CHART 6 -------------------------------------------------------------------
	@Query("select 1.0 * count(a) / (select count(b) from Application b) from Application a where a.status = 'PENDING'")
	Float getRatioPendingApplicationsGroupByStatus();

	@Query("select 1.0 * count(a) / (select count(b) from Application b) from Application a where a.status = 'ACCEPTED'")
	Float getRatioAcceptedApplicationsGroupByStatus();

	@Query("select 1.0 * count(a) / (select count(b) from Application b) from Application a where a.status = 'REJECTED'")
	Float getRatioRejectedApplicationsGroupByStatus();

}
