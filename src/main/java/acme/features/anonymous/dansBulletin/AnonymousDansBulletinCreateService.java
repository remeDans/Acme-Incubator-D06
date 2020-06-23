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

package acme.features.anonymous.dansBulletin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.DansBulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousDansBulletinCreateService implements AbstractCreateService<Anonymous, DansBulletin> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AnonymousDansBulletinRepository repository;


	@Override
	public boolean authorise(final Request<DansBulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public DansBulletin instantiate(final Request<DansBulletin> request) {
		assert request != null;

		DansBulletin result;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		result = new DansBulletin();
		result.setAuthor("John Doe");
		result.setText("Lorem ipsum");
		result.setMoment(moment);

		return result;
	}

	@Override
	public void unbind(final Request<DansBulletin> request, final DansBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "text");
	}

	@Override
	public void bind(final Request<DansBulletin> request, final DansBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void validate(final Request<DansBulletin> request, final DansBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<DansBulletin> request, final DansBulletin entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);

	}

}
