
package acme.features.administrator.bookkeeperRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bookkeeperRequest.BookkeeperRequest;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorBookkeeperRequestShowService implements AbstractShowService<Administrator, BookkeeperRequest> {

	@Autowired
	private AdministratorBookkeeperRequestRepository repository;


	@Override
	public boolean authorise(final Request<BookkeeperRequest> request) {
		assert request != null;

		int bookkeeperRequestId = request.getModel().getInteger("id");
		BookkeeperRequest bookkeeperRequest = this.repository.findOneById(bookkeeperRequestId);
		boolean result = bookkeeperRequest.getStatus().equals("PENDING");

		return result;
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
		int id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}

}
