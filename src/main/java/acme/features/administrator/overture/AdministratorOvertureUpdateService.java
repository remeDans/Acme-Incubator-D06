/*
 * AnonymousUserAccountCreateService.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.overture;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.overture.Overture;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorOvertureUpdateService implements AbstractUpdateService<Administrator, Overture> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AdministratorOvertureRepository repository;


	@Override
	public boolean authorise(final Request<Overture> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Overture> request, final Overture entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "creationMoment", "deadline", "paragraphsDescription", "minIntervalMoney", "maxIntervalMoney", "contactEmail");
		//request.unbind(entity, model,"intervalMoney")
	}

	@Override
	public void bind(final Request<Overture> request, final Overture entity, final Errors errors) {

		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public Overture findOne(final Request<Overture> request) {
		assert request != null;

		Overture result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findMOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<Overture> request, final Overture entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Calendar calendar;
		Date minimumDeadline;

		if (!errors.hasErrors("deadline")) {
			calendar = new GregorianCalendar();
			//calendar.add(Calendar.DAY_OF_MONTH,7);
			minimumDeadline = calendar.getTime();
			errors.state(request, entity.getDeadline().after(minimumDeadline), "deadline", "administrator.overture.form.error.deadline");
		}

		if (!errors.hasErrors("minIntervalMoney")) {

			boolean assertCurrency = entity.getMinIntervalMoney().getCurrency().contentEquals("€") || entity.getMinIntervalMoney().getCurrency().contentEquals("EUR") || entity.getMinIntervalMoney().getCurrency().contentEquals("EURO")
				|| entity.getMinIntervalMoney().getCurrency().contentEquals("EUROS");
			errors.state(request, assertCurrency, "minIntervalMoney", "administrator.overture.error.currency.euro");
		}

		if (!errors.hasErrors("maxIntervalMoney")) {

			boolean assertCurrency = entity.getMaxIntervalMoney().getCurrency().contentEquals("€") || entity.getMaxIntervalMoney().getCurrency().contentEquals("EUR") || entity.getMaxIntervalMoney().getCurrency().contentEquals("EURO")
				|| entity.getMaxIntervalMoney().getCurrency().contentEquals("EUROS");
			;
			errors.state(request, assertCurrency, "maxIntervalMoney", "administrator.overture.error.currency.euro");
		}

		if (!errors.hasErrors("minIntervalMoney") && !errors.hasErrors("maxIntervalMoney")) {

			boolean assertAmount = entity.getMinIntervalMoney().getAmount() < entity.getMaxIntervalMoney().getAmount();
			errors.state(request, assertAmount, "minIntervalMoney", "administrator.overture.error.Amount");
			errors.state(request, assertAmount, "maxIntervalMoney", "administrator.overture.error.Amount");

		}

	}

	@Override
	public void update(final Request<Overture> request, final Overture entity) {
		assert request != null;
		assert entity != null;

		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationMoment(moment);

		this.repository.save(entity);

	}

}
