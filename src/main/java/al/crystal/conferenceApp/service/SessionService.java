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

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    public String createSession(SessionDTO sessionDTO) {
        Session newSession = Session.builder().description(sessionDTO.getDescription())
                .endTime(sessionDTO.getEndTime())
                .startTime(sessionDTO.getStartTime())
                .title(sessionDTO.getTitle())
                .capacity(sessionDTO.getCapacity())
                .track(sessionDTO.getTrack())
                .type(sessionDTO.getType())
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
        return sessionRepository.findSessionsByStartTime(localDate);
    }

    public List<Session> getAllSessions() {
        return sessionRepository.findAll(Sort.by("startTime"));
    }
}
