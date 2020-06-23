/*
 * AnonymousUserAccountRepository.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.authenticated.notice;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.notice.Notice;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedNoticeRepository extends AbstractRepository {

	@Query("select n from Notice n")
	Collection<Notice> findMany();

	@Query("select n from Notice n where n.deadline > current_date()")
	Collection<Notice> findManyActive();

	@Query("select n from Notice n where n.id =?1")
	Notice findMOneById(int id);

}
