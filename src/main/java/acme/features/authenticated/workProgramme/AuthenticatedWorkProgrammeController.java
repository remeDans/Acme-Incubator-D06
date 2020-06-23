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

package acme.features.authenticated.workProgramme;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.workProgramme.WorkProgramme;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/work-programme/")
public class AuthenticatedWorkProgrammeController extends AbstractController<Authenticated, WorkProgramme> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedWorkProgrammeListService listService;

	//@Autowired
	//private AuthenticatedWorkProgrammeListActiveService	listActiveService;

	@Autowired
	private AuthenticatedWorkProgrammeShowService showService;

	//@Autowired
	//private AnonymousDansBulletinCreateService	createService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		//super.addCustomCommand(CustomCommand.LIST_ACTIVE, BasicCommand.LIST, this.listActiveService);
		//super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}

}
