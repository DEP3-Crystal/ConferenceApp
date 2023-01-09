package al.crystal.conferenceApp.repository;

import al.crystal.conferenceApp.model.Event;
import al.crystal.conferenceApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByOrganiser(User user);


//    @Query("select e from events e where e.end_day >= :end_day")
//    List<Event> findAllWithEndDayAfter(
//            @Param("end_day") LocalDate end_day);
//
//    @Query("select e from events e where e.start_day <= :start_day")
//    List<Event> findAllWithStartDayBefore(
//            @Param("start_day") LocalDate start_day);

//    SELECT * FROM events e where (start_day between e.start_day and e.end_day) or  (end_day between e.start_day and e.end_day)
    @Query(value = "SELECT * FROM conferencedb.events s where (:start_day between s.start_day and s.end_day) or (:end_day between s.start_day and s.end_day)",
            nativeQuery = true)
    List<Event> findEventsDate(@Param("start_day") LocalDate start_day, @Param("end_day") LocalDate end_day);

    @Query(value = "SELECT * FROM conferencedb.events s where (:today <= s.start_day)",
            nativeQuery = true)
    List<Event> eventsToShowAfter(@Param("today") LocalDate today);

    @Query(value = "SELECT * FROM conferencedb.events s where (:today between s.start_day and s.end_day)",
            nativeQuery = true)
    List<Event> eventToShowNow(@Param("today") LocalDate today);


     

}
