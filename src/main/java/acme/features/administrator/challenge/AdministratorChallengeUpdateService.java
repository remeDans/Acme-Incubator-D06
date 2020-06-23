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

package acme.features.administrator.challenge;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.challenges.Challenge;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorChallengeUpdateService implements AbstractUpdateService<Administrator, Challenge> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AdministratorChallengeRepository repository;


	@Override
	public boolean authorise(final Request<Challenge> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Challenge> request, final Challenge entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "description", "deadline", "goalExpertLevel", "rewardExpertLevel", "goalAverageLevel", "rewardAverageLevel", "goalRookieLevel", "rewardRookieLevel");
	}

	@Override
	public void bind(final Request<Challenge> request, final Challenge entity, final Errors errors) {

		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public Challenge findOne(final Request<Challenge> request) {
		assert request != null;

		Challenge result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findMOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Calendar calendar;
		Date minimumDeadline;

		if (!errors.hasErrors("deadline")) {
			calendar = new GregorianCalendar();
			calendar.add(Calendar.MONTH, 1);
			minimumDeadline = calendar.getTime();
			errors.state(request, entity.getDeadline().after(minimumDeadline), "deadline", "administrator.challenge.form.error.deadline");
		}
		//gold
		if (!errors.hasErrors("rewardExpertLevel")) {

			boolean assertCurrency = entity.getRewardExpertLevel().getCurrency().contentEquals("€") || entity.getRewardExpertLevel().getCurrency().contentEquals("EUR") || entity.getRewardExpertLevel().getCurrency().contentEquals("EURO")
				|| entity.getRewardExpertLevel().getCurrency().contentEquals("EUROS");
			errors.state(request, assertCurrency, "rewardExpertLevel", "administrator.challenge.error.currency.euro");
		}
		//silver
		if (!errors.hasErrors("rewardAverageLevel")) {

			boolean assertCurrency = entity.getRewardAverageLevel().getCurrency().contentEquals("€") || entity.getRewardAverageLevel().getCurrency().contentEquals("EUR") || entity.getRewardAverageLevel().getCurrency().contentEquals("EURO")
				|| entity.getRewardAverageLevel().getCurrency().contentEquals("EUROS");
			errors.state(request, assertCurrency, "rewardAverageLevel", "administrator.challenge.error.currency.euro");
		}
		//bronze
		if (!errors.hasErrors("rewardRookieLevel")) {

			boolean assertCurrency = entity.getRewardRookieLevel().getCurrency().contentEquals("€") || entity.getRewardRookieLevel().getCurrency().contentEquals("EUR") || entity.getRewardRookieLevel().getCurrency().contentEquals("EURO")
				|| entity.getRewardRookieLevel().getCurrency().contentEquals("EUROS");
			errors.state(request, assertCurrency, "rewardRookieLevel", "administrator.challenge.error.currency.euro");
		}

		if (!errors.hasErrors("rewardExpertLevel") && !errors.hasErrors("rewardAverageLevel") && !errors.hasErrors("rewardRookieLevel")) {

			boolean assertAmount = entity.getRewardRookieLevel().getAmount() < entity.getRewardAverageLevel().getAmount() && entity.getRewardRookieLevel().getAmount() < entity.getRewardExpertLevel().getAmount()
				&& entity.getRewardAverageLevel().getAmount() < entity.getRewardExpertLevel().getAmount() && entity.getRewardRookieLevel().getAmount() < entity.getRewardExpertLevel().getAmount();
			errors.state(request, assertAmount, "rewardRookieLevel", "administrator.challenge.error.Amount");
			errors.state(request, assertAmount, "rewardAverageLevel", "administrator.challenge.error.Amount");
			errors.state(request, assertAmount, "rewardExpertLevel", "administrator.challenge.error.Amount");

		}

	}

	@Override
	public void update(final Request<Challenge> request, final Challenge entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
