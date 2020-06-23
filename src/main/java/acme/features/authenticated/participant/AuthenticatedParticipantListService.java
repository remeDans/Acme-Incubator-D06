
package acme.features.authenticated.participant;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.participant.Participant;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedParticipantListService implements AbstractListService<Authenticated, Participant> {

	@Autowired
	AuthenticatedParticipantRepository repository;


	@Override
	public boolean authorise(final Request<Participant> request) {
		assert request != null;

		int forumId = request.getModel().getInteger("id");
		int authenticatedId = request.getPrincipal().getActiveRoleId();
		boolean result = this.repository.findIsForumParticipant(forumId, authenticatedId);

		return result;

	}

	@Override
	public void unbind(final Request<Participant> request, final Participant entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "authenticated.userAccount.username");
	}

	@Override
	public Collection<Participant> findMany(final Request<Participant> request) {
		assert request != null;

		Collection<Participant> result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findManyParticipantsByForumId(id);

		return result;
	}

}
