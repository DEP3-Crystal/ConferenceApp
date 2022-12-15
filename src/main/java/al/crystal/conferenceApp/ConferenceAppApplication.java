package al.crystal.conferenceApp;

import al.crystal.conferenceApp.dto.EventDTO;
import al.crystal.conferenceApp.dto.SessionDTO;
import al.crystal.conferenceApp.dto.TrackDTO;
import al.crystal.conferenceApp.faker.FakerDataAccess;
import al.crystal.conferenceApp.model.*;
import al.crystal.conferenceApp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
//@Configuration
//@EnableJpaRepositories(basePackages = "al.crystal.conferenceApp")
public class ConferenceAppApplication implements CommandLineRunner {
    @Autowired
    private EventService eventService;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private SpeakerService speakerService;
    @Autowired
    private TrackService trackService;
    @Autowired
    private OrganizerService organizerService;
    public static void main(String[] args) {
        SpringApplication.run(ConferenceAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        Organiser organizer = organizerService.getOrganizer(1L);
//        System.out.println(organizer);
//        FakerDataAccess fakerDataAccess=FakerDataAccess.getInstance();
//        EventDTO event = fakerDataAccess.createEvent(1, organizer);
//        System.out.println(event);
//        Event event1 = eventService.saveEvent(event);
//        List<TrackDTO> trackDTOS = fakerDataAccess.trackDTOList(3);
//        System.out.println(trackDTOS);
//        List<Track> tracks = trackService.saveTracks(trackDTOS);
//        List<Speaker> speakers = fakerDataAccess.speakerList(3);
//        System.out.println(speakers);
//        List<Speaker> speakers1 = speakerService.saveListOfSpeaker(speakers);
//        List<SessionDTO> sessionDTOS = fakerDataAccess.sessionList(3, event1, tracks, speakers1);
//        List<Session> sessions = sessionService.saveSessions(sessionDTOS);
//        System.out.println(sessions);
    }
}
