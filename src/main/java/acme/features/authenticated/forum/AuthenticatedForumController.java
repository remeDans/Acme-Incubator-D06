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

package acme.features.authenticated.forum;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.forum.Forum;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/forum/")
public class AuthenticatedForumController extends AbstractController<Authenticated, Forum> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedForumListMineService				listMineService;

	@Autowired
	private AuthenticatedForumShowService					showService;

	@Autowired
	private AuthenticatedForumShowInvestmentRoundService	showInvestmentRoundService;

	@Autowired
	private AuthenticatedForumCreateService					createService;

	@Autowired
	private AuthenticatedForumDeleteService					deleteService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addCustomCommand(CustomCommand.SHOW_FORUM_INVESTMENT_ROUND, BasicCommand.SHOW, this.showInvestmentRoundService);
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listMineService);

		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
	}

}
