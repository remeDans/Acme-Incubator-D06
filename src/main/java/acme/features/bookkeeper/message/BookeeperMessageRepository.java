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

package acme.features.bookkeeper.message;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.message.Message;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface BookeeperMessageRepository extends AbstractRepository {

	@Query("select m from Message m")
	Collection<Message> findMany();

	@Query("select m from Message m join m.forum f where f.id=?1")
	Collection<Message> findManyByForum(int id);

	@Query("select m from Message m where m.id =?1")
	Message findMOneById(int id);

}
