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

package acme.features.bookkeeper.investmentRound;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.investmentRound.InvestmentRound;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface BookeeperInvestmentRoundRepository extends AbstractRepository {

	@Query("select i from InvestmentRound i")
	Collection<InvestmentRound> findMany();

	@Query("select i from InvestmentRound i where i.id =?1")
	InvestmentRound findMOneById(int id);

	@Query("select i from Activity a join  a.investmentRound i where a.startMoment <= current_date() and a.endMoment > current_date()")
	Collection<InvestmentRound> findManyActive();

	@Query("select i from InvestmentRound i where i.entrepreneur.id =?1")
	Collection<InvestmentRound> findManyByEntrepreneurId(int entrepreneurId);

	@Query("select distinct(i) from AccountingRecord a join a.investmentRound i  where a.bookkeeper.id =?1")
	Collection<InvestmentRound> findManyByBookkeeperId(int bookkeeperId);

	@Query("select distinct(i) from InvestmentRound i where i.id not in (select i from AccountingRecord a join a.investmentRound i where a.bookkeeper.id =?1)")
	Collection<InvestmentRound> findManyNOByBookkeeperId(int bookkeeperId);
}
