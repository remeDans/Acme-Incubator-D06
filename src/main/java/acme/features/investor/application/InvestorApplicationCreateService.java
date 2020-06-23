
package acme.features.investor.application;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import acme.components.SpamFilter;
import acme.entities.application.Application;
import acme.entities.investmentRound.InvestmentRound;
import acme.entities.roles.Investor;
import acme.features.administrator.customisation.AdministratorCustomisationRepository;
import acme.features.investor.investmentRound.InvestorInvestmentRoundRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service

public class InvestorApplicationCreateService implements AbstractCreateService<Investor, Application> {

	@Autowired
	InvestorApplicationRepository			repository;

	@Autowired
	InvestorInvestmentRoundRepository		investmentRoundRepository;

	@Autowired
	AdministratorCustomisationRepository	customisationRepository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		int investmentId = request.getModel().getInteger("investmentId");
		InvestmentRound investmentRound = this.investmentRoundRepository.findMOneById(investmentId);
		boolean result = investmentRound.getStatus().equals("PUBLISHED");

		return result;
	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creationMoment");
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("investmentId", request.getModel().getInteger("investmentId"));
		request.unbind(entity, model, "ticker", "creationMoment", "statement", "investmentMoneyOffer");

	}

	@Override
	public Application instantiate(final Request<Application> request) {
		assert request != null;

		Application result = new Application();
		result.setStatus("PENDING");

		int investmentId = request.getModel().getInteger("investmentId");
		result.setInvestmentRound(this.investmentRoundRepository.findMOneById(investmentId));

		Investor investor = this.repository.findOneByInvestorId(request.getPrincipal().getActiveRoleId());
		result.setInvestor(investor);

		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.MILLISECOND, 1);
		Date today = calendar.getTime();
		result.setCreationMoment(today);

		return result;
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean tickerHasErrors = errors.hasErrors("ticker");
		if (!tickerHasErrors) {
			Application existing = this.repository.findOneByTicker(entity.getTicker());
			errors.state(request, existing == null || existing.getId() == entity.getId(), "ticker", "investor.application.form.error.ticker-unique");
		}

		if (!errors.hasErrors("investmentMoneyOffer")) {

			boolean assertCurrency = entity.getInvestmentMoneyOffer().getCurrency().contentEquals("€") || entity.getInvestmentMoneyOffer().getCurrency().contentEquals("EUR") || entity.getInvestmentMoneyOffer().getCurrency().contentEquals("EURO")
				|| entity.getInvestmentMoneyOffer().getCurrency().contentEquals("EUROS");
			errors.state(request, assertCurrency, "investmentMoneyOffer", "entrepreneur.application.error.currency.euro");
		}

		// SPAM FILTER( "statement")
		Double threshold = this.customisationRepository.findSpamThreshold();
		String spamWords_En = this.customisationRepository.findSpamWords_En();
		String spamWords_Es = this.customisationRepository.findSpamWords_Es();
		boolean isSpam = false;
		final String lang = LocaleContextHolder.getLocale().getLanguage();

		//Spam - statement

		if (!errors.hasErrors("statement")) {
			if (lang.equals("en")) {
				isSpam = SpamFilter.filterText(request.getModel().getString("statement"), spamWords_En, threshold);
			} else {
				isSpam = SpamFilter.filterText(request.getModel().getString("statement"), spamWords_Es, threshold);
			}
			errors.state(request, !isSpam, "statement", "investor.application.error.isSpam");
		}

	}

	@Override
	public void create(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;

		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.MILLISECOND, 1);
		Date today = calendar.getTime();
		entity.setCreationMoment(today);

		this.repository.save(entity);
	}
}
