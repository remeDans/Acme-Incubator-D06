
package acme.features.administrator.bookkeeperRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bookkeeperRequest.BookkeeperRequest;
import acme.entities.roles.Bookkeeper;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorBookkeeperRequestUpdateService implements AbstractUpdateService<Administrator, BookkeeperRequest> {

	@Autowired
	AdministratorBookkeeperRequestRepository repository;


	@Override
	public boolean authorise(final Request<BookkeeperRequest> request) {
		assert request != null;

		int bookkeeperRequestId = request.getModel().getInteger("id");
		BookkeeperRequest bookkeeperRequest = this.repository.findOneById(bookkeeperRequestId);
		boolean result = bookkeeperRequest.getStatus().equals("PENDING");

		return result;

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

		request.unbind(entity, model, "userAccount.username", "firmName", "responsibilityStatement", "status");
	}

	@Override
	public BookkeeperRequest findOne(final Request<BookkeeperRequest> request) {
		assert request != null;

		BookkeeperRequest result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<BookkeeperRequest> request, final BookkeeperRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void update(final Request<BookkeeperRequest> request, final BookkeeperRequest entity) {
		assert request != null;
		assert entity != null;

		if (entity.getStatus().equals("ACCEPTED")) {
			Bookkeeper bookkeeper = new Bookkeeper();
			bookkeeper.setFirmName(entity.getFirmName());
			bookkeeper.setResponsibilityStatement(entity.getResponsibilityStatement());
			bookkeeper.setUserAccount(entity.getUserAccount());
			this.repository.save(bookkeeper);
		}

		this.repository.save(entity);
	}

}
