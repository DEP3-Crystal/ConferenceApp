package al.crystal.conferenceApp.repository;

import al.crystal.conferenceApp.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

public interface ParticipantRepository  extends JpaRepository<Participant, UUID> {
}
