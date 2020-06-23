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
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import acme.components.SpamFilter;
import acme.entities.banner.Banner;
import acme.entities.roles.Patron;
import acme.features.administrator.customisation.AdministratorCustomisationRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class PatronBannerCreateService implements AbstractCreateService<Patron, Banner> {

	// Internal state ---------------------------------------------------------

	@Autowired
	PatronBannerRepository					repository;

	@Autowired
	AdministratorCustomisationRepository	customisationRepository;


	@Override
	public boolean authorise(final Request<Banner> request) {
		assert request != null;

		return true;
	}

	@Override
	public Banner instantiate(final Request<Banner> request) {
		assert request != null;

		Banner result;

		result = new Banner();

		return result;
	}

	@Override
	public void unbind(final Request<Banner> request, final Banner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "picture", "slogan", "targetUrl");

	}

	@Override
	public void bind(final Request<Banner> request, final Banner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Principal principal;

		principal = request.getPrincipal();
		int id = principal.getActiveRoleId();

		Patron p = this.repository.findOneByIdPatron(id);
		entity.setPatron(p);

		request.bind(entity, errors, "patron");

	}

	@Override
	public void validate(final Request<Banner> request, final Banner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		// SPAM FILTER
		Double threshold = this.customisationRepository.findSpamThreshold();
		String spamWords_En = this.customisationRepository.findSpamWords_En();
		String spamWords_Es = this.customisationRepository.findSpamWords_Es();
		boolean isSpam = false;
		final String lang = LocaleContextHolder.getLocale().getLanguage();

		//Spam - slogan
		if (!errors.hasErrors("slogan")) {
			if (lang.equals("en")) {
				isSpam = SpamFilter.filterText(request.getModel().getString("slogan"), spamWords_En, threshold);
			} else {
				isSpam = SpamFilter.filterText(request.getModel().getString("slogan"), spamWords_Es, threshold);
			}
			errors.state(request, !isSpam, "slogan", "patron.banner.error.isSpam");
		}

	}

	@Override
	public void create(final Request<Banner> request, final Banner entity) {
		assert request != null;
		assert entity != null;

		Principal principal;

		principal = request.getPrincipal();
		int id = principal.getActiveRoleId();

		Patron p = this.repository.findOneByIdPatron(id);
		entity.setPatron(p);

		this.repository.save(entity);

	}

}
