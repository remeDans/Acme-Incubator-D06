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
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class EntrepreneurActivityShowService implements AbstractShowService<Entrepreneur, Activity> {

	// Internal state ---------------------------------------------------------

	@Autowired
	EntrepreneurActivityRepository			repository;

	@Autowired
	EntrepreneurInvestmentRoundRepository	investmentRoundRepository;


	//AbstractList<Authenticated, InvestmentRound> interface ---------------------

	@Override
	public boolean authorise(final Request<Activity> request) {
		assert request != null;

		return true;
	}

	@Override
	public Activity findOne(final Request<Activity> request) {//
		assert request != null;

		Activity result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;

	}

	@Override
	public void unbind(final Request<Activity> request, final Activity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		Integer activityId = request.getModel().getInteger("id");
		InvestmentRound investmentRound = this.investmentRoundRepository.findOneByActivityId(activityId);

		model.setAttribute("investmentRoundIsDRAFT", false);

		if (investmentRound.getStatus().equals("DRAFT")) {
			model.setAttribute("investmentRoundIsDRAFT", true);
		}

		request.unbind(entity, model, "title", "startMoment", "endMoment", "budget");

	}

}
