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

package acme.features.authenticated.message;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.forum.Forum;
import acme.entities.message.Message;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageRepository extends AbstractRepository {

	@Query("select m from Message m")
	Collection<Message> findMany();

	@Query("select m from Message m join m.forum f where f.id=?1")
	Collection<Message> findManyByForum(int id);

	@Query("select m from Message m where m.id =?1")
	Message findMOneById(int id);

	@Query("select mt from Forum mt where mt.id = ?1")
	Forum findOneById(int id);

	@Query("select distinct p.forum from Participant p where p.authenticated.id = ?1")
	Collection<Forum> findManyByAuthenticatedId(int authenticatedId);

	@Query("select count(p)>0 from Participant p where p.forum.id = ?1 and p.authenticated.id = ?2")
	Boolean findExistsForumParticipant(int messageThreadId, int authenticatedId);

	@Query("select a from Authenticated a where a.id = ?1")
	Authenticated findOneAuthenticatedById(int id);

}
