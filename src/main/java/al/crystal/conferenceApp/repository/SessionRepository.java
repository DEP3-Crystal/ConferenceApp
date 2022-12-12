package al.crystal.conferenceApp.repository;

import al.crystal.conferenceApp.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {
}
