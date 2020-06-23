
package acme.features.authenticated.message;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import acme.components.SpamFilter;
import acme.entities.message.Message;
import acme.features.administrator.customisation.AdministratorCustomisationRepository;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedMessageCreateService implements AbstractCreateService<Authenticated, Message> {

	@Autowired
	AuthenticatedMessageRepository			repository;

	@Autowired
	AdministratorCustomisationRepository	customisationRepository;


	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;

		int forumId = request.getModel().getInteger("forumId");
		Principal principal = request.getPrincipal();
		boolean result = this.repository.findExistsForumParticipant(forumId, principal.getActiveRoleId());

		return result;

	}

	@Override
	public void bind(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creationMoment");
	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("forumId", request.getModel().getInteger("forumId"));
		request.unbind(entity, model, "title", "creationMoment", "optionalListTags", "body");
		request.unbind(entity, model, "creator.userAccount.username");

		//------------------------------------------
		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("accept", "false");
		} else {
			request.transfer(model, "accept");
		}
	}

	@Override
	public Message instantiate(final Request<Message> request) {
		assert request != null;

		Message result = new Message();

		Authenticated auth = this.repository.findOneAuthenticatedById(request.getPrincipal().getActiveRoleId());

		result.setCreator(auth);
		int forumId = request.getModel().getInteger("forumId");
		result.setForum(this.repository.findOneById(forumId));

		Date moment = new Date(System.currentTimeMillis() - 1);
		result.setCreationMoment(moment);

		return result;
	}

	@Override
	public void validate(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean isAccepted;

		isAccepted = request.getModel().getBoolean("accept");
		errors.state(request, isAccepted, "accept", "authenticated.message.error.must-accept");

		//---------------------------------------------------------------------------------------

		// SPAM FILTER("title", "optionalListTags","body")
		Double threshold = this.customisationRepository.findSpamThreshold();
		String spamWords_En = this.customisationRepository.findSpamWords_En();
		String spamWords_Es = this.customisationRepository.findSpamWords_Es();
		boolean isSpam = false;
		final String lang = LocaleContextHolder.getLocale().getLanguage();

		//Spam - title
		if (!errors.hasErrors("title")) {
			if (lang.equals("en")) {
				isSpam = SpamFilter.filterText(request.getModel().getString("title"), spamWords_En, threshold);
			} else {
				isSpam = SpamFilter.filterText(request.getModel().getString("title"), spamWords_Es, threshold);
			}
			errors.state(request, !isSpam, "title", "authenticated.message.error.isSpam");
		}

		//Spam - body
		if (!errors.hasErrors("body")) {
			if (lang.equals("en")) {
				isSpam = SpamFilter.filterText(request.getModel().getString("body"), spamWords_En, threshold);
			} else {
				isSpam = SpamFilter.filterText(request.getModel().getString("body"), spamWords_Es, threshold);
			}
			errors.state(request, !isSpam, "body", "authenticated.message.error.isSpam");
		}

		//Spam - optionalListTags
		if (!errors.hasErrors("optionalListTags")) {
			if (lang.equals("en")) {
				isSpam = SpamFilter.filterText(request.getModel().getString("optionalListTags"), spamWords_En, threshold);
			} else {
				isSpam = SpamFilter.filterText(request.getModel().getString("optionalListTags"), spamWords_Es, threshold);
			}
			errors.state(request, !isSpam, "optionalListTags", "authenticated.message.error.isSpam");
		}

	}

	@Override
	public void create(final Request<Message> request, final Message entity) {
		assert request != null;
		assert entity != null;

		Date moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationMoment(moment);

		this.repository.save(entity);
	}

}
