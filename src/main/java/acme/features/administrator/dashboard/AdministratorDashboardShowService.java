
package acme.features.administrator.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.Dashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, Dashboard> {

	@Autowired
	AdministratorDashboardRepository repository;


	@Override
	public boolean authorise(final Request<Dashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "totalNumberNotices", "totalNumberTechnologyRecords", "totalNumberToolRecords");
		request.unbind(entity, model, "minMoneyIntervalsActiveInquiries", "maxMoneyIntervalsActiveInquiries", "avgMoneyIntervalsActiveInquiries", "desvMoneyIntervalsActiveInquiries");
		request.unbind(entity, model, "minMoneyIntervalsActiveOvertures", "maxMoneyIntervalsActiveOvertures", "avgMoneyIntervalsActiveOvertures", "desvMoneyIntervalsActiveOvertures");
		//--------------------------------------------------------------
		request.unbind(entity, model, "avgNumberInventmentRoundPerEntrepreneur", "avgNumberApplicationPerEntrepreneur", "avgNumberApplicationPerInvestor");
	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {

		Dashboard res = new Dashboard();

		Integer totalNumberNotices = this.repository.getTotalNumberNotices();
		Integer totalNumberTechnologyRecords = this.repository.getTotalNumberTechnologyRecords();
		Integer totalNumberToolRecords = this.repository.getTotalNumberToolRecords();

		Double minMoneyIntervalsActiveInquiries = this.repository.getMinMoneyIntervalsActiveInquiries();
		Double maxMoneyIntervalsActiveInquiries = this.repository.getMaxMoneyIntervalsActiveInquiries();
		Double avgMoneyIntervalsActiveInquiries = this.repository.getAvgMoneyIntervalsActiveInquiries();
		Double desvMoneyIntervalsActiveInquiries = this.repository.getDesvMoneyIntervalsActiveInquiries();

		Double minMoneyIntervalsActiveOvertures = this.repository.getMinMoneyIntervalsActiveOvertures();
		Double maxMoneyIntervalsActiveOvertures = this.repository.getMaxMoneyIntervalsActiveOvertures();
		Double avgMoneyIntervalsActiveOvertures = this.repository.getAvgMoneyIntervalsActiveOvertures();
		Double desvMoneyIntervalsActiveOvertures = this.repository.getDesvMoneyIntervalsActiveOvertures();

		Double avgNumberInventmentRoundPerEntrepreneur = this.repository.getAvgNumberInventmentRoundPerEntrepreneur();
		Double avgNumberApplicationPerEntrepreneur = this.repository.getAvgNumberApplicationPerEntrepreneur();
		Double avgNumberApplicationPerInvestor = this.repository.getAavgNumberApplicationPerInvestor();

		res.setTotalNumberNotices(totalNumberNotices);
		res.setTotalNumberTechnologyRecords(totalNumberTechnologyRecords);
		res.setTotalNumberToolRecords(totalNumberToolRecords);

		res.setMinMoneyIntervalsActiveInquiries(minMoneyIntervalsActiveInquiries);
		res.setMaxMoneyIntervalsActiveInquiries(maxMoneyIntervalsActiveInquiries);
		res.setAvgMoneyIntervalsActiveInquiries(avgMoneyIntervalsActiveInquiries);
		res.setDesvMoneyIntervalsActiveInquiries(desvMoneyIntervalsActiveInquiries);

		res.setMinMoneyIntervalsActiveOvertures(minMoneyIntervalsActiveOvertures);
		res.setMaxMoneyIntervalsActiveOvertures(maxMoneyIntervalsActiveOvertures);
		res.setAvgMoneyIntervalsActiveOvertures(avgMoneyIntervalsActiveOvertures);
		res.setDesvMoneyIntervalsActiveOvertures(desvMoneyIntervalsActiveOvertures);

		res.setAvgNumberInventmentRoundPerEntrepreneur(avgNumberInventmentRoundPerEntrepreneur);
		res.setAvgNumberApplicationPerEntrepreneur(avgNumberApplicationPerEntrepreneur);
		res.setAvgNumberApplicationPerInvestor(avgNumberApplicationPerInvestor);

		return res;
	}

}
