/*
 * AuthenticatedProviderUpdateService.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.authenticated.investor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import acme.components.SpamFilter;
import acme.entities.roles.Investor;
import acme.features.administrator.customisation.AdministratorCustomisationRepository;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractUpdateService;

@Service
public class AuthenticatedInvestorUpdateService implements AbstractUpdateService<Authenticated, Investor> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedInvestorRepository			repository;

	@Autowired
	private AdministratorCustomisationRepository	customisationRepository;


	// AbstractUpdateService<Authenticated, Investor> interface ---------------

	@Override
	public boolean authorise(final Request<Investor> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Investor> request, final Investor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		String activitySector_En = this.customisationRepository.findActivitySectors();
		String[] words = null;
		words = SpamFilter.splitText(activitySector_En);
		request.getModel().setAttribute("words", words);

		request.bind(entity, errors, "words");
	}

	@Override
	public void unbind(final Request<Investor> request, final Investor entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		String activitySector_En = this.customisationRepository.findActivitySectors();
		String[] words = null;
		words = SpamFilter.splitText(activitySector_En);
		model.setAttribute("words", words);

		request.unbind(entity, model, "firmName", "activitySector");
		request.unbind(entity, model, "profile.photo", "profile.biography");
	}

	@Override
	public Investor findOne(final Request<Investor> request) {
		assert request != null;

		Investor result;
		Principal principal;
		int userAccountId;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();

		result = this.repository.findOneEntrepreneurByUserAccountId(userAccountId);

		return result;
	}

	@Override
	public void validate(final Request<Investor> request, final Investor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		// SPAM FILTER
		Double threshold = this.customisationRepository.findSpamThreshold();
		String spamWords_En = this.customisationRepository.findSpamWords_En();
		String spamWords_Es = this.customisationRepository.findSpamWords_Es();
		boolean isSpam = false;
		final String lang = LocaleContextHolder.getLocale().getLanguage();

		//Spam - firmName
		if (!errors.hasErrors("firmName")) {
			if (lang.equals("en")) {
				isSpam = SpamFilter.filterText(request.getModel().getString("firmName"), spamWords_En, threshold);
			} else {
				isSpam = SpamFilter.filterText(request.getModel().getString("firmName"), spamWords_Es, threshold);
			}
			errors.state(request, !isSpam, "firmName", "investor.error.isSpam");
		}

		//Spam - profile.biography
		if (!errors.hasErrors("profile.biography")) {
			if (lang.equals("en")) {
				isSpam = SpamFilter.filterText(request.getModel().getString("profile.biography"), spamWords_En, threshold);
			} else {
				isSpam = SpamFilter.filterText(request.getModel().getString("profile.biography"), spamWords_Es, threshold);
			}
			errors.state(request, !isSpam, "profile.biography", "investor.error.isSpam");
		}

	}

	@Override
	public void update(final Request<Investor> request, final Investor entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

	@Override
	public void onSuccess(final Request<Investor> request, final Response<Investor> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
