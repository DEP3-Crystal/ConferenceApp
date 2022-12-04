package al.crystal.conferenceApp.repository;

import al.crystal.conferenceApp.model.Event;
import al.crystal.conferenceApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {
    public List<Event> findByOrganiser(User user);

}
