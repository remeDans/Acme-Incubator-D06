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

package acme.features.entrepreneur.investmentRound;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import acme.components.SpamFilter;
import acme.entities.activity.Activity;
import acme.entities.investmentRound.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.features.administrator.customisation.AdministratorCustomisationRepository;
import acme.features.entrepreneur.activity.EntrepreneurActivityRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class EntrepreneurInvestmentRoundCreateService implements AbstractCreateService<Entrepreneur, InvestmentRound> {

	// Internal state ---------------------------------------------------------

	@Autowired
	EntrepreneurInvestmentRoundRepository	repository;

	@Autowired
	EntrepreneurActivityRepository			activityRepository;

	@Autowired
	AdministratorCustomisationRepository	customisationRepository;


	@Override
	public boolean authorise(final Request<InvestmentRound> request) {
		assert request != null;

		boolean result;
		Entrepreneur entrepreneur;
		Principal principal;

		entrepreneur = this.repository.findOneEntrepreneurByEntrepreneurId(request.getPrincipal().getActiveRoleId());
		principal = request.getPrincipal();
		result = entrepreneur.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public InvestmentRound instantiate(final Request<InvestmentRound> request) {
		assert request != null;

		InvestmentRound result;
		Date moment;

		result = new InvestmentRound();

		moment = new Date(System.currentTimeMillis() - 1);
		result.setCreationMoment(moment);

		return result;
	}

	@Override
	public void unbind(final Request<InvestmentRound> request, final InvestmentRound entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "creationMoment", "deadline", "kindOfRound", "title", "description", "status", "link");
		request.unbind(entity, model, "amountOfMoney");

	}

	@Override
	public void bind(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Principal principal;

		principal = request.getPrincipal();
		int id = principal.getActiveRoleId();

		Entrepreneur p = this.repository.findOneEntrepreneurByEntrepreneurId(id);
		entity.setEntrepreneur(p);

		request.bind(entity, errors, "entrepreneur");
		//request.bind(entity, errors, "amountOfMoney");

	}

	@Override
	public void validate(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("status")) {
			boolean assertSumActivitiesMoney = false;
			if (entity.getStatus().equals("PUBLISHED")) {
				Collection<Activity> activities = this.activityRepository.findManyByInvestmentRound(entity.getId());
				if (activities.size() > 0) {
					Double sum = activities.stream().mapToDouble(j -> j.getBudget().getAmount()).sum();

					if (sum.equals(entity.getAmountOfMoney().getAmount())) {
						assertSumActivitiesMoney = true;
					}
				}

				errors.state(request, assertSumActivitiesMoney, "status", "entrepreneur.investment-round.error.activities");
			}

		}

		if (!errors.hasErrors("amountOfMoney")) {

			boolean assertCurrency = entity.getAmountOfMoney().getCurrency().contentEquals("€") || entity.getAmountOfMoney().getCurrency().contentEquals("EUR") || entity.getAmountOfMoney().getCurrency().contentEquals("EURO")
				|| entity.getAmountOfMoney().getCurrency().contentEquals("EUROS");
			errors.state(request, assertCurrency, "amountOfMoney", "entrepreneur.investment-round.error.currency.euro");
		}

		//validacion unico ticker
		InvestmentRound existing;

		if (!errors.hasErrors("ticker")) {
			existing = this.repository.findOneByTicker(entity.getTicker());
			errors.state(request, existing == null, "ticker", "entrepreneur.investment-round.form.error.ticker.duplicated");
		}

		Calendar calendar;
		Date minimumDeadline;

		if (!errors.hasErrors("deadline")) {
			calendar = new GregorianCalendar();
			//calendar.add(Calendar.DAY_OF_MONTH,7);
			minimumDeadline = calendar.getTime();
			errors.state(request, entity.getDeadline().after(minimumDeadline), "deadline", "entrepreneur.investment-round.form.error.deadline");
		}

		// SPAM FILTER("title", "description")
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
			errors.state(request, !isSpam, "title", "entrepreneur.investment-round.error.isSpam");
		}

		//Spam - description
		if (!errors.hasErrors("description")) {
			if (lang.equals("en")) {
				isSpam = SpamFilter.filterText(request.getModel().getString("description"), spamWords_En, threshold);
			} else {
				isSpam = SpamFilter.filterText(request.getModel().getString("description"), spamWords_Es, threshold);
			}
			errors.state(request, !isSpam, "description", "entrepreneur.investment-round.error.isSpam");
		}

	}

	@Override
	public void create(final Request<InvestmentRound> request, final InvestmentRound entity) {
		assert request != null;
		assert entity != null;

		Principal principal;
		Date moment;

		principal = request.getPrincipal();
		int id = principal.getActiveRoleId();

		Entrepreneur p = this.repository.findOneEntrepreneurByEntrepreneurId(id);
		entity.setEntrepreneur(p);

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationMoment(moment);

		this.repository.save(entity);

	}

}
