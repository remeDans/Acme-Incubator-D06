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

package acme.features.bookkeeper.workProgramme;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.roles.Bookkeeper;
import acme.entities.workProgramme.WorkProgramme;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/bookkeeper/work-programme/")
public class BookeeperWorkProgrammeController extends AbstractController<Bookkeeper, WorkProgramme> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private BookeeperWorkProgrammeListService listService;

	//@Autowired
	//private AuthenticatedWorkProgrammeListActiveService	listActiveService;

	@Autowired
	private BookeeperWorkProgrammeShowService showService;

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
