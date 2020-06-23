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

package acme.features.authenticated.inquiry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.inquiry.Inquiry;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedInquiryShowService implements AbstractShowService<Authenticated, Inquiry> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedInquiryRepository repository;


	//AbstractList<Administrator, Announcement> interface ---------------------

	@Override
	public boolean authorise(final Request<Inquiry> request) {
		assert request != null;

		return true;
	}

	@Override
	public Inquiry findOne(final Request<Inquiry> request) {//
		assert request != null;

		Inquiry result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findMOneById(id);

		return result;

	}

	@Override
	public void unbind(final Request<Inquiry> request, final Inquiry entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "creationMoment", "deadline", "paragraphsDescription", "minIntervalMoney", "maxIntervalMoney", "contactEmail");
		//request.unbind(entity, model,"intervalMoney");
	}

}
