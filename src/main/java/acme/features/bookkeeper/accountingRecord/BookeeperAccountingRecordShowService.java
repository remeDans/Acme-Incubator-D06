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

package acme.features.bookkeeper.accountingRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.accountingRecord.AccountingRecord;
import acme.entities.roles.Bookkeeper;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class BookeeperAccountingRecordShowService implements AbstractShowService<Bookkeeper, AccountingRecord> {

	// Internal state ---------------------------------------------------------

	@Autowired
	BookeeperAccountingRecordRepository repository;


	//AbstractList<Authenticated, InvestmentRound> interface ---------------------

	@Override
	public boolean authorise(final Request<AccountingRecord> request) {
		assert request != null;

		return true;
	}

	@Override
	public AccountingRecord findOne(final Request<AccountingRecord> request) {//
		assert request != null;

		AccountingRecord result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findMOneById(id);

		return result;

	}

	@Override
	public void unbind(final Request<AccountingRecord> request, final AccountingRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "status", "creationMoment", "body");

		request.unbind(entity, model, "bookkeeper.userAccount.username", "bookkeeper.userAccount.identity.name");
		request.unbind(entity, model, "bookkeeper.userAccount.identity.surname", "bookkeeper.userAccount.identity.email");

	}

}
