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

package acme.features.entrepreneur.activity;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.activity.Activity;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/entrepreneur/activity/")
public class EntrepreneurActivityController extends AbstractController<Entrepreneur, Activity> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private EntrepreneurActivityListService					listService;

	@Autowired
	private EntrepreneurActivityListInvestmentRoundService	listInvestmentRoundService;

	@Autowired
	private EntrepreneurActivityShowService					showService;

	@Autowired
	private EntrepreneurActivityCreateService				createService;

	@Autowired
	private EntrepreneurActivityDeleteService				deleteService;

	@Autowired
	private EntrepreneurActivityUpdateService				updateService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addCustomCommand(CustomCommand.LIST_ACTIVITY_INVESTMENT_ROUND, BasicCommand.LIST, this.listInvestmentRoundService);

		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
	}

}
