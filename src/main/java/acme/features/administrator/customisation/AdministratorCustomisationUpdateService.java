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

package acme.features.administrator.customisation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisation.Customisation;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorCustomisationUpdateService implements AbstractUpdateService<Administrator, Customisation> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AdministratorCustomisationRepository repository;


	//AbstractList<Administrator, Configuration> interface ---------------------

	@Override
	public boolean authorise(final Request<Customisation> request) {
		assert request != null;

		return true;
	}

	@Override
	public Customisation findOne(final Request<Customisation> request) {//
		assert request != null;

		Customisation result;

		result = this.repository.findCustomisation();

		return result;

	}

	@Override
	public void unbind(final Request<Customisation> request, final Customisation entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "spamWords_En", "spamWords_Es", "spamThreshold", "activitySectors");
	}

	@Override
	public void bind(final Request<Customisation> request, final Customisation entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void validate(final Request<Customisation> request, final Customisation entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void update(final Request<Customisation> request, final Customisation entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
