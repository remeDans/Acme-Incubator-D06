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

package acme.features.entrepreneur.application;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import acme.components.SpamFilter;
import acme.entities.application.Application;
import acme.entities.roles.Entrepreneur;
import acme.features.administrator.customisation.AdministratorCustomisationRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class EntrepreneurApplicationUpdateService implements AbstractUpdateService<Entrepreneur, Application> {

	// Internal state ---------------------------------------------------------

	@Autowired
	EntrepreneurApplicationRepository		repository;

	@Autowired
	AdministratorCustomisationRepository	customisationRepository;


	//AbstractCreateService<Entrepreneur, Application> interface ---------------------

	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		boolean result;
		int applicationId;
		Application application;
		Entrepreneur entrepreneur;
		Principal principal;

		applicationId = request.getModel().getInteger("id");
		application = this.repository.findOneById(applicationId);
		entrepreneur = application.getInvestmentRound().getEntrepreneur();
		principal = request.getPrincipal();
		result = entrepreneur.getUserAccount().getId() == principal.getAccountId() && application.getStatus().equals("PENDING");

		return result;

	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "creationMoment", "status", "justification", "statement", "investmentMoneyOffer");

		//request.unbind(entity, model, "investor.userAccount.username", "investor.userAccount.identity.name");
		//request.unbind(entity, model, "investor.userAccount.identity.surname", "investor.userAccount.identity.email");
	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creationMoment");

	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean rejected = false;

		if (!errors.hasErrors("justification")) {
			if (entity.getStatus().equals("REJECTED") && entity.getJustification().isEmpty()) {
				rejected = true;
			}
			errors.state(request, !rejected, "justification", "entrepreneur.application.error.rejected.fill.justification");

		}

		// SPAM FILTER
		Double threshold = this.customisationRepository.findSpamThreshold();
		String spamWords_En = this.customisationRepository.findSpamWords_En();
		String spamWords_Es = this.customisationRepository.findSpamWords_Es();
		boolean isSpam = false;
		final String lang = LocaleContextHolder.getLocale().getLanguage();

		//Spam - justification
		if (!errors.hasErrors("justification")) {
			if (lang.equals("en")) {
				isSpam = SpamFilter.filterText(request.getModel().getString("justification"), spamWords_En, threshold);
			} else {
				isSpam = SpamFilter.filterText(request.getModel().getString("justification"), spamWords_Es, threshold);
			}
			errors.state(request, !isSpam, "justification", "entrepreneur.application.error.isSpam");
		}

	}

	@Override
	public Application findOne(final Request<Application> request) {

		assert request != null;

		Application result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;

	}

	@Override
	public void update(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationMoment(moment);

		this.repository.save(entity);

	}

}
