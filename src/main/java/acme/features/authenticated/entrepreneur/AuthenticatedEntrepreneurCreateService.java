/*
 * AuthenticatedProviderCreateService.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.authenticated.entrepreneur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import acme.components.SpamFilter;
import acme.entities.roles.Entrepreneur;
import acme.features.administrator.customisation.AdministratorCustomisationRepository;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.entities.UserAccount;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedEntrepreneurCreateService implements AbstractCreateService<Authenticated, Entrepreneur> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedEntrepreneurRepository		repository;

	@Autowired
	private AdministratorCustomisationRepository	customisationRepository;


	// AbstractCreateService<Authenticated, Entrepreneur> interface ---------------

	@Override
	public boolean authorise(final Request<Entrepreneur> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Entrepreneur> request, final Entrepreneur entity, final Errors errors) {
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
	public void unbind(final Request<Entrepreneur> request, final Entrepreneur entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		String activitySector_En = this.customisationRepository.findActivitySectors();
		String[] words = null;
		words = SpamFilter.splitText(activitySector_En);
		model.setAttribute("words", words);

		request.unbind(entity, model, "startupName", "activitySector", "qualificationRecord", "skillsRecord");
	}

	@Override
	public Entrepreneur instantiate(final Request<Entrepreneur> request) {
		assert request != null;

		Entrepreneur result;
		Principal principal;
		int userAccountId;
		UserAccount userAccount;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		userAccount = this.repository.findOneUserAccountById(userAccountId);

		result = new Entrepreneur();
		result.setUserAccount(userAccount);

		return result;
	}

	@Override
	public void validate(final Request<Entrepreneur> request, final Entrepreneur entity, final Errors errors) {
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
		if (!errors.hasErrors("startupName")) {
			if (lang.equals("en")) {
				isSpam = SpamFilter.filterText(request.getModel().getString("startupName"), spamWords_En, threshold);
			} else {
				isSpam = SpamFilter.filterText(request.getModel().getString("startupName"), spamWords_Es, threshold);
			}
			errors.state(request, !isSpam, "startupName", "entrepreneur.error.isSpam");
		}

		//Spam - qualificationRecord
		if (!errors.hasErrors("qualificationRecord")) {
			if (lang.equals("en")) {
				isSpam = SpamFilter.filterText(request.getModel().getString("qualificationRecord"), spamWords_En, threshold);
			} else {
				isSpam = SpamFilter.filterText(request.getModel().getString("qualificationRecord"), spamWords_Es, threshold);
			}
			errors.state(request, !isSpam, "qualificationRecord", "entrepreneur.error.isSpam");
		}

		//Spam - qualificationRecord
		if (!errors.hasErrors("skillsRecord")) {
			if (lang.equals("en")) {
				isSpam = SpamFilter.filterText(request.getModel().getString("skillsRecord"), spamWords_En, threshold);
			} else {
				isSpam = SpamFilter.filterText(request.getModel().getString("skillsRecord"), spamWords_Es, threshold);
			}
			errors.state(request, !isSpam, "skillsRecord", "entrepreneur.error.isSpam");
		}
	}

	@Override
	public void create(final Request<Entrepreneur> request, final Entrepreneur entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

	@Override
	public void onSuccess(final Request<Entrepreneur> request, final Response<Entrepreneur> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
