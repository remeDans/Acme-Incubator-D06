
package acme.features.authenticated.bookkeeperRequest;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.bookkeeperRequest.BookkeeperRequest;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/bookkeeper-request/")
public class AuthenticatedBookkeeperRequestController extends AbstractController<Authenticated, BookkeeperRequest> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedBookkeeperRequestCreateService createService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}

}
