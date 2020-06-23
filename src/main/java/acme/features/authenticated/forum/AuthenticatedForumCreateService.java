
package acme.features.authenticated.forum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import acme.components.SpamFilter;
import acme.entities.forum.Forum;
import acme.entities.investmentRound.InvestmentRound;
import acme.entities.participant.Participant;
import acme.features.administrator.customisation.AdministratorCustomisationRepository;
import acme.features.authenticated.investmentRound.AuthenticatedInvestmentRoundRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedForumCreateService implements AbstractCreateService<Authenticated, Forum> {

	@Autowired
	AuthenticatedForumRepository			repository;

	@Autowired
	AuthenticatedInvestmentRoundRepository	investmentRoundRepository;

	@Autowired
	AdministratorCustomisationRepository	customisationRepository;


	@Override
	public boolean authorise(final Request<Forum> request) {
		assert request != null;

		int investmentId = request.getModel().getInteger("roundId");
		InvestmentRound investmentRound = this.investmentRoundRepository.findMOneById(investmentId);
		boolean result = investmentRound.getStatus().equals("PUBLISHED");

		return result;
	}

	@Override
	public void bind(final Request<Forum> request, final Forum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Forum> request, final Forum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		Integer id = request.getModel().getInteger("roundId");
		model.setAttribute("roundId", id);

		request.unbind(entity, model, "title");
	}

	@Override
	public Forum instantiate(final Request<Forum> request) {
		assert request != null;

		Forum result = new Forum();

		Integer investmentRoundId = request.getModel().getInteger("roundId");
		InvestmentRound i = this.investmentRoundRepository.findMOneById(investmentRoundId);

		Authenticated auth = this.repository.findOneAuthenticatedById(request.getPrincipal().getActiveRoleId());
		result.setCreator(auth);
		result.setInvestmentRound(i);

		return result;
	}

	@Override
	public void validate(final Request<Forum> request, final Forum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		// SPAM FILTER("title")
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
			errors.state(request, !isSpam, "title", "authenticated.forum.error.isSpam");
		}

	}

	@Override
	public void create(final Request<Forum> request, final Forum entity) {
		assert request != null;
		assert entity != null;

		Authenticated auth = this.repository.findOneAuthenticatedById(request.getPrincipal().getActiveRoleId());
		Participant creator = new Participant();
		creator.setForum(entity);
		creator.setAuthenticated(auth);

		this.repository.save(entity);
		this.repository.save(creator);
	}

}
