package al.crystal.conferenceApp.repository;

import al.crystal.conferenceApp.model.Event;
import al.crystal.conferenceApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByOrganiser(User user);

}
