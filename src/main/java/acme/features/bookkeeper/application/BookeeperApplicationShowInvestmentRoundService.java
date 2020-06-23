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

package acme.features.bookkeeper.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.application.Application;
import acme.entities.roles.Bookkeeper;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class BookeeperApplicationShowInvestmentRoundService implements AbstractShowService<Bookkeeper, Application> {

	// Internal state ---------------------------------------------------------

	@Autowired
	BookeeperApplicationRepository repository;


	//AbstractList<Administrator, Announcement> interface ---------------------

	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		boolean result = true;

		/*
		 * int applicationId;
		 * Application application;
		 * Worker worker;
		 * Principal principal;
		 *
		 * applicationId = request.getModel().getInteger("id");
		 * application = this.repository.findOneById(applicationId);
		 * worker = application.getWorker();
		 * principal = request.getPrincipal();
		 * result = application.getStatus() == "ACCEPTED" || !(application.getStatus() == "ACCEPTED") && worker.getUserAccount().getId() == principal.getAccountId();
		 */

		return result;
	}

	@Override
	public Application findOne(final Request<Application> request) {//
		assert request != null;

		Application result;
		int id;

		id = request.getModel().getInteger("investmentId");
		result = this.repository.findOneByInvestmentRoundId(id);

		return result;
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "creationMoment", "status", "justification", "statement", "investmentMoneyOffer");

		request.unbind(entity, model, "investor.userAccount.username", "investor.userAccount.identity.name");
		request.unbind(entity, model, "investor.userAccount.identity.surname", "investor.userAccount.identity.email");

	}

}
