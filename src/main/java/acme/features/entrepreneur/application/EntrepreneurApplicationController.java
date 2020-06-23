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

package acme.features.entrepreneur.application;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.application.Application;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/entrepreneur/application/")
public class EntrepreneurApplicationController extends AbstractController<Entrepreneur, Application> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private EntrepreneurApplicationListEntrepreneurService	listEntrepreneurService;

	@Autowired
	private EntrepreneurApplicationShowService				showService;

	@Autowired
	private EntrepreneurApplicationUpdateService			updateService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {

		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addCustomCommand(CustomCommand.LIST_APPLICATION_ENTREPRENEUR, BasicCommand.LIST, this.listEntrepreneurService);
	}

}
