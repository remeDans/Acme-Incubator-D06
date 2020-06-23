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
import acme.features.authenticated.message.AuthenticatedMessageRepository;
import acme.features.authenticated.participant.AuthenticatedParticipantRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class AuthenticatedForumDeleteService implements AbstractDeleteService<Authenticated, Forum> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedForumRepository		repository;

	@Autowired
	AuthenticatedMessageRepository		messageRepository;

	@Autowired
	AuthenticatedParticipantRepository	participantRepository;


	@Override
	public boolean authorise(final Request<Forum> request) {
		assert request != null;

		int forumId = request.getModel().getInteger("id");
		Principal principal = request.getPrincipal();
		boolean result = this.participantRepository.findIsForumCreator(forumId, principal.getActiveRoleId());

		return result;

	}

	@Override
	public void unbind(final Request<Forum> request, final Forum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title");
		request.unbind(entity, model, "creator.userAccount.username");
	}

	@Override
	public void bind(final Request<Forum> request, final Forum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "title");
		request.bind(entity, errors, "creator.userAccount.username");

	}

	@Override
	public Forum findOne(final Request<Forum> request) {
		assert request != null;

		Forum result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<Forum> request, final Forum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void delete(final Request<Forum> request, final Forum entity) {
		assert request != null;
		assert entity != null;

		this.repository.deleteAll(this.messageRepository.findManyByForum(entity.getId()));
		this.repository.deleteAll(this.participantRepository.findManyParticipantsByForumId(entity.getId()));
		this.repository.delete(entity);

	}

}
