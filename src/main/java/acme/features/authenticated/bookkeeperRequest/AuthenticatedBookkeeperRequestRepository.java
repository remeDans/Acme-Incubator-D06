
package acme.features.authenticated.bookkeeperRequest;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.bookkeeperRequest.BookkeeperRequest;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedBookkeeperRequestRepository extends AbstractRepository {

	@Query("select ar from BookkeeperRequest ar where ar.id = ?1")
	BookkeeperRequest findOneById(int id);

	@Query("select count(ar)>0 from BookkeeperRequest ar where ar.userAccount.id = ?1 and ar.status = 'PENDING'")
	Boolean findHasPendingByUserAccountId(int id);

	@Query("select count(a)>0 from Bookkeeper a where a.userAccount.id = ?1")
	Boolean findIsBookkeeperByUserAccountId(int id);

	@Query("select ua from UserAccount ua where ua.id = ?1")
	UserAccount findOneUserAccountById(int id);
}
