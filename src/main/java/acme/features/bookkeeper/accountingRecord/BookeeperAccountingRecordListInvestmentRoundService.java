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

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.accountingRecord.AccountingRecord;
import acme.entities.roles.Bookkeeper;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class BookeeperAccountingRecordListInvestmentRoundService implements AbstractListService<Bookkeeper, AccountingRecord> {

	// Internal state ---------------------------------------------------------

	@Autowired
	BookeeperAccountingRecordRepository repository;


	@Override
	public boolean authorise(final Request<AccountingRecord> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<AccountingRecord> findMany(final Request<AccountingRecord> request) {//
		assert request != null;

		Collection<AccountingRecord> result;

		//int investmentRoundID = request.getModel().getInteger("id");

		int investmentRoundID = request.getModel().getInteger("investmentId");

		result = this.repository.findManyByInvestmentRound(investmentRoundID);

		//result = this.repository.findMany();

		return result;

	}

	@Override
	public void unbind(final Request<AccountingRecord> request, final AccountingRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "status", "creationMoment");

	}

}
