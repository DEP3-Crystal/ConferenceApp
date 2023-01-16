package al.crystal.conferenceApp.service;

import al.crystal.conferenceApp.dto.SessionDTO;
import al.crystal.conferenceApp.exception.IllegalException;
import al.crystal.conferenceApp.exception.ResourceNotFoundException;
import al.crystal.conferenceApp.mapper.SessionMapper;
import al.crystal.conferenceApp.mapper.SpeakerMapper;
import al.crystal.conferenceApp.model.*;
import al.crystal.conferenceApp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;
    private final TrackRepository trackRepository;
    private final EventRepository eventRepository;
    private final ParticipantSessionRepository participantSessionRepository;
    private final UserRepository userRepository;
    private final ParticipantRepository participantRepository;
    private final SpeakerMapper speakerMapper = SpeakerMapper.Instance;
    private final SessionMapper sessionMapper = SessionMapper.Instance;

    @Autowired
    public SessionService(SessionRepository sessionRepository,
                          TrackRepository trackRepository,
                          EventRepository eventRepository,
                          ParticipantSessionRepository participantSessionRepository,
                          UserRepository userRepository,
                          ParticipantRepository participantRepository) {
        this.sessionRepository = sessionRepository;
        this.trackRepository = trackRepository;
        this.eventRepository = eventRepository;
        this.participantSessionRepository = participantSessionRepository;
        this.userRepository = userRepository;
        this.participantRepository = participantRepository;
    }


    public Session createSession(SessionDTO sessionDTO) throws ResourceNotFoundException {

        if (sessionDTO.getTrack() == null) {
            throw new ResourceNotFoundException("Track not found");
        }
        long selectedTrackId = sessionDTO.getTrack().getId();
        boolean isIncludedTrackId = selectedTrackId == 0;
        if (isIncludedTrackId) {
            throw new ResourceNotFoundException("TrackId not found");
        }
        Track selectedTrack = trackRepository.findById(selectedTrackId).orElseThrow(() -> new ResourceNotFoundException("Track not found in DB"));

        long selectedEventId = sessionDTO.getEvent().getId();
        if (selectedEventId == 0) {
            throw new ResourceNotFoundException("Event not found");
        }
        Event selectedEvent = eventRepository.findById(selectedEventId).orElseThrow(() -> new ResourceNotFoundException("Event not found"));

        //Date validation:

        List<SessionDTO> sessionsByEvent = getSessionsByEvent(selectedEventId);
        for (SessionDTO session : sessionsByEvent) {
            if (session.getStartTime().isBefore(sessionDTO.getEndTime()) && session.getEndTime().isAfter(sessionDTO.getStartTime())
                    || session.getStartTime().isBefore(sessionDTO.getStartTime()) && session.getEndTime().isAfter(sessionDTO.getStartTime())
                    || session.getStartTime().isBefore(sessionDTO.getEndTime()) && session.getEndTime().isAfter(sessionDTO.getEndTime())) {
                throw new IllegalException("Conflicting session found. Session ID: " + session.getId());
            }
        }


        Session newSession = Session.builder()
                .description(sessionDTO.getDescription())
                .endTime(sessionDTO.getEndTime())
                .startTime(sessionDTO.getStartTime())
                .title(sessionDTO.getTitle())
                .capacity(sessionDTO.getCapacity())
                .track(selectedTrack)
                .event(selectedEvent)
                .type(sessionDTO.getType())
                .speakers(sessionDTO.getSpeakersDTO().stream().map(speakerMapper::speaker).collect(Collectors.toList()))
                .build();
        return sessionRepository.save(newSession);
    }


    public SessionDTO getOneSession(Long id) {
        Optional<Long> optionalId = Optional.ofNullable(id);
        if (!optionalId.isPresent()) {
            throw new IllegalArgumentException("Session id is missing");
        }
        Optional<Session> session = sessionRepository.findById(id);
        if (!session.isPresent()) {
            throw new ResourceNotFoundException("Session not found in DB");
        }
        SessionDTO sessionDTO = sessionMapper.sessionToSessionDTO(session.get());
        try {
            sessionDTO.setParticipation(participantSessionRepository.getParticipationForSession(sessionDTO.getId()));
        } catch (Exception e) {
            throw new ResourceNotFoundException("Error retrieving participation for session");
        }
        return sessionDTO;

    }

    private List<SessionDTO> getSessionsByDateBasedOnEvent(String date, Long id) {
        LocalDate localDate = stringToLocalDate(date);
        List<Session> sessionList = sessionRepository.findSessionsByStartTime(localDate, id);
        return sessionsToSessionsDTO(sessionList);
    }

    private List<SessionDTO> getSessionsByLocationBasedOnEvent(String location, Long id) {
        List<Session> sessionList = sessionRepository.findAllByTrackRoomLocationAndEventId(location, id);
        return sessionsToSessionsDTO(sessionList);
    }

    private List<SessionDTO> getSessionsByEvent(Long id) {
        List<Session> sessionList = sessionRepository.findAllByEventId(id);
        return sessionsToSessionsDTO(sessionList);
    }

    private List<SessionDTO> getSessionsByDateAndLocationBasedOnEvent(String date, String location, Long id) {
        LocalDate localDate = stringToLocalDate(date);
        List<Session> sessionList = sessionRepository.findAllByStartTimeAndTrackRoomLocation(localDate, location, id);
        return sessionsToSessionsDTO(sessionList);
    }

    private List<SessionDTO> getSessionsByDateAndLocation(String date, String location) {
        LocalDate localDate = stringToLocalDate(date);
        List<Session> sessionList = sessionRepository.findAllByDateAndLocation(localDate, location);
        return sessionsToSessionsDTO(sessionList);
    }


    public List<Session> getAllSessions() {
        return sessionRepository.findAll(Sort.by("startTime"));
    }

    public List<SessionDTO> getAllSessionsDTO() {
        List<Session> sessionList = sessionRepository.findAll(Sort.by("startTime"));
        List<SessionDTO> sessionDTOS = sessionsToSessionsDTO(sessionList);
        return sessionDTOS;
    }


    public List<SessionDTO> getSessions(String date, String location, Long id) {
        if (date == null && location == null && id != null) {
            return getSessionsByEvent(id);
        } else if (date != null && location == null && id != null) {
            return getSessionsByDateBasedOnEvent(date, id);
        } else if (date == null && location != null && id != null) {
            return getSessionsByLocationBasedOnEvent(location, id);
        } else if (date != null && location != null && id != null) {
            return getSessionsByDateAndLocationBasedOnEvent(date, location, id);
        } else if (date != null && location != null && id == null) {
            return getSessionsByDateAndLocation(date, location);
        } else if (date == null && location != null && id == null) {
            return getSessionsByLocation(location);
        } else if (date != null && location == null && id == null) {
            return getSessionsByDate(date);
        } else {
            List<Session> sessionList = getAllSessions();
            List<SessionDTO> sessionDTOS = sessionsToSessionsDTO(sessionList);
            sessionDTOS.forEach(sessionDTO ->
                    sessionDTO.setParticipation(participantSessionRepository.getParticipationForSession(sessionDTO.getId())));
            return sessionDTOS;
        }
    }

    private List<SessionDTO> getSessionsByDate(String date) {
        LocalDate localDate = stringToLocalDate(date);
        List<Session> sessionList = sessionRepository.findAllByDate(localDate);
        return sessionsToSessionsDTO(sessionList);
    }

    private List<SessionDTO> getSessionsByLocation(String location) {
        List<Session> sessionList = sessionRepository.findAllByTrackRoomLocation(location);
        return sessionsToSessionsDTO(sessionList);
    }


    public List<Session> saveSessionList(List<Session> sessionData) {
        return sessionRepository.saveAll(sessionData);
    }

    private List<SessionDTO> sessionsToSessionsDTO(List<Session> sessionList) {
        return sessionList.stream()
                .map(sessionMapper::sessionToSessionDTO)
                .collect(Collectors.toList());
    }

    private LocalDate stringToLocalDate(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, dateTimeFormatter);
    }

    public List<String> getSessionsDates(String location, Long eventId) {
        if (location != null && !location.equals("") && eventId == null) {
            return sessionRepository.findDistinctStartTimeBasedOnLocation(location);
        } else if (location != null && !location.equals("") && eventId != null) {
            return sessionRepository.findDistinctStartTimeBasedOnLocationAndEventId(location, eventId);
        } else if (location == null && eventId != null) {
            return sessionRepository.findDistinctStartTimeBasedOnEventId(eventId);
        } else {
            return sessionRepository.findDistinctStartTime();
        }
    }

    public List<String> getSessionsLocations(String date, Long eventId) {
        if (date != null && !date.equals("") && eventId == null) {
            return sessionRepository.findDistinctLocationBasedOnStartTime(date);
        } else if (date != null && !date.equals("") && eventId != null) {
            return sessionRepository.findDistinctLocationBasedOnStartTimeAndEventId(date, eventId);
        } else if (date == null && eventId != null) {
            return sessionRepository.findDistinctLocationBasedOnEventId(eventId);
        } else {
            return sessionRepository.findDistinctTrackRoomLocation();
        }
    }


    public void deleteSession(Long id) {
        participantSessionRepository.deleteBySessionId(id);
        sessionRepository.deleteById(id);
    }

    public SessionDTO updateSession(SessionDTO sessionDTO) {
        Session sessionOnDB = sessionRepository.findById(sessionDTO.getId()).get();

        sessionOnDB.setTitle(sessionDTO.getTitle());
        sessionOnDB.setDescription(sessionDTO.getDescription());
        sessionOnDB.setType(sessionDTO.getType());
        sessionOnDB.setCapacity(sessionDTO.getCapacity());
        sessionOnDB.setStartTime(sessionDTO.getStartTime());
        sessionOnDB.setEndTime(sessionDTO.getEndTime());
        sessionOnDB.setTrack(sessionDTO.getTrack());
        sessionOnDB.setSpeakers(sessionDTO.getSpeakersDTO().stream().map(speakerMapper::speaker).collect(Collectors.toList()));

        Session session = sessionRepository.saveAndFlush(sessionOnDB);
        return sessionMapper.sessionToSessionDTO(session);

    }

    public boolean rateSession(String email, Long sessionId, int rate) {
        Participant user = participantRepository.findByEmail(email);
        ParticipantSession participatedSession = participantSessionRepository.findByParticipantIdAndSessionId(user.getId(), sessionId);

        if (participatedSession != null) {
            if (participatedSession.getRating() != null) {
                return false;
            } else {
                participatedSession.setRating(rate);
                participantSessionRepository.saveAndFlush(participatedSession);
                return true;
            }
        }
        return false;
    }

    public Integer checkRatedSession(String email, Long sessionId) {
        Participant user = participantRepository.findByEmail(email);
        if (user != null) {
            ParticipantSession participatedSession = participantSessionRepository.findByParticipantIdAndSessionId(user.getId(), sessionId);
            if (participatedSession != null) {
                if (participatedSession.getRating() != null) {
                    return participatedSession.getRating();
                } else {
                    return null;
                }
            } else {
                return -1;
            }
        } else return -1;
    }
}
