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

package acme.features.administrator.toolRecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.toolRecord.ToolRecord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorToolRecordRepository extends AbstractRepository {

	@Query("select t from ToolRecord t")
	Collection<ToolRecord> findMany();

	@Query("select t from ToolRecord t where t.id =?1")
	ToolRecord findMOneById(int id);

}
