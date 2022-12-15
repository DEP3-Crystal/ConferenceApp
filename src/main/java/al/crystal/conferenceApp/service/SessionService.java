package al.crystal.conferenceApp.service;

import al.crystal.conferenceApp.dto.SessionDTO;
import al.crystal.conferenceApp.model.Session;
import al.crystal.conferenceApp.model.Speaker;
import al.crystal.conferenceApp.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    public String createSession(SessionDTO sessionDTO) {
        Session newSession = Session.builder()
                .description(sessionDTO.getDescription())
                .endTime(sessionDTO.getEndTime())
                .startTime(sessionDTO.getStartTime())
                .title(sessionDTO.getTitle())
                .capacity(sessionDTO.getCapacity())
                .track(sessionDTO.getTrack())
                .event(sessionDTO.getEvent())
                .type(sessionDTO.getType())
                .speakers(sessionDTO.getSpeakers())
                .build();
        sessionRepository.save(newSession);
        return "done";
    }

    public String addSpeakers(Long sessionId, List<Speaker> speakers) {
        Session session = sessionRepository.getReferenceById(sessionId);
        session.getSpeakers().addAll(speakers);
        sessionRepository.save(session);
        return "done";
    }

    public Session getSession(Long id) {
        return sessionRepository.getReferenceById(id);
    }

    public List<Session> getSessionsByDate(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, dateTimeFormatter);
        System.out.println("Data formatted1:"+localDate);
        return sessionRepository.findSessionsByStartTime(localDate);
    }

    private List<Session> getSessionsByLocation(String location) {
        return sessionRepository.findAllByTrackRoomLocation(location);
    }

    private List<Session> getSessionsByDateAndLocation(String date, String location) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, dateTimeFormatter);
        System.out.println("Data formatted2:"+localDate);
        return sessionRepository.findAllByStartTimeAndTrackRoomLocation(localDate, location);
    }


    public List<Session> getAllSessions() {
        return sessionRepository.findAll(Sort.by("startTime"));
    }


    public List<Session> getSessions(String date, String location) {
        System.out.println("Location passed: "+location);
        System.out.println("Date passed: "+date);
        if (date != null && location == null) {
            return getSessionsByDate(date);
        } else if (date == null && location != null) {
            return getSessionsByLocation(location);
        } else if (date != null && location != null) {
            return getSessionsByDateAndLocation(date, location);
        } else
            return getAllSessions();
    }

    public List<Session> saveSessions(List<SessionDTO> sessionDTOS){
        List<Session> collect = sessionDTOS.stream().map(sessionDTO -> Session.builder()
                .description(sessionDTO.getDescription())
                .endTime(sessionDTO.getEndTime())
                .startTime(sessionDTO.getStartTime())
                .title(sessionDTO.getTitle())
                .capacity(sessionDTO.getCapacity())
                .track(sessionDTO.getTrack())
                .event(sessionDTO.getEvent())
                .type(sessionDTO.getType())
                .speakers(sessionDTO.getSpeakers())
                .build()).collect(Collectors.toList());
        return sessionRepository.saveAll(collect);
    }
}
