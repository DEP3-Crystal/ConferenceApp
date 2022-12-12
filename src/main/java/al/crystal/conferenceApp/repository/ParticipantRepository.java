package al.crystal.conferenceApp.repository;

import al.crystal.conferenceApp.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ParticipantRepository  extends JpaRepository<Participant, Long> {
}
