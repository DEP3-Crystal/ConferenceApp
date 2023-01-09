package al.crystal.conferenceApp;

import al.crystal.conferenceApp.dto.EventDTO;
import al.crystal.conferenceApp.dto.OrganizerDTO;
import al.crystal.conferenceApp.faker.FakerDataAccess;
import al.crystal.conferenceApp.model.Organiser;
import al.crystal.conferenceApp.repository.SpeakerRateRepository;
import al.crystal.conferenceApp.service.EventService;
import al.crystal.conferenceApp.service.OrganizerService;
import al.crystal.conferenceApp.service.ParticipantService;
import al.crystal.conferenceApp.service.SpeakerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@Configuration
//@EnableJpaRepositories(basePackages = "al.crystal.conferenceApp")
public class ConferenceAppApplication implements CommandLineRunner {

    @Autowired
    private OrganizerService organizerService;
    @Autowired
    private SpeakerRateRepository speakerRateRepository;
    @Autowired
    private ParticipantService participantService;
    @Autowired
    private SpeakerService speakerService;
    @Autowired
    private FakerDataAccess fakerDataAccess;

    @Autowired
    private EventService eventService;
    public static void main(String[] args) {
        SpringApplication.run(ConferenceAppApplication.class, args);
    }
    Logger logger= LoggerFactory.getLogger(this.getClass().getName());
    @Override
    public void run(String... args) throws Exception {
        OrganizerDTO organizerDTO=new OrganizerDTO("fda","fea","fabfr",
                "ewfeadw","ewfeadw","ewfeadw",
                "ewfeadw","ewfead","feas","fa");
        organizerService.addOrganizer(organizerDTO);
        Organiser organizer = organizerService.getOrganizer(1L);
        fakerDataAccess.createSessions(5,3,15,organizer,50);

        EventDTO event = fakerDataAccess.createEvent(organizer);
        eventService.saveEvent(event);

    }
}
