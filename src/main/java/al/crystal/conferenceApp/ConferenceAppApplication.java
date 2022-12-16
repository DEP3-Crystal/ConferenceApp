package al.crystal.conferenceApp;

import al.crystal.conferenceApp.dto.ParticipantDTO;
import al.crystal.conferenceApp.dto.SpeakerSessionRateDTO;
import al.crystal.conferenceApp.dto.speaker.SpeakerRateDTO;
import al.crystal.conferenceApp.faker.FakerDataAccess;
import al.crystal.conferenceApp.mapper.SpeakerSessionRateMapper;
import al.crystal.conferenceApp.model.Organiser;
import al.crystal.conferenceApp.model.Speaker;
import al.crystal.conferenceApp.model.SpeakerRate;
import al.crystal.conferenceApp.model.SpeakerRateId;
import al.crystal.conferenceApp.repository.SpeakerRateRepo;
import al.crystal.conferenceApp.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
//@Configuration
//@EnableJpaRepositories(basePackages = "al.crystal.conferenceApp")
public class ConferenceAppApplication implements CommandLineRunner {

    @Autowired
    private OrganizerService organizerService;
    @Autowired
    private SpeakerRateRepo speakerRateRepo;
    @Autowired
    private ParticipantService participantService;
    @Autowired
    private SpeakerService speakerService;
    @Autowired
    private FakerDataAccess fakerDataAccess;
    public static void main(String[] args) {
        SpringApplication.run(ConferenceAppApplication.class, args);
    }
    Logger logger= LoggerFactory.getLogger(this.getClass().getName());
    @Override
    public void run(String... args) throws Exception {
        Organiser organizer = organizerService.getOrganizer(1L);
        logger.info(organizer.toString());

        fakerDataAccess.createSessions(10,6,15,organizer);

        List<ParticipantDTO> participant = fakerDataAccess.createParticipant(50);
        participantService.participants(participant);
        List<Speaker> speakers = fakerDataAccess.speakerList(10);
        speakerService.saveListOfSpeaker(speakers);
        List<SpeakerSessionRateDTO> speakerSessionRateDTOS = fakerDataAccess.speakerRate(participant);
        List<SpeakerRate> collect = speakerSessionRateDTOS.stream()
                .map(speakerSessionRateDTO -> SpeakerSessionRateMapper.Instance.speakerRate(speakerSessionRateDTO))
                .collect(Collectors.toList());
        speakerRateRepo.saveAll(collect);
    }
}
