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

package acme.features.patron.creditCard;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.creditCard.CreditCard;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronCreditCardRepository extends AbstractRepository {

	@Query("select c from CreditCard c where c.id =?1")
	CreditCard findOneById(int id);

	@Query("select c from CreditCard c where c.banner.id =?1")
	CreditCard findOneByBannerId(int id);

	@Query("select c from CreditCard c")
	Collection<CreditCard> findManyAll();

	@Query("select c from CreditCard c where c.patron.id =?1")
	CreditCard findOneByPatronId(int id);

}
