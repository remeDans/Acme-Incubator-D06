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

package acme.features.entrepreneur.forum;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.forum.Forum;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/entrepreneur/forum/")
public class EntrepreneurForumController extends AbstractController<Entrepreneur, Forum> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private EntrepreneurForumShowService				showService;

	@Autowired
	private EntrepreneurForumShowInvestmentRoundService	showInvestmentRoundService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.SHOW_FORUM_INVESTMENT_ROUND, BasicCommand.SHOW, this.showInvestmentRoundService);
	}

}
