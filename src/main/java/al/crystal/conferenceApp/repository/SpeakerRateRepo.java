package al.crystal.conferenceApp.repository;

import al.crystal.conferenceApp.model.SpeakerRate;
import al.crystal.conferenceApp.model.SpeakerRateId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeakerRateRepo extends JpaRepository<SpeakerRate, SpeakerRateId> {
}
