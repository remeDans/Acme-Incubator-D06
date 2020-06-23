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

package acme.features.entrepreneur.workProgramme;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.roles.Entrepreneur;
import acme.entities.workProgramme.WorkProgramme;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/entrepreneur/work-programme/")
public class EntrepreneurWorkProgrammeController extends AbstractController<Entrepreneur, WorkProgramme> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private EntrepreneurWorkProgrammeListService listService;

	//@Autowired
	//private AuthenticatedWorkProgrammeListActiveService	listActiveService;

	@Autowired
	private EntrepreneurWorkProgrammeShowService showService;

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
