package al.crystal.conferenceApp.repository;

import al.crystal.conferenceApp.model.ParticipantSession;
import al.crystal.conferenceApp.model.ParticipantSessionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface ParticipantSessionRepository extends JpaRepository<ParticipantSession, ParticipantSessionId> {
    @Modifying
    @Transactional
    @Query(value = "Delete FROM participant_session ps where (ps.session_id)=:id", nativeQuery = true)
    void deleteBySessionId(Long id);


}
