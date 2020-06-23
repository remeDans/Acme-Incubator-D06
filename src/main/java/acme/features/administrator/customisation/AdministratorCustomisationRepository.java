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

package acme.features.administrator.customisation;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.customisation.Customisation;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorCustomisationRepository extends AbstractRepository {

	@Query("select c from Customisation c where c.id =?1")
	Customisation findMOneById(int id);

	@Query("select c from Customisation c")
	Customisation findCustomisation();

	@Query("select c.spamWords_En from Customisation c")
	String findSpamWords_En();

	@Query("select c.spamWords_Es from Customisation c")
	String findSpamWords_Es();

	@Query("select c.spamThreshold from Customisation c")
	Double findSpamThreshold();

	@Query("select c.activitySectors from Customisation c")
	String findActivitySectors();

}
