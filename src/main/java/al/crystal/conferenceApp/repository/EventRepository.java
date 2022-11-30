package al.crystal.conferenceApp.repository;

import al.crystal.conferenceApp.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
}
