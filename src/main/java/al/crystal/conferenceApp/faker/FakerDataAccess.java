package al.crystal.conferenceApp.faker;

import al.crystal.conferenceApp.dto.*;
import al.crystal.conferenceApp.dto.speaker.SpeakerDTO;
import al.crystal.conferenceApp.dto.speaker.SpeakerSessionRateDTO;
import al.crystal.conferenceApp.mapper.ParticipantMapper;
import al.crystal.conferenceApp.mapper.SpeakerMapper;
import al.crystal.conferenceApp.mapper.SpeakerSessionRateMapper;
import al.crystal.conferenceApp.model.*;
import al.crystal.conferenceApp.repository.SpeakerRateRepo;
import al.crystal.conferenceApp.service.*;
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class FakerDataAccess {

    @Autowired
    private EventService eventService;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private SpeakerService speakerService;
    @Autowired
    private TrackService trackService;

    @Autowired
    private ParticipantService participantService;

    @Autowired
    private SpeakerRateRepo speakerRateRepo;

    FakeValuesService fakeValuesService = new FakeValuesService(
            new Locale("en-GB"), new RandomService());
    Faker faker = Faker.instance();


    public List<SessionDTO> createSessions(int numberOfSessions, int numberOfTracks, int numberOfSpeakers, Organiser organiser) {
        EventDTO event1 = createEvent(organiser);
        System.out.println(event1);
        Event event = eventService.saveEvent(event1);
        List<Track> tracks = trackService.saveTracks(trackDTOList(numberOfTracks));
        List<SpeakerDTO> speakerDTOS = speakerService.saveListOfSpeaker(speakerList(numberOfSpeakers));
        return sessionList(numberOfSessions, event, tracks, speakerDTOS);
    }

    public List<SessionDTO> sessionList(int numberOfSession, Event event, List<Track> tracks, List<SpeakerDTO> speakers) {
        List<SessionDTO> sessionData = IntStream.range(0, numberOfSession).mapToObj(date -> SessionDTO.builder()
                .title("title")
                .capacity(faker.random().nextInt(10, 590))
                .startTime(getFutureDay(1))
                .endTime(getFutureDay(1))
                .event(event)
                .description(faker.lorem().characters(50, 80))
                .type("none")
                .build()).collect(Collectors.toList());
        sessionData.forEach(sessionDTO -> {
            sessionDTO.setTrack(random(tracks));
            sessionDTO.setSpeakersDTO(randomList(speakers, 0.6f));
        });
        sessionService.saveSessions(sessionData);
        return sessionData;
    }

    public EventDTO createEvent(Organiser organiser) {
        int capacity = faker.random().nextInt(500);
        return new EventDTO("title", getPastDay(5),
                getPastDay(2),
                faker.address().fullAddress(),
                capacity, organiser);
    }

    private LocalDate getPastDay(int day) {
        return LocalDate.ofInstant(Instant.ofEpochMilli(faker.date().past(day, TimeUnit.DAYS).getTime()), ZoneId.systemDefault());
    }


    public List<TrackDTO> trackDTOList(int numberOfTrack) {
        return IntStream.range(0, numberOfTrack)
                .mapToObj(data -> new TrackDTO(faker.funnyName().name(),
                        faker.address().streetName(), "none"))
                .collect(Collectors.toList());
    }


    private LocalDateTime getFutureDay(int day) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(faker.date().future(day, TimeUnit.DAYS).getTime()), ZoneId.systemDefault());
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

    public <T> T random(List<T> t) {
        return t.get(faker.random().nextInt(t.size()));
    }

    public <T> List<T> randomList(List<T> t, float percentage) {
        int totalPercentage = Math.round(percentage * t.size());
        return IntStream.range(0, totalPercentage).mapToObj(data -> random(t)).collect(Collectors.toList());
    }

    public List<ParticipantDTO> createParticipant(int numberOfParticipant) {
        return IntStream.range(0, numberOfParticipant)
                .mapToObj(i -> ParticipantDTO.builder()
                        .firstName(faker.name().firstName())
                        .lastName(faker.name().lastName())
                        .email(email())
                        .password(faker.funnyName().name())
                        .build()).collect(Collectors.toList());
    }

    public List<SpeakerSessionRateDTO> speakerRate(List<ParticipantDTO> participantDTOS) {
        return speakerService.getAllSpeakers().stream().flatMap(speakerDTO ->
                participantDTOS.stream().map(participantDTO ->
                        new SpeakerSessionRateDTO(
                                ParticipantMapper.Instance.participant(participantDTO),
                                SpeakerMapper.Instance.speaker(speakerDTO), faker.random().nextInt(1, 5))
                )
        ).collect(Collectors.toList());
    }


    public List<SpeakerRate> createSpeakerRate(int numberOfParticipant,int numberOfSpeakers){
        List<ParticipantDTO> participant = createParticipant(numberOfParticipant);
        participantService.participants(participant);
        List<Speaker> speakers = speakerList(numberOfSpeakers);
        speakerService.saveListOfSpeaker(speakers);
        List<SpeakerSessionRateDTO> speakerSessionRateDTOS = speakerRate(participant);
        List<SpeakerRate> speakerRateList = speakerSessionRateDTOS.stream()
                .map(SpeakerSessionRateMapper.Instance::speakerRate)
                .collect(Collectors.toList());
        return speakerRateRepo.saveAll(speakerRateList);

    }
    public String email() {
        return fakeValuesService.bothify("????##@gmail.com");
    }
}
