package al.crystal.conferenceApp.repository;

import al.crystal.conferenceApp.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long> {

    @Query(value = "Select * from session s where DATE(s.start_time) =:startDate", nativeQuery = true)
    List<Session> findSessionsByStartTime(LocalDate startDate);

    List<Session> findAllByTrackRoomLocation(String roomLocation);

    @Query(value = "Select * from session s JOIN tracks t  ON (t.id=s.track_id) where DATE(s.start_time) =:startDate and (t.room_location)=:roomLocation ", nativeQuery = true)
    List<Session> findAllByStartTimeAndTrackRoomLocation(LocalDate startDate, String roomLocation);
}
