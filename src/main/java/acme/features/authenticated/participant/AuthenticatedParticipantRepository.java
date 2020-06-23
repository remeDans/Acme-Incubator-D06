
package acme.features.authenticated.participant;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.forum.Forum;
import acme.entities.participant.Participant;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedParticipantRepository extends AbstractRepository {

	@Query("select p from Participant p where p.id = ?1")
	Participant findOneById(int id);

	@Query("select mt from Forum mt where mt.id = ?1")
	Forum findOneForumdById(int forumId);

	@Query("select count(mt)>0 from Forum mt where mt.id = ?1 and mt.creator.id = ?2")
	Boolean findIsForumCreator(int threadId, int creatorId);

	@Query("select count(p)>0 from Participant p where p.forum.id = ?1 and p.authenticated.id = ?2")
	Boolean findIsForumParticipant(int forumId, int authenticatedId);

	@Query("select count(p)>0 from Participant p where p.forum.id = ?1 and p.authenticated.id = ?2")
	Boolean findAuthenticatedExists(int authenticatedId);

	@Query("select a from Authenticated a where a.id = ?1")
	Authenticated findOneAuthenticatedById(int id);

	@Query("select p from Participant p where p.forum.id = ?1")
	Collection<Participant> findManyParticipantsByForumId(int forumId);

	@Query("select a from Authenticated a where a.userAccount.username = ?1")
	Authenticated findOneAuthenticatedByUsername(String username);
}
