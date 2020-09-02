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

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banner.Banner;
import acme.entities.creditCard.CreditCard;
import acme.entities.roles.Patron;
import acme.features.patron.banner.PatronBannerRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class PatronCreditCardCreateBannerService implements AbstractCreateService<Patron, CreditCard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	PatronCreditCardRepository	repository;

	@Autowired
	PatronBannerRepository		bannerRepository;


	@Override
	public boolean authorise(final Request<CreditCard> request) {
		assert request != null;

		boolean result;
		Patron patron;
		Principal principal;

		patron = this.repository.findOneByIdPatron(request.getPrincipal().getActiveRoleId());
		principal = request.getPrincipal();
		result = patron.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public CreditCard instantiate(final Request<CreditCard> request) {
		assert request != null;

		CreditCard result;

		result = new CreditCard();

		Integer bannerId = request.getModel().getInteger("banner_id");
		Banner banner = this.bannerRepository.findOneById(bannerId);

		result.setBanner(banner);

		return result;
	}

	@Override
	public void unbind(final Request<CreditCard> request, final CreditCard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		Integer bannerId = request.getModel().getInteger("banner_id");
		model.setAttribute("banner_id", bannerId);

		request.unbind(entity, model, "holderName", "number", "brand", "monthExpired", "yearExpired", "CVV");

	}

	@Override
	public void bind(final Request<CreditCard> request, final CreditCard entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void validate(final Request<CreditCard> request, final CreditCard entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("monthExpired")) {
			Calendar fecha = Calendar.getInstance();

			int actualMonth = fecha.get(Calendar.MONTH) + 1;
			int actualYear = fecha.get(Calendar.YEAR);

			boolean validMonth = true;

			if (entity.getYearExpired() == actualYear) {
				if (entity.getMonthExpired() < actualMonth) {
					validMonth = false;
				}
			}

			errors.state(request, validMonth, "monthExpired", "patron.creditCard.form.error.validMonth");

		}
	}

	@Override
	public void create(final Request<CreditCard> request, final CreditCard entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
