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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.application.Application;
import acme.entities.investmentRound.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.features.entrepreneur.activity.EntrepreneurActivityRepository;
import acme.features.entrepreneur.application.EntrepreneurApplicationRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class EntrepreneurInvestmentRoundDeleteService implements AbstractDeleteService<Entrepreneur, InvestmentRound> {

	// Internal state ---------------------------------------------------------

	@Autowired
	EntrepreneurInvestmentRoundRepository	repository;

	@Autowired
	EntrepreneurApplicationRepository		applicationRepository;

	@Autowired
	EntrepreneurActivityRepository			activityRepository;


	@Override
	public boolean authorise(final Request<InvestmentRound> request) {
		assert request != null;

		boolean result;
		Integer investmentRoundId;
		InvestmentRound investmentRound;
		Entrepreneur entrepreneur;
		Principal principal;

		investmentRoundId = request.getModel().getInteger("id");
		investmentRound = this.repository.findMOneById(investmentRoundId);
		entrepreneur = investmentRound.getEntrepreneur();
		principal = request.getPrincipal();

		result = entrepreneur.getUserAccount().getId() == principal.getAccountId() && !this.hasApplications(investmentRoundId);

		return result;

	}

	public boolean hasApplications(final Integer investmentRoundId) {

		boolean result = false;
		for (Application a1 : this.applicationRepository.findMany()) {

			Integer InvestmentRoundIdApplication = a1.getInvestmentRound().getId();
			if (investmentRoundId.equals(InvestmentRoundIdApplication)) {
				result = true;
				break;
			}
		}

		return result;
	}

	@Override
	public void unbind(final Request<InvestmentRound> request, final InvestmentRound entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "creationMoment", "deadline", "kindOfRound", "title", "description", "amountOfMoney", "link");
	}

	@Override
	public void bind(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		int investmentRoundId = request.getModel().getInteger("id");
		Application a = this.applicationRepository.findOneByInvestmentRoundId(investmentRoundId);

		request.bind(entity, errors, "a");

	}

	@Override
	public InvestmentRound findOne(final Request<InvestmentRound> request) {
		assert request != null;

		InvestmentRound result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findMOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void delete(final Request<InvestmentRound> request, final InvestmentRound entity) {
		assert request != null;
		assert entity != null;

		this.repository.deleteAll(this.activityRepository.findManyByInvestmentRound(entity.getId()));
		this.repository.delete(entity);

	}

}
