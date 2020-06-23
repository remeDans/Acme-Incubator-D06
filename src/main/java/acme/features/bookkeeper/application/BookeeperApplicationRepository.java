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

package acme.features.bookkeeper.application;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.application.Application;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface BookeeperApplicationRepository extends AbstractRepository {

	@Query("select a from Application a where a.id =?1")
	Application findOneById(int id);

	@Query("select a from Application a where a.investor.id =?1")
	Collection<Application> findManyByInvestorId(int investorId);

	@Query("select a from Application a")
	Collection<Application> findMany();

	@Query("select a from Application a join a.investmentRound i where i.id =?1")
	Application findOneByInvestmentRoundId(int id);

	@Query("select a from Application a join a.investmentRound i where i.entrepreneur.id =?1")
	Collection<Application> findManyByEntrepreneurId(int id);

	//@Query("select j from Application a join a.job j where a.id=?1")
	//Collection<Job> findManyByApplication(int applicationId);

	//@Query("select j from Application a join a.job j where a.id=?1")
	//Collection<Job> findManyJobsByApplication(int applicationId);

	//@Query("select w from Worker w where w.id =?1")
	//Worker findOneByIdWorker(int id);

	//@Query("select j from Job j")
	//Collection<Job> findManyAllJob();

	@Query("select a.id from Application a")
	Collection<Integer> findManyId();

	//@Query("select j from Job j where j.finalMode=true and j.deadline > ?1 and j.draft=false and j.id not in( select j.id from Application a join a.job j )")
	//Collection<Job> findManyJobsActivesWithoutApplication(Date actualDate);

	//@Query("Select c from Configuration c")
	//Configuration findConfig();

	//@Query("select a from Application a where a.reference =?1")
	//Application findOneByReference(String reference);

	//@Query("select j from Job j where j.id =?1")
	//Job findOneJobById(int id);

}
