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

package acme.features.bookkeeper.investmentRound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.accountingRecord.AccountingRecord;
import acme.entities.activity.Activity;
import acme.entities.application.Application;
import acme.entities.forum.Forum;
import acme.entities.investmentRound.InvestmentRound;
import acme.entities.roles.Bookkeeper;
import acme.features.authenticated.activity.AuthenticateActivityRepository;
import acme.features.authenticated.application.AuthenticatedApplicationRepository;
import acme.features.authenticated.forum.AuthenticatedForumRepository;
import acme.features.bookkeeper.accountingRecord.BookeeperAccountingRecordRepository;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class BookeeperInvestmentRoundShowService implements AbstractShowService<Bookkeeper, InvestmentRound> {

	// Internal state ---------------------------------------------------------

	@Autowired
	BookeeperInvestmentRoundRepository	repository;

	@Autowired
	AuthenticateActivityRepository		activityRepository;

	@Autowired
	AuthenticatedApplicationRepository	applicationRepository;

	@Autowired
	AuthenticatedForumRepository		forumRepository;

	@Autowired
	BookeeperAccountingRecordRepository	accountingRecordRepository;


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

		model.setAttribute("hasAccountingRecords", false);

		for (AccountingRecord a : this.accountingRecordRepository.findMany()) {

			Integer InvestmentRoundIdAccounting = a.getInvestmentRound().getId();
			if (InvestmentRoundId.equals(InvestmentRoundIdAccounting)) {
				model.setAttribute("hasAccountingRecords", true);
			}
		}

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

		for (Application a1 : this.applicationRepository.findMany()) {

			Integer InvestmentRoundIdApplication = a1.getInvestmentRound().getId();
			if (InvestmentRoundId.equals(InvestmentRoundIdApplication)) {
				model.setAttribute("hasApplication", true);
			}
		}

		//-----------------------------------------------

		request.unbind(entity, model, "ticker", "creationMoment", "deadline", "kindOfRound", "title", "description", "amountOfMoney", "link");
		request.unbind(entity, model, "status");

		request.unbind(entity, model, "entrepreneur.userAccount.username", "entrepreneur.userAccount.identity.name");
		request.unbind(entity, model, "entrepreneur.userAccount.identity.surname", "entrepreneur.userAccount.identity.email");

	}

}
