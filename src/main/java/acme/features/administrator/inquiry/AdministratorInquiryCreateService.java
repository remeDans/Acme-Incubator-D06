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

package acme.features.administrator.inquiry;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.inquiry.Inquiry;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorInquiryCreateService implements AbstractCreateService<Administrator, Inquiry> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AdministratorInquiryRepository repository;


	@Override
	public boolean authorise(final Request<Inquiry> request) {
		assert request != null;

		return true;
	}

	@Override
	public Inquiry instantiate(final Request<Inquiry> request) {
		assert request != null;

		Inquiry result;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		result = new Inquiry();

		result.setCreationMoment(moment);

		return result;
	}

	@Override
	public void unbind(final Request<Inquiry> request, final Inquiry entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "creationMoment", "deadline", "paragraphsDescription", "minIntervalMoney", "maxIntervalMoney", "contactEmail");
		//request.unbind(entity, model,"intervalMoney");

	}

	@Override
	public void bind(final Request<Inquiry> request, final Inquiry entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creationMoment");

	}

	@Override
	public void validate(final Request<Inquiry> request, final Inquiry entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Calendar calendar;
		Date minimumDeadline;

		if (!errors.hasErrors("deadline")) {
			calendar = new GregorianCalendar();
			//calendar.add(Calendar.DAY_OF_MONTH,7);
			minimumDeadline = calendar.getTime();
			errors.state(request, entity.getDeadline().after(minimumDeadline), "deadline", "administrator.inquiry.form.error.deadline");
		}

		if (!errors.hasErrors("minIntervalMoney")) {

			boolean assertCurrency = entity.getMinIntervalMoney().getCurrency().contentEquals("€") || entity.getMinIntervalMoney().getCurrency().contentEquals("EUR") || entity.getMinIntervalMoney().getCurrency().contentEquals("EURO")
				|| entity.getMinIntervalMoney().getCurrency().contentEquals("EUROS");
			errors.state(request, assertCurrency, "minIntervalMoney", "administrator.inquiry.error.currency.euro");
		}

		if (!errors.hasErrors("maxIntervalMoney")) {

			boolean assertCurrency = entity.getMaxIntervalMoney().getCurrency().contentEquals("€") || entity.getMaxIntervalMoney().getCurrency().contentEquals("EUR") || entity.getMaxIntervalMoney().getCurrency().contentEquals("EURO")
				|| entity.getMaxIntervalMoney().getCurrency().contentEquals("EUROS");
			errors.state(request, assertCurrency, "maxIntervalMoney", "administrator.inquiry.error.currency.euro");
		}

		if (!errors.hasErrors("minIntervalMoney") && !errors.hasErrors("maxIntervalMoney")) {

			boolean assertAmount = entity.getMinIntervalMoney().getAmount() < entity.getMaxIntervalMoney().getAmount();
			errors.state(request, assertAmount, "minIntervalMoney", "administrator.inquiry.error.Amount");
			errors.state(request, assertAmount, "maxIntervalMoney", "administrator.inquiry.error.Amount");

		}

	}

	@Override
	public void create(final Request<Inquiry> request, final Inquiry entity) {
		assert request != null;
		assert entity != null;

		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationMoment(moment);

		this.repository.save(entity);

	}

}
