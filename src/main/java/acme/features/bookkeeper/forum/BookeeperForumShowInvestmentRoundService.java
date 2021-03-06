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

package acme.features.bookkeeper.forum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.forum.Forum;
import acme.entities.message.Message;
import acme.entities.roles.Bookkeeper;
import acme.features.bookkeeper.message.BookeeperMessageRepository;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class BookeeperForumShowInvestmentRoundService implements AbstractShowService<Bookkeeper, Forum> {

	// Internal state ---------------------------------------------------------

	@Autowired
	BookeeperForumRepository	repository;

	@Autowired
	BookeeperMessageRepository	messageRepository;


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

		id = request.getModel().getInteger("investmentId");
		result = this.repository.findOneByInvestmentRoundId(id);

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
		f = this.repository.findMOneById(forumId);

		model.setAttribute("hasMessages", false);

		for (Message m : this.messageRepository.findMany()) {

			Integer forumIdMessages = m.getForum().getId();
			if (forumId.equals(forumIdMessages)) {
				model.setAttribute("hasMessages", true);
			}
		}

		//------------------------------------

		request.unbind(entity, model, "title");
		request.unbind(entity, model, "creator.userAccount.username");

	}

}
