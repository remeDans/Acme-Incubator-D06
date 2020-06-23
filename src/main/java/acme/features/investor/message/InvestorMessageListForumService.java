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

package acme.features.investor.message;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.message.Message;
import acme.entities.roles.Investor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class InvestorMessageListForumService implements AbstractListService<Investor, Message> {

	// Internal state ---------------------------------------------------------

	@Autowired
	InvestorMessageRepository repository;


	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<Message> findMany(final Request<Message> request) {//
		assert request != null;

		Collection<Message> result;

		int id;

		id = request.getModel().getInteger("forumId");
		result = this.repository.findManyByForum(id);

		return result;

	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "creationMoment");
		request.unbind(entity, model, "creator.userAccount.username");
	}

}
