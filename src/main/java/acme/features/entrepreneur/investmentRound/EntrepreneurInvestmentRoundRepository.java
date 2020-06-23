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

package acme.features.entrepreneur.investmentRound;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.investmentRound.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EntrepreneurInvestmentRoundRepository extends AbstractRepository {

	@Query("select i from InvestmentRound i")
	Collection<InvestmentRound> findMany();

	@Query("select i from InvestmentRound i where i.id =?1")
	InvestmentRound findMOneById(int id);

	@Query("select i from Activity a join a.investmentRound i where a.startMoment <= current_date() and a.endMoment > current_date()")
	Collection<InvestmentRound> findManyActive();

	@Query("select i from InvestmentRound i where i.entrepreneur.id =?1")
	Collection<InvestmentRound> findManyByEntrepreneurId(int entrepreneurId);

	//@Query("select i from Activity a join a.investmentRound i where a.id =?1")
	//Collection<InvestmentRound> findManyByActivityId(int activityId);

	@Query("select i from Activity a join a.investmentRound i where a.id =?1")
	InvestmentRound findOneByActivityId(int activityId);

	@Query("select i from InvestmentRound i where i.ticker =?1")
	InvestmentRound findOneByTicker(String ticker);

	//-------------------
	@Query("select p from Entrepreneur p where p.id =?1")
	Entrepreneur findOneEntrepreneurByEntrepreneurId(int id);

}
