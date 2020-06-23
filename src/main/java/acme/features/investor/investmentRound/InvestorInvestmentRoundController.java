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

package acme.features.investor.investmentRound;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.investmentRound.InvestmentRound;
import acme.entities.roles.Investor;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/investor/investment-round/")
public class InvestorInvestmentRoundController extends AbstractController<Investor, InvestmentRound> {

	// Internal state ---------------------------------------------------------

	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {

	}

}
