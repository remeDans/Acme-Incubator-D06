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

package acme.features.authenticated.forum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.forum.Forum;
import acme.entities.message.Message;
import acme.features.authenticated.message.AuthenticatedMessageRepository;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedForumShowService implements AbstractShowService<Authenticated, Forum> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedForumRepository	repository;

	@Autowired
	AuthenticatedMessageRepository	messageRepository;


	//AbstractList<Administrator, Announcement> interface ---------------------

	@Override
	public boolean authorise(final Request<Forum> request) {
		assert request != null;

		return true;
	}

	@Override
	public Forum findOne(final Request<Forum> request) {//
		assert request != null;

		Forum result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;

	}

	@Override
	public void unbind(final Request<Forum> request, final Forum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		Forum f;
		Integer forumId;

		forumId = entity.getId();
		f = this.repository.findOneById(forumId);

		//------------------------------------

		model.setAttribute("hasMessages", false);

		for (Message m : this.messageRepository.findMany()) {

			Integer forumIdMessages = m.getForum().getId();
			if (forumId.equals(forumIdMessages)) {
				model.setAttribute("hasMessages", true);
			}
		}

		//------------------------------------------
		model.setAttribute("iCreator", false);

		Authenticated creatorForum = f.getCreator();
		Integer creatorForumId = creatorForum.getUserAccount().getId();

		Principal principal = request.getPrincipal();

		if (creatorForumId.equals(principal.getAccountId())) {
			model.setAttribute("iCreator", true);
		}

		//------------------------------------

		request.unbind(entity, model, "title");
		request.unbind(entity, model, "creator.userAccount.username");

	}

}
