
package acme.features.administrator.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	@Query("Select Count(n) from Notice n")
	Integer getTotalNumberNotices();
	@Query("Select Count(t) from TechnologyRecord t")
	Integer getTotalNumberTechnologyRecords();
	@Query("Select Count(t) from ToolRecord t")
	Integer getTotalNumberToolRecords();

	@Query("select min((i.maxIntervalMoney.amount)-(i.minIntervalMoney.amount)) from Inquiry i where i.deadline > current_date()")
	Double getMinMoneyIntervalsActiveInquiries();

	@Query("select max((i.maxIntervalMoney.amount)-(i.minIntervalMoney.amount)) from Inquiry i where i.deadline > current_date()")
	Double getMaxMoneyIntervalsActiveInquiries();

	@Query("select avg((i.maxIntervalMoney.amount)-(i.minIntervalMoney.amount)) from Inquiry i where i.deadline > current_date()")
	Double getAvgMoneyIntervalsActiveInquiries();

	@Query("select stddev((i.maxIntervalMoney.amount)-(i.minIntervalMoney.amount)) from Inquiry i where i.deadline > current_date()")
	Double getDesvMoneyIntervalsActiveInquiries();

	@Query("select min((o.maxIntervalMoney.amount)-(o.minIntervalMoney.amount)) from Overture o where o.deadline > current_date()")
	Double getMinMoneyIntervalsActiveOvertures();

	@Query("select max((o.maxIntervalMoney.amount)-(o.minIntervalMoney.amount)) from Overture o where o.deadline > current_date()")
	Double getMaxMoneyIntervalsActiveOvertures();

	@Query("select avg((o.maxIntervalMoney.amount)-(o.minIntervalMoney.amount)) from Overture o where o.deadline > current_date()")
	Double getAvgMoneyIntervalsActiveOvertures();

	@Query("select stddev((o.maxIntervalMoney.amount)-(o.minIntervalMoney.amount)) from Overture o where o.deadline > current_date()")
	Double getDesvMoneyIntervalsActiveOvertures();

	//---------------------------------------------

	@Query("select avg(select count(i) from InvestmentRound i where i.entrepreneur.id = ua.id) from Entrepreneur ua")
	Double getAvgNumberInventmentRoundPerEntrepreneur();

	@Query("select avg(select count(a) from Application a join a.investmentRound i where i.entrepreneur.id = ua.id) from Entrepreneur ua")
	Double getAvgNumberApplicationPerEntrepreneur();

	@Query("select avg(select count(a) from Application a where a.investor.id = ua.id) from Investor ua")
	Double getAavgNumberApplicationPerInvestor();

}
