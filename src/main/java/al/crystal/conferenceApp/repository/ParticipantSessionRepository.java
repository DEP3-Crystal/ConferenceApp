package al.crystal.conferenceApp.repository;

import al.crystal.conferenceApp.model.ParticipantSession;
import al.crystal.conferenceApp.model.ParticipantSessionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantSessionRepository extends JpaRepository<ParticipantSession, ParticipantSessionId> {
}
