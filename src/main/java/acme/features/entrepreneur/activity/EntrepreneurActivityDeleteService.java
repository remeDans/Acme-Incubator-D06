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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.activity.Activity;
import acme.entities.investmentRound.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.features.entrepreneur.investmentRound.EntrepreneurInvestmentRoundRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class EntrepreneurActivityDeleteService implements AbstractDeleteService<Entrepreneur, Activity> {

	// Internal state ---------------------------------------------------------

	@Autowired
	EntrepreneurActivityRepository			repository;

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

		request.unbind(entity, model);
	}

	@Override
	public void bind(final Request<Activity> request, final Activity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "title", "startMoment", "endMoment", "budget");

	}

	@Override
	public Activity findOne(final Request<Activity> request) {
		assert request != null;

		Activity result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<Activity> request, final Activity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void delete(final Request<Activity> request, final Activity entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);

	}

}
