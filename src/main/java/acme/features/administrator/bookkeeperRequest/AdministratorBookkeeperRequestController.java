
package acme.features.administrator.bookkeeperRequest;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.bookkeeperRequest.BookkeeperRequest;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/bookkeeper-request/")
public class AdministratorBookkeeperRequestController extends AbstractController<Administrator, BookkeeperRequest> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AdministratorBookkeeperRequestListService	listService;

	@Autowired
	private AdministratorBookkeeperRequestShowService	showService;

	@Autowired
	private AdministratorBookkeeperRequestUpdateService	updateService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
	}

}
