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

package acme.features.administrator.technologyRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.SpamFilter;
import acme.entities.technologyRecord.TechnologyRecord;
import acme.features.administrator.customisation.AdministratorCustomisationRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorTechnologyRecordCreateService implements AbstractCreateService<Administrator, TechnologyRecord> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AdministratorTechnologyRecordRepository			repository;

	@Autowired
	private AdministratorCustomisationRepository	customisationRepository;


	@Override
	public boolean authorise(final Request<TechnologyRecord> request) {
		assert request != null;

		return true;
	}

	@Override
	public TechnologyRecord instantiate(final Request<TechnologyRecord> request) {
		assert request != null;

		TechnologyRecord result;

		result = new TechnologyRecord();

		return result;
	}

	@Override
	public void unbind(final Request<TechnologyRecord> request, final TechnologyRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		String activitySector_En = this.customisationRepository.findActivitySectors();
		String[] words = null;
		words = SpamFilter.splitText(activitySector_En);
		model.setAttribute("words", words);

		request.unbind(entity, model, "title", "activitySector", "nameInventor", "description", "webSite", "contactEmail", "openSource", "stars");

	}

	@Override
	public void bind(final Request<TechnologyRecord> request, final TechnologyRecord entity, final Errors errors) {
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
	public void validate(final Request<TechnologyRecord> request, final TechnologyRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<TechnologyRecord> request, final TechnologyRecord entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
