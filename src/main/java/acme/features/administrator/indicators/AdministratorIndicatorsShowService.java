
package acme.features.administrator.indicators;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.Indicators;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorIndicatorsShowService implements AbstractShowService<Administrator, Indicators> {

	@Autowired
	private AdministratorIndicatorsRepository repository;


	@Override
	public boolean authorise(final Request<Indicators> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Indicators> request, final Indicators entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "pendingApplicationsPerDay", "acceptedApplicationsPerDay", "rejectedApplicationsPerDay");

	}

	@Override
	public Indicators findOne(final Request<Indicators> request) {
		Indicators res = new Indicators();

		Date fifteenDaysAgo = DateUtils.addDays(new Date(), -15);
		res.setPendingApplicationsPerDay(this.repository.findPendingApplicationsPerDay(fifteenDaysAgo));
		res.setAcceptedApplicationsPerDay(this.repository.findAcceptedApplicationsPerDay(fifteenDaysAgo));
		res.setRejectedApplicationsPerDay(this.repository.findRejectedApplicationsPerDay(fifteenDaysAgo));

		return res;
	}

}
