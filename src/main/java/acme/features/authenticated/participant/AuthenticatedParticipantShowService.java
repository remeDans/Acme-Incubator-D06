
package acme.features.authenticated.participant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.participant.Participant;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedParticipantShowService implements AbstractShowService<Authenticated, Participant> {

	@Autowired
	AuthenticatedParticipantRepository repository;


	@Override
	public boolean authorise(final Request<Participant> request) {
		assert request != null;

		int participantId = request.getModel().getInteger("id");
		Participant participant = this.repository.findOneById(participantId);
		int threadId = participant.getForum().getId();
		int authenticatedId = request.getPrincipal().getActiveRoleId();
		boolean result = this.repository.findIsForumParticipant(threadId, authenticatedId);

		return result;
	}

	@Override
	public void unbind(final Request<Participant> request, final Participant entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "authenticated.userAccount.username", "forum.creator.id");
	}

	@Override
	public Participant findOne(final Request<Participant> request) {
		assert request != null;

		Participant result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

}
