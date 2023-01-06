//package al.crystal.conferenceApp;
//
//import al.crystal.conferenceApp.faker.FakerDataAccess;
//import al.crystal.conferenceApp.repository.SpeakerRateRepo;
//import al.crystal.conferenceApp.service.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication
////@Configuration
////@EnableJpaRepositories(basePackages = "al.crystal.conferenceApp")
//public class ConferenceAppApplication implements CommandLineRunner {
//
//    @Autowired
//    private OrganizerService organizerService;
//    @Autowired
//    private SpeakerRateRepo speakerRateRepo;
//    @Autowired
//    private ParticipantService participantService;
//    @Autowired
//    private SpeakerService speakerService;
//    @Autowired
//    private FakerDataAccess fakerDataAccess;
//    public static void main(String[] args) {
//        SpringApplication.run(ConferenceAppApplication.class, args);
//    }
//    Logger logger= LoggerFactory.getLogger(this.getClass().getName());
//    @Override
//    public void run(String... args) throws Exception {
////        Organiser organizer = organizerService.getOrganizer(1L);
////        logger.info(organizer.toString());
////        fakerDataAccess.createSessions(10,6,15,organizer);
////
////        fakerDataAccess.createSpeakerRate(10,10);
//    }
//
//
//
//}


package al.crystal.conferenceApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Configuration
@EnableJpaRepositories(basePackages = "al.crystal.conferenceApp")
public class ConferenceAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConferenceAppApplication.class, args);
    }
}


