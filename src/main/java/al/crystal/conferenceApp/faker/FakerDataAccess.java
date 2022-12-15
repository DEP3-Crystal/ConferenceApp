package al.crystal.conferenceApp.faker;

import al.crystal.conferenceApp.dto.EventDTO;
import al.crystal.conferenceApp.dto.SessionDTO;
import al.crystal.conferenceApp.dto.TrackDTO;
import al.crystal.conferenceApp.model.Event;
import al.crystal.conferenceApp.model.Organiser;
import al.crystal.conferenceApp.model.Speaker;
import al.crystal.conferenceApp.model.Track;
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FakerDataAccess {

    private static FakerDataAccess instance;
    FakeValuesService fakeValuesService = new FakeValuesService(
            new Locale("en-GB"), new RandomService());
    Faker faker = Faker.instance();

    private FakerDataAccess() {
    }

    public static synchronized FakerDataAccess getInstance() {
        if (instance == null) {
            instance = new FakerDataAccess();
        }
        return instance;
    }

    public EventDTO createEvent(int numOfEvent, Organiser organiser) {
//        List<EventDTO> eventList = IntStream.range(0, numOfEvent).mapToObj(num -> new EventDTO("title", faker.date().past(5, TimeUnit.DAYS),
//                faker.date().future(2, TimeUnit.DAYS),
//                faker.address().fullAddress(), faker.random().nextInt(500), organiser))
//                .collect(Collectors.toList());
        EventDTO eventDTO = new EventDTO("title", LocalDate.ofInstant(Instant.ofEpochMilli(faker.date().past(5, TimeUnit.DAYS).getTime()), ZoneId.systemDefault()),
                LocalDate.ofInstant(Instant.ofEpochMilli(faker.date().future(2, TimeUnit.DAYS).getTime()), ZoneId.systemDefault()),
                faker.address().fullAddress(), faker.random().nextInt(500), organiser);
        return eventDTO;
    }

//    public List<Session> sessionsList(int numOfSession,Event event){
//        IntStream.range(0,numOfSession).mapToObj(data->new Session())
//    }

    public List<TrackDTO> trackDTOList(int numberOfTrack) {
        return IntStream.range(0, numberOfTrack)
                .mapToObj(data -> new TrackDTO(faker.funnyName().name(),
                        faker.address().streetName(), "none"))
                .collect(Collectors.toList());
    }

    public List<SessionDTO> sessionList(int numberOfSession, Event event, List<Track> tracks, List<Speaker> speakers) {
        List<SessionDTO> sessionData = IntStream.range(0, numberOfSession).mapToObj(date -> SessionDTO.builder()
                .title("title")
                .capacity(faker.random().nextInt(10, 590))
                .startTime(LocalDateTime.ofInstant(Instant.ofEpochMilli(faker.date().future(1, TimeUnit.DAYS).getTime()), ZoneId.systemDefault()))
                .endTime(LocalDateTime.ofInstant(Instant.ofEpochMilli(faker.date().future(1, TimeUnit.DAYS).getTime()), ZoneId.systemDefault()))
                .event(event)
                .description(faker.lorem().characters(50, 80))
                .type("none")
                .build()).collect(Collectors.toList());

        sessionData.stream().forEach(sessionDTO -> {
            sessionDTO.setTrack(tracks.get(faker.random().nextInt(tracks.size())));
            Set<Speaker> speakerList = new HashSet<>(speakers.subList(faker.random().nextInt(1),
                    faker.random().nextInt(speakers.size())));
            sessionDTO.setSpeakers(speakerList);
        });

        return sessionData;
    }

    public List<Speaker> speakerList(int numberOfSpeakers) {
        return IntStream.range(0, numberOfSpeakers).mapToObj(data -> Speaker.builder()
                .name(faker.name().firstName())
                .lastName(faker.name().lastName())
                .biography(faker.lorem().characters(20, 50))
                .companyName(faker.company().name())
                .title("none")
                .build()).collect(Collectors.toList());
    }


}
