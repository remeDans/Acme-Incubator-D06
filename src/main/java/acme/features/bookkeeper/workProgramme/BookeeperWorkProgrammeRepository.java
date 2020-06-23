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

package acme.features.bookkeeper.workProgramme;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.workProgramme.WorkProgramme;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface BookeeperWorkProgrammeRepository extends AbstractRepository {

	@Query("select w from WorkProgramme w")
	Collection<WorkProgramme> findMany();

	@Query("select i from WorkProgramme i where i.id =?1")
	WorkProgramme findMOneById(int id);

	//@Query("select i from InvestmentRound i where i.deadline > current_date()")
	//Collection<InvestmentRound> findManyActive();

}
