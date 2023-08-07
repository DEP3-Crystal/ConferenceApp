package al.crystal.conferenceApp.repository;

import al.crystal.conferenceApp.model.Organiser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrganiserRepository extends JpaRepository<Organiser, Long> {

    @Query(value = "Select * from organiser where user_id =:id", nativeQuery = true)
    Organiser findOrganiserBy(Long id);
}
