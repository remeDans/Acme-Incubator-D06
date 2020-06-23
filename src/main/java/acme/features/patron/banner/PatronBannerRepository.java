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

package acme.features.patron.banner;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banner.Banner;
import acme.entities.creditCard.CreditCard;
import acme.entities.roles.Patron;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronBannerRepository extends AbstractRepository {

	@Query("select b from Banner b where b.id =?1")
	Banner findOneById(int id);

	@Query("select b from Banner b")
	Collection<Banner> findManyAll();

	@Query("Select b from Banner b where b.patron.id = ?1")
	Collection<Banner> findManyByPatronId(int patronId);

	@Query("select p from Patron p where p.id =?1")
	Patron findOneByIdPatron(int id);

	@Query("select c from CreditCard c join c.banner b where b.id =?1")
	CreditCard findOneCreditcard(int id);

}
