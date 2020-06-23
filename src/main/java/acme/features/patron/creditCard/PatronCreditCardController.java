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

package acme.features.patron.creditCard;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.creditCard.CreditCard;
import acme.entities.roles.Patron;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/patron/credit-card/")
public class PatronCreditCardController extends AbstractController<Patron, CreditCard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private PatronCreditCardShowBannerService	showService;

	@Autowired
	private PatronCreditCardCreateBannerService	createBannerService;

	@Autowired
	private PatronCreditCardCreatePatronService	createPatronService;

	@Autowired
	private PatronCreditCardShowBannerService	showBannerService;

	@Autowired
	private PatronCreditCardShowPatronService	showPatronService;

	@Autowired
	private PatronCreditCardDeleteService		deleteService;

	@Autowired
	private PatronCreditCardUpdateService		updateService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);

		super.addCustomCommand(CustomCommand.CREATE_CREDITCARD_BANNER, BasicCommand.CREATE, this.createBannerService);
		super.addCustomCommand(CustomCommand.CREATE_CREDITCARD_PATRON, BasicCommand.CREATE, this.createPatronService);

		super.addCustomCommand(CustomCommand.SHOW_CREDITCARD_BANNER, BasicCommand.SHOW, this.showBannerService);
		super.addCustomCommand(CustomCommand.SHOW_CREDITCARD_PATRON, BasicCommand.SHOW, this.showPatronService);

		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);

	}

}
