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

package acme.features.administrator.challenge;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.challenges.Challenge;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/challenge/")
public class AdministratorChallengeController extends AbstractController<Administrator, Challenge> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AdministratorChallengeListService	listService;

	//@Autowired
	//private AuthenticatedChallengeListActiveService	listActiveService;

	@Autowired
	private AdministratorChallengeShowService	showService;

	@Autowired
	private AdministratorChallengeCreateService	createService;

	@Autowired
	private AdministratorChallengeDeleteService	deleteService;

	@Autowired
	private AdministratorChallengeUpdateService	updateService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);

		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		//super.addCustomCommand(CustomCommand.LIST_ACTIVE, BasicCommand.LIST, this.listActiveService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
	}

}
