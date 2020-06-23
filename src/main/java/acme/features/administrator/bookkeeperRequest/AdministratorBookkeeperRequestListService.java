
package acme.features.administrator.bookkeeperRequest;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bookkeeperRequest.BookkeeperRequest;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorBookkeeperRequestListService implements AbstractListService<Administrator, BookkeeperRequest> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AdministratorBookkeeperRequestRepository repository;


	@Override
	public boolean authorise(final Request<BookkeeperRequest> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<BookkeeperRequest> findMany(final Request<BookkeeperRequest> request) {
		assert request != null;
		Collection<BookkeeperRequest> result;
		result = this.repository.findManyAll();
		return result;
	}

	@Override
	public void unbind(final Request<BookkeeperRequest> request, final BookkeeperRequest entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "userAccount.username", "firmName", "responsibilityStatement");

	}

}
