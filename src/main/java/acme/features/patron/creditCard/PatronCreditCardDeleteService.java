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
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractDeleteService;

@Service
public class PatronCreditCardDeleteService implements AbstractDeleteService<Patron, CreditCard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	PatronCreditCardRepository repository;


	@Override
	public boolean authorise(final Request<CreditCard> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<CreditCard> request, final CreditCard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model);
	}

	@Override
	public void bind(final Request<CreditCard> request, final CreditCard entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "holderName", "number", "brand", "monthExpired", "yearExpired", "CVV");

	}

	@Override
	public CreditCard findOne(final Request<CreditCard> request) {
		assert request != null;

		CreditCard result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<CreditCard> request, final CreditCard entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void delete(final Request<CreditCard> request, final CreditCard entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);

	}

}
