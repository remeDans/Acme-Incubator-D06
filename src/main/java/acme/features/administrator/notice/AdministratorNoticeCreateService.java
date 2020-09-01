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

package acme.features.administrator.notice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.stereotype.Service;

import acme.entities.notice.Notice;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorNoticeCreateService implements AbstractCreateService<Administrator, Notice> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AdministratorNoticeRepository repository;


	@Override
	public boolean authorise(final Request<Notice> request) {
		assert request != null;

		return true;
	}

	@Override
	public Notice instantiate(final Request<Notice> request) {
		assert request != null;

		Notice result;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		result = new Notice();

		result.setCreationMoment(moment);

		return result;
	}

	@Override
	public void unbind(final Request<Notice> request, final Notice entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "headerPicture", "title", "creationMoment", "deadline", "body", "optionalLinks");

		//------------------------------------------
		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("accept", "false");
		} else {
			request.transfer(model, "accept");
		}
	}

	@Override
	public void bind(final Request<Notice> request, final Notice entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creationMoment");

	}

	@Override
	public void validate(final Request<Notice> request, final Notice entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean isAccepted;

		isAccepted = request.getModel().getBoolean("accept");
		errors.state(request, isAccepted, "accept", "administrator.notice.error.must-accept");

		Calendar calendar;
		Date minimumDeadline;

		if (!errors.hasErrors("deadline")) {
			calendar = new GregorianCalendar();
			//calendar.add(Calendar.DAY_OF_MONTH,7);
			minimumDeadline = calendar.getTime();
			errors.state(request, entity.getDeadline().after(minimumDeadline), "deadline", "administrator.notice.form.error.deadline");
		}

		if (!errors.hasErrors("optionalLinks") && !entity.getOptionalLinks().isEmpty()) {
			boolean isURLs;
			List<String> optLinks = new ArrayList<>();
			optLinks = Arrays.asList(entity.getOptionalLinks().split(","));
			isURLs = optLinks.stream().allMatch(x -> UrlUtils.isAbsoluteUrl(x.trim()));
			errors.state(request, isURLs, "optionalLinks", "administrator.notice.error.NotUrls");
		}

	}

	@Override
	public void create(final Request<Notice> request, final Notice entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationMoment(moment);
		this.repository.save(entity);

	}

}
