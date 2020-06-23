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

package acme.features.administrator.banner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banner.Banner;
import acme.entities.creditCard.CreditCard;
import acme.features.administrator.creditCard.AdministratorCreditCardRepository;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorBannerShowService implements AbstractShowService<Administrator, Banner> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AdministratorBannerRepository		repository;

	@Autowired
	AdministratorCreditCardRepository	creditCardRepository;


	//AbstractList<Administrator, Banner> interface ---------------------

	@Override
	public boolean authorise(final Request<Banner> request) {
		assert request != null;

		return true;
	}

	@Override
	public Banner findOne(final Request<Banner> request) {//
		assert request != null;

		Banner result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findMOneById(id);

		return result;

	}

	@Override
	public void unbind(final Request<Banner> request, final Banner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		Integer bannerId = entity.getId();

		model.setAttribute("hasCreditcard", false);

		for (CreditCard c : this.creditCardRepository.findManyAll()) {
			Integer bannerIdCreditcard = c.getBanner().getId();
			if (bannerId.equals(bannerIdCreditcard)) {
				model.setAttribute("hasCreditcard", true);
			}
		}

		request.unbind(entity, model, "picture", "slogan", "targetUrl");

	}

}
