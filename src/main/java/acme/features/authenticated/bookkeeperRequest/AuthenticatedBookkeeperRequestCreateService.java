/*
 * AuthenticatedConsumerCreateService.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.authenticated.bookkeeperRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bookkeeperRequest.BookkeeperRequest;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.entities.UserAccount;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedBookkeeperRequestCreateService implements AbstractCreateService<Authenticated, BookkeeperRequest> {

	@Autowired
	private AuthenticatedBookkeeperRequestRepository repository;


	@Override
	public boolean authorise(final Request<BookkeeperRequest> request) {
		assert request != null;

		Principal principal = request.getPrincipal();
		boolean result = !this.repository.findIsBookkeeperByUserAccountId(principal.getAccountId());

		return result;
	}

	@Override
	public void validate(final Request<BookkeeperRequest> request, final BookkeeperRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Principal principal = request.getPrincipal();

		boolean noMorePending = !this.repository.findHasPendingByUserAccountId(principal.getAccountId());
		errors.state(request, noMorePending, "responsibilityStatement", "authenticated.auditor-request.form.error.noMorePending");
	}

	@Override
	public void bind(final Request<BookkeeperRequest> request, final BookkeeperRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<BookkeeperRequest> request, final BookkeeperRequest entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "firmName", "responsibilityStatement");
	}

	@Override
	public BookkeeperRequest instantiate(final Request<BookkeeperRequest> request) {
		assert request != null;

		BookkeeperRequest result = new BookkeeperRequest();
		result.setStatus("PENDING");

		Principal principal = request.getPrincipal();
		int userAccountId = principal.getAccountId();
		UserAccount userAccount = this.repository.findOneUserAccountById(userAccountId);
		result.setUserAccount(userAccount);

		return result;
	}

	@Override
	public void create(final Request<BookkeeperRequest> request, final BookkeeperRequest entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
