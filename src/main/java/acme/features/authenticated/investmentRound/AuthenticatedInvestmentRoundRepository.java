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

package acme.features.authenticated.investmentRound;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.investmentRound.InvestmentRound;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedInvestmentRoundRepository extends AbstractRepository {

	@Query("select i from InvestmentRound i")
	Collection<InvestmentRound> findMany();

	@Query("select i from InvestmentRound i where i.id =?1")
	InvestmentRound findMOneById(int id);

	@Query("select i from Activity a join a.investmentRound i where a.startMoment <= current_date() and a.endMoment > current_date()")
	Collection<InvestmentRound> findManyActiveAntiguo();

	@Query("select distinct(i) from Activity a join a.investmentRound i where i.status = 'PUBLISHED'and i.deadline > current_date()")
	Collection<InvestmentRound> findManyActiveNuevo();
}
