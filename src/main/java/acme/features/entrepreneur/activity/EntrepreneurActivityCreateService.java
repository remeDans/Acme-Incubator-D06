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
import acme.framework.services.AbstractCreateService;

@Service
public class EntrepreneurActivityCreateService implements AbstractCreateService<Entrepreneur, Activity> {

	// Internal state ---------------------------------------------------------

	@Autowired
	EntrepreneurActivityRepository			repository;

	@Autowired
	EntrepreneurInvestmentRoundRepository	investmentRoundRepository;

	@Autowired
	AdministratorCustomisationRepository	customisationRepository;


	@Override
	public boolean authorise(final Request<Activity> request) {
		assert request != null;

		boolean result;
		int investmentRoundId;
		InvestmentRound investmentRound;
		Entrepreneur entrepreneur;
		Principal principal;

		investmentRoundId = request.getModel().getInteger("roundId");
		investmentRound = this.investmentRoundRepository.findMOneById(investmentRoundId);
		entrepreneur = investmentRound.getEntrepreneur();
		principal = request.getPrincipal();
		result = entrepreneur.getUserAccount().getId() == principal.getAccountId() && investmentRound.getStatus().equals("DRAFT");

		return result;

		//return true;
	}

	@Override
	public Activity instantiate(final Request<Activity> request) {

		assert request != null;

		Activity res;

		res = new Activity();

		Integer investmentRoundId = request.getModel().getInteger("roundId");
		InvestmentRound i = this.investmentRoundRepository.findMOneById(investmentRoundId);

		if (i != null) {
			res.setInvestmentRound(i);
		}

		return res;
	}

	@Override
	public void unbind(final Request<Activity> request, final Activity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		Integer id = request.getModel().getInteger("roundId");
		model.setAttribute("roundId", id);

		request.unbind(entity, model, "title", "startMoment", "endMoment", "budget");

	}

	@Override
	public void bind(final Request<Activity> request, final Activity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "investmentRound");

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
			Integer investmentRoundId = request.getModel().getInteger("roundId");
			InvestmentRound i = this.investmentRoundRepository.findMOneById(investmentRoundId);

			Collection<Activity> activities = this.repository.findManyByInvestmentRound(investmentRoundId);
			Double sum = activities.stream().mapToDouble(j -> j.getBudget().getAmount()).sum();
			Double sum2 = entity.getBudget().getAmount() + sum;

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
	public void create(final Request<Activity> request, final Activity entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
