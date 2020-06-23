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

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.activity.Activity;
import acme.entities.application.Application;
import acme.entities.forum.Forum;
import acme.entities.investmentRound.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.features.authenticated.activity.AuthenticateActivityRepository;
import acme.features.authenticated.application.AuthenticatedApplicationRepository;
import acme.features.authenticated.forum.AuthenticatedForumRepository;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class EntrepreneurInvestmentRoundShowService implements AbstractShowService<Entrepreneur, InvestmentRound> {

	// Internal state ---------------------------------------------------------

	@Autowired
	EntrepreneurInvestmentRoundRepository	repository;

	@Autowired
	AuthenticateActivityRepository			activityRepository;

	@Autowired
	AuthenticatedForumRepository			forumRepository;

	@Autowired
	AuthenticatedApplicationRepository		applicationRepository;


	//AbstractList<Authenticated, InvestmentRound> interface ---------------------

	@Override
	public boolean authorise(final Request<InvestmentRound> request) {
		assert request != null;

		return true;
	}

	@Override
	public InvestmentRound findOne(final Request<InvestmentRound> request) {//
		assert request != null;

		InvestmentRound result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findMOneById(id);

		return result;

	}

	@Override
	public void unbind(final Request<InvestmentRound> request, final InvestmentRound entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		InvestmentRound i;
		Integer InvestmentRoundId;

		InvestmentRoundId = entity.getId();
		i = this.repository.findMOneById(InvestmentRoundId);

		//------------------------------------
		/*
		 * model.setAttribute("hasAccountingRecords", false);
		 *
		 * if (i.getAccountingRecords().size() != 0) {
		 * model.setAttribute("hasAccountingRecords", true);
		 * }
		 */

		//-----------------------------------------------

		model.setAttribute("hasActivities", false);

		for (Activity a : this.activityRepository.findMany()) {

			Integer InvestmentRoundIdActivities = a.getInvestmentRound().getId();
			if (InvestmentRoundId.equals(InvestmentRoundIdActivities)) {
				model.setAttribute("hasActivities", true);
			}
		}

		// -----------------------------------------------

		model.setAttribute("hasForum", false);

		for (Forum f : this.forumRepository.findMany()) {

			Integer InvestmentRoundIdForum = f.getInvestmentRound().getId();
			if (InvestmentRoundId.equals(InvestmentRoundIdForum)) {
				model.setAttribute("hasForum", true);
			}
		}

		// -----------------------------------------------

		model.setAttribute("hasApplication", false);
		model.setAttribute("hasPendingStatusApplication", false);

		for (Application a1 : this.applicationRepository.findMany()) {

			Integer InvestmentRoundIdApplication = a1.getInvestmentRound().getId();
			if (InvestmentRoundId.equals(InvestmentRoundIdApplication)) {
				model.setAttribute("hasApplication", true);
				if (a1.getStatus().equals("PENDING")) {
					model.setAttribute("hasPendingStatusApplication", true);
				}
			}
		}

		//------------------------------------------------------

		model.setAttribute("isCompletedSumActivitiesMoney", false);

		Collection<Activity> activities = this.activityRepository.findManyByInvestmentRound(InvestmentRoundId);
		Double sum = activities.stream().mapToDouble(j -> j.getBudget().getAmount()).sum();
		Double sum2 = sum;

		if (sum2.equals(i.getAmountOfMoney().getAmount())) {
			model.setAttribute("isCompletedSumActivitiesMoney", true);
		}

		//-----------------------------------------------

		request.unbind(entity, model, "ticker", "creationMoment", "deadline", "kindOfRound", "title", "description", "amountOfMoney", "status", "link");

		request.unbind(entity, model, "entrepreneur.userAccount.username", "entrepreneur.userAccount.identity.name");
		request.unbind(entity, model, "entrepreneur.userAccount.identity.surname", "entrepreneur.userAccount.identity.email");

	}

}
