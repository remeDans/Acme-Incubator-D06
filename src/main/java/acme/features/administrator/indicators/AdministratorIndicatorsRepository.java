
package acme.features.administrator.indicators;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorIndicatorsRepository extends AbstractRepository {

	@Query("select a.creationMoment, count(a) from Application a where a.creationMoment >= ?1 and a.status = 'PENDING' group by day(a.creationMoment), month(a.creationMoment), year(a.creationMoment)")
	Object[] findPendingApplicationsPerDay(Date after);

	@Query("select a.creationMoment, count(a) from Application a where a.creationMoment >= ?1 and a.status = 'ACCEPTED' group by day(a.creationMoment), month(a.creationMoment), year(a.creationMoment)")
	Object[] findAcceptedApplicationsPerDay(Date after);

	@Query("select a.creationMoment, count(a) from Application a where a.creationMoment >= ?1 and a.status = 'REJECTED' group by day(a.creationMoment), month(a.creationMoment), year(a.creationMoment)")
	Object[] findRejectedApplicationsPerDay(Date after);

}
