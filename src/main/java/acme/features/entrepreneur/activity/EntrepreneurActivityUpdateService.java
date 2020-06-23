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

package acme.features.entrepreneur.activity;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import acme.components.SpamFilter;
import acme.entities.activity.Activity;
import acme.entities.investmentRound.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.features.administrator.customisation.AdministratorCustomisationRepository;
import acme.features.entrepreneur.investmentRound.EntrepreneurInvestmentRoundRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class EntrepreneurActivityUpdateService implements AbstractUpdateService<Entrepreneur, Activity> {

	// Internal state ---------------------------------------------------------

	@Autowired
	EntrepreneurActivityRepository			repository;

	@Autowired
	AdministratorCustomisationRepository	customisationRepository;

	@Autowired
	EntrepreneurInvestmentRoundRepository	investmentRoundRepository;


	@Override
	public boolean authorise(final Request<Activity> request) {
		assert request != null;

		boolean result;
		InvestmentRound investmentRound;
		int activityId;
		Entrepreneur entrepreneur;
		Principal principal;

		activityId = request.getModel().getInteger("id");
		investmentRound = this.investmentRoundRepository.findOneByActivityId(activityId);
		entrepreneur = investmentRound.getEntrepreneur();
		principal = request.getPrincipal();
		result = investmentRound.getStatus().equals("DRAFT");//entrepreneur.getUserAccount().getId() == principal.getAccountId() &&

		return result;

		//return true;
	}

	@Override
	public void unbind(final Request<Activity> request, final Activity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "startMoment", "endMoment", "budget");
	}

	@Override
	public void bind(final Request<Activity> request, final Activity entity, final Errors errors) {

		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public Activity findOne(final Request<Activity> request) {
		assert request != null;

		Activity result;
		int idActivity;

		idActivity = request.getModel().getInteger("id");
		result = this.repository.findOneById(idActivity);

		return result;
	}

	@Override
	public void validate(final Request<Activity> request, final Activity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Date deadlineInvestmentRound;

		if (!errors.hasErrors("startMoment")) {

			deadlineInvestmentRound = entity.getInvestmentRound().getDeadline();

			errors.state(request, entity.getStartMoment().after(deadlineInvestmentRound), "startMoment", "entrepreneur.activity.form.error.startMoment");
		}

		if (!errors.hasErrors("endMoment")) {
			errors.state(request, entity.getEndMoment().after(entity.getStartMoment()), "endMoment", "entrepreneur.activity.form.error.endMoment");
		}

		if (!errors.hasErrors("budget")) {

			boolean assertSumActivitiesMoney = false;
			Integer activityId = request.getModel().getInteger("id");
			InvestmentRound i = this.investmentRoundRepository.findOneByActivityId(activityId);

			Collection<Activity> activities = this.repository.findManyByInvestmentRound(i.getId());
			Double sum = activities.stream().mapToDouble(j -> j.getBudget().getAmount()).sum();
			Double sum2 = entity.getBudget().getAmount();

			if (sum2 > i.getAmountOfMoney().getAmount()) {
				assertSumActivitiesMoney = true;

			}
			errors.state(request, !assertSumActivitiesMoney, "budget", "entrepreneur.activity.error.budget");

			boolean assertCurrency = entity.getBudget().getCurrency().contentEquals("€") || entity.getBudget().getCurrency().contentEquals("EUR") || entity.getBudget().getCurrency().contentEquals("EURO")
				|| entity.getBudget().getCurrency().contentEquals("EUROS");
			errors.state(request, assertCurrency, "budget", "entrepreneur.activity.error.currency.euro");
		}

		// SPAM FILTER("title")
		Double threshold = this.customisationRepository.findSpamThreshold();
		String spamWords_En = this.customisationRepository.findSpamWords_En();
		String spamWords_Es = this.customisationRepository.findSpamWords_Es();
		boolean isSpam = false;
		final String lang = LocaleContextHolder.getLocale().getLanguage();

		//Spam - title
		if (!errors.hasErrors("title")) {
			if (lang.equals("en")) {
				isSpam = SpamFilter.filterText(request.getModel().getString("title"), spamWords_En, threshold);
			} else {
				isSpam = SpamFilter.filterText(request.getModel().getString("title"), spamWords_Es, threshold);
			}
			errors.state(request, !isSpam, "title", "entrepreneur.activity.error.isSpam");
		}

	}

	@Override
	public void update(final Request<Activity> request, final Activity entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
