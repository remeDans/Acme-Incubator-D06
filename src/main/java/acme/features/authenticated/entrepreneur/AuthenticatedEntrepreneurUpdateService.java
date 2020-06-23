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

package acme.features.authenticated.entrepreneur;

import org.springframework.beans.factory.annotation.Autowired;
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
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractUpdateService;

@Service
public class AuthenticatedEntrepreneurUpdateService implements AbstractUpdateService<Authenticated, Entrepreneur> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedEntrepreneurRepository		repository;

	@Autowired
	private AdministratorCustomisationRepository	customisationRepository;


	// AbstractUpdateService<Authenticated, Entrepreneur> interface ---------------

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
	public Entrepreneur findOne(final Request<Entrepreneur> request) {
		assert request != null;

		Entrepreneur result;
		Principal principal;
		int userAccountId;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();

		result = this.repository.findOneEntrepreneurByUserAccountId(userAccountId);

		return result;
	}

	@Override
	public void validate(final Request<Entrepreneur> request, final Entrepreneur entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void update(final Request<Entrepreneur> request, final Entrepreneur entity) {
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
