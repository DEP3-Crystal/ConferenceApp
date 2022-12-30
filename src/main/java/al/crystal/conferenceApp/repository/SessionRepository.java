package al.crystal.conferenceApp.repository;

import al.crystal.conferenceApp.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long> {

    @Query(value = "Select * from public.session  where DATE(session.start_time) =:startDate", nativeQuery = true)
    List<Session> findSessionsByStartTime(LocalDate startDate);

    List<Session> findAllByTrackRoomLocation(String room_location);

   @Query(value = "Select * from session s JOIN tracks t ON (t.id=s.track_id) where DATE(s.start_time) =:startDate and (t.room_location)=:roomLocation ", nativeQuery = true)
    List<Session> findAllByStartTimeAndTrackRoomLocation(LocalDate startDate, String roomLocation);

    @Query(value = "SELECT DISTINCT DATE(s.startTime) FROM session s " , nativeQuery = true)
    List<String> findDistinctByStartTime();
//
   @Query(value = "SELECT DISTINCT DATE(s.start_time) FROM session s JOIN tracks t ON (t.id=s.track_id) where (t.room_location)=:roomLocation", nativeQuery = true)
    List<String> findDistinctByStartTimeBasedOnLocation(String roomLocation);

    @Query(value = "SELECT DISTINCT (tracks.room_location) FROM tracks",nativeQuery = true)
    List<String> findDistinctByTrackRoomLocation();

    @Query(value = "SELECT DISTINCT (t.room_location) FROM tracks t JOIN session s ON (t.id=s.track_id) where DATE(s.start_time) =:startDate", nativeQuery = true)
    List<String> findDistinctByLocationBasedOnStartTime(String startDate);
}


//