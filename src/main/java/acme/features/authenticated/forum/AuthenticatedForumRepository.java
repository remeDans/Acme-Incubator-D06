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

package acme.features.authenticated.forum;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.forum.Forum;
import acme.entities.participant.Participant;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedForumRepository extends AbstractRepository {

	@Query("select f from Forum f")
	Collection<Forum> findMany();

	@Query("select f from Forum f where f.id =?1")
	Forum findOneById(int id);

	@Query("select f from Forum f join f.investmentRound i where i.id=?1")
	Forum findOneByInvestmentRoundId(int id);

	@Query("select distinct(f) from Message m join m.forum f where m.creator.id =?1 and f.creator.id =?1")
	Collection<Forum> findManyByCreatorMessageId(int creatorId);

	@Query("select f from Forum f where f.creator.id=?1")
	Collection<Forum> findManyByCreatorForumId(int creatorId);

	@Query("select a from Authenticated a where a.id = ?1")
	Authenticated findOneAuthenticatedById(int id);

	@Query("select p from Participant p where p.forum.id = ?1")
	Collection<Participant> findForumParticipant(int forumId);

}
