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

package acme.features.bookkeeper.accountingRecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.accountingRecord.AccountingRecord;
import acme.entities.roles.Bookkeeper;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface BookeeperAccountingRecordRepository extends AbstractRepository {

	@Query("select a from AccountingRecord a")
	Collection<AccountingRecord> findMany();

	@Query("select a from AccountingRecord a where a.id =?1")
	AccountingRecord findMOneById(int id);

	@Query("select a from AccountingRecord a join a.investmentRound i where i.id=?1")
	Collection<AccountingRecord> findManyByInvestmentRound(int investmentRoundId);

	@Query("select a from AccountingRecord a where a.bookkeeper.id =?1")
	AccountingRecord findMOneByBookkeeperId(int id);

	@Query("select b from Bookkeeper b where b.id =?1")
	Bookkeeper findOneBookkeepeByBookkeeperId(int id);
}
