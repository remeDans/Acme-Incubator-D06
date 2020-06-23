/*
 * AnonymousUserAccountController.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.entrepreneur.accountingRecord;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.accountingRecord.AccountingRecord;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/entrepreneur/accounting-record/")
public class EntrepreneurAccountingRecordController extends AbstractController<Entrepreneur, AccountingRecord> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private EntrepreneurAccountingRecordListInvestmentRoundService	listInvestmentRoundService;

	@Autowired
	private EntrepreneurAccountingRecordShowService					showService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addCustomCommand(CustomCommand.LIST_ACCOUNTING_RECORD_INVESTMENT_ROUND, BasicCommand.LIST, this.listInvestmentRoundService);
	}

}
