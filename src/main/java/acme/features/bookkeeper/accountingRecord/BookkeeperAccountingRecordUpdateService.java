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
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import acme.components.SpamFilter;
import acme.entities.accountingRecord.AccountingRecord;
import acme.entities.roles.Bookkeeper;
import acme.features.administrator.customisation.AdministratorCustomisationRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class BookkeeperAccountingRecordUpdateService implements AbstractUpdateService<Bookkeeper, AccountingRecord> {

	// Internal state ---------------------------------------------------------

	@Autowired
	BookeeperAccountingRecordRepository		repository;

	@Autowired
	AdministratorCustomisationRepository	customisationRepository;


	@Override
	public boolean authorise(final Request<AccountingRecord> request) {
		assert request != null;

		boolean result;
		int accountingRecordId;
		AccountingRecord accountingRecord;
		Bookkeeper bookkeeper;
		Principal principal;

		accountingRecordId = request.getModel().getInteger("id");
		accountingRecord = this.repository.findMOneById(accountingRecordId);
		bookkeeper = accountingRecord.getBookkeeper();
		principal = request.getPrincipal();
		result = bookkeeper.getUserAccount().getId() == principal.getAccountId() && accountingRecord.getStatus().equals("DRAFT");

		return result;

	}

	@Override
	public void unbind(final Request<AccountingRecord> request, final AccountingRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "status", "creationMoment", "body");

	}

	@Override
	public void bind(final Request<AccountingRecord> request, final AccountingRecord entity, final Errors errors) {

		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public AccountingRecord findOne(final Request<AccountingRecord> request) {
		assert request != null;

		AccountingRecord result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findMOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<AccountingRecord> request, final AccountingRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		// SPAM FILTER("title", "body")
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
			errors.state(request, !isSpam, "title", "bookkeeper.accounting-record.error.isSpam");
		}

		//Spam - description
		if (!errors.hasErrors("body")) {
			if (lang.equals("en")) {
				isSpam = SpamFilter.filterText(request.getModel().getString("body"), spamWords_En, threshold);
			} else {
				isSpam = SpamFilter.filterText(request.getModel().getString("body"), spamWords_Es, threshold);
			}
			errors.state(request, !isSpam, "body", "bookkeeper.accounting-record.error.isSpam");
		}

	}

	@Override
	public void update(final Request<AccountingRecord> request, final AccountingRecord entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
