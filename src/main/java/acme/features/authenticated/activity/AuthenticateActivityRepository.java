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

package acme.features.authenticated.activity;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.activity.Activity;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticateActivityRepository extends AbstractRepository {

	@Query("select a from Activity a")
	Collection<Activity> findMany();

	@Query("select a from Activity a where a.id =?1")
	Activity findMOneById(int id);

	@Query("select a from Activity a join a.investmentRound i where i.id=?1")
	Collection<Activity> findManyByInvestmentRound(int investmentRoundId);

}
