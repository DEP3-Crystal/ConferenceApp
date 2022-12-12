package al.crystal.conferenceApp.repository;

import al.crystal.conferenceApp.model.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeakerRepository extends JpaRepository<Speaker,Long> {
}
