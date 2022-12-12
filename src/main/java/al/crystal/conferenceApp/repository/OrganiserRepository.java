package al.crystal.conferenceApp.repository;

import al.crystal.conferenceApp.model.Organiser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrganiserRepository  extends JpaRepository<Organiser, Long> {
}
