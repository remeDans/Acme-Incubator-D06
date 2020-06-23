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

package acme.features.patron.banner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banner.Banner;
import acme.entities.creditCard.CreditCard;
import acme.entities.roles.Patron;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class PatronBannerDeleteService implements AbstractDeleteService<Patron, Banner> {

	// Internal state ---------------------------------------------------------

	@Autowired
	PatronBannerRepository repository;


	@Override
	public boolean authorise(final Request<Banner> request) {
		assert request != null;

		boolean result;
		int bannerId;
		Banner banner;
		Patron patron;
		Principal principal;

		bannerId = request.getModel().getInteger("id");
		banner = this.repository.findOneById(bannerId);
		patron = banner.getPatron();
		principal = request.getPrincipal();
		result = patron.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void unbind(final Request<Banner> request, final Banner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model);
	}

	@Override
	public void bind(final Request<Banner> request, final Banner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "picture", "slogan", "targetUrl");

	}

	@Override
	public Banner findOne(final Request<Banner> request) {
		assert request != null;

		Banner result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<Banner> request, final Banner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void delete(final Request<Banner> request, final Banner entity) {
		assert request != null;
		assert entity != null;

		int id;

		id = entity.getId();
		CreditCard c = this.repository.findOneCreditcard(id);

		if (c != null) {
			this.repository.delete(c);
		}
		this.repository.delete(entity);

	}

}
