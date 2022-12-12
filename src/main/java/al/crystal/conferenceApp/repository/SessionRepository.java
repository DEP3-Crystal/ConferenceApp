package al.crystal.conferenceApp.repository;

import al.crystal.conferenceApp.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long> {

    @Query(value = "Select * from session s where DATE(s.start_time) =:startDate", nativeQuery = true)
    List<Session> findSessionsByStartTime(LocalDate startDate);
}
