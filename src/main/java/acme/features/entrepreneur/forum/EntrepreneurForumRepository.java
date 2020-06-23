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

package acme.features.entrepreneur.forum;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.forum.Forum;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EntrepreneurForumRepository extends AbstractRepository {

	@Query("select f from Forum f")
	Collection<Forum> findMany();

	@Query("select f from Forum f where f.id =?1")
	Forum findMOneById(int id);

	@Query("select f from Forum f join f.investmentRound i where i.id=?1")
	Forum findOneByInvestmentRoundId(int id);

}
