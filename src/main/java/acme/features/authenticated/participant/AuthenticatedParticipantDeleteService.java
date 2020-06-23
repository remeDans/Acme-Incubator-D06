
package acme.features.authenticated.participant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.participant.Participant;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class AuthenticatedParticipantDeleteService implements AbstractDeleteService<Authenticated, Participant> {

	@Autowired
	AuthenticatedParticipantRepository repository;


	@Override
	public boolean authorise(final Request<Participant> request) {
		assert request != null;

		int participantId = request.getModel().getInteger("id");
		Participant participant = this.repository.findOneById(participantId);
		Principal principal = request.getPrincipal();
		boolean result = participant.getForum().getCreator().getId() == principal.getActiveRoleId();

		return result;
	}

	@Override
	public void bind(final Request<Participant> request, final Participant entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Participant> request, final Participant entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "authenticated.userAccount.username");
	}

	@Override
	public Participant findOne(final Request<Participant> request) {
		assert request != null;

		Participant result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		request.unbind(result, request.getModel(), "authenticated.userAccount.username"); // Provisional
		return result;
	}

	@Override
	public void validate(final Request<Participant> request, final Participant entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean notCreator = entity.getForum().getCreator().getId() != entity.getAuthenticated().getId();
		errors.state(request, notCreator, "authenticated.userAccount.username", "authenticated.participant.form.error.delete-creator");
	}

	@Override
	public void delete(final Request<Participant> request, final Participant entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);
	}
}
