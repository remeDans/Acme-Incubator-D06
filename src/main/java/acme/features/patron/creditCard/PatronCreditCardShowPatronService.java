/*
 * AnonymousUserAccountCreateService.java
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.creditCard.CreditCard;
import acme.entities.roles.Patron;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class PatronCreditCardShowPatronService implements AbstractShowService<Patron, CreditCard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	PatronCreditCardRepository repository;


	//AbstractList<Administrator, Banner> interface ---------------------

	@Override
	public boolean authorise(final Request<CreditCard> request) {
		assert request != null;

		return true;
	}

	@Override
	public CreditCard findOne(final Request<CreditCard> request) {//
		assert request != null;

		CreditCard result;
		Integer patronId = request.getModel().getInteger("patron_id");

		result = this.repository.findOneByPatronId(patronId);

		return result;

	}

	@Override
	public void unbind(final Request<CreditCard> request, final CreditCard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "holderName", "number", "brand", "monthExpired", "yearExpired", "CVV");
	}

}
