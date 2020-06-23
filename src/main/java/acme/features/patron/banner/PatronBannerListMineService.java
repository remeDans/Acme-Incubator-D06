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

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banner.Banner;
import acme.entities.roles.Patron;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class PatronBannerListMineService implements AbstractListService<Patron, Banner> {

	// Internal state ---------------------------------------------------------

	@Autowired
	PatronBannerRepository repository;


	@Override
	public boolean authorise(final Request<Banner> request) {
		assert request != null;

		boolean result = true;

		/*
		 * int bannerId;
		 * Banner banner;
		 * Patron patron = null;
		 * Principal principal;
		 *
		 * bannerId = request.getModel().getInteger("id");
		 * banner = this.repository.findOneById(bannerId);
		 * patron = banner.getPatron();
		 * principal = request.getPrincipal();
		 * result = patron.getUserAccount().getId() == principal.getAccountId();
		 */

		return result;

	}

	@Override
	public Collection<Banner> findMany(final Request<Banner> request) {//
		assert request != null;

		Principal principal;
		Collection<Banner> result;

		//result = this.repository.findManyAll();

		principal = request.getPrincipal();
		result = this.repository.findManyByPatronId(principal.getActiveRoleId());

		return result;

	}

	@Override
	public void unbind(final Request<Banner> request, final Banner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "picture", "slogan", "targetUrl");

	}

}
