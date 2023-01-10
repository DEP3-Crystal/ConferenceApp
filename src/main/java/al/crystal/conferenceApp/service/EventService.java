package al.crystal.conferenceApp.service;

import al.crystal.conferenceApp.dto.EventDTO;
import al.crystal.conferenceApp.mapper.EventMap;
import al.crystal.conferenceApp.model.Event;
import al.crystal.conferenceApp.model.Organiser;
import al.crystal.conferenceApp.repository.EventRepository;
import al.crystal.conferenceApp.repository.OrganiserRepository;
import al.crystal.conferenceApp.service.job_ruunner.EventJobRunner;
import al.crystal.conferenceApp.service.job_ruunner.SessionJobRunner;
import al.crystal.conferenceApp.service.job_ruunner.SpeakerJobRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private SpeakerJobRunner speakerJobRunner;
    @Autowired
    private SessionJobRunner sessionJobRunner;
    @Autowired
    private EventJobRunner eventJobRunner;
    @Autowired
    private OrganiserRepository organiserRepository;

    public Event saveEvent(EventDTO event) {

//        if (event.getStartDay().toInstant().isAfter(event.getEndDay().toInstant())) {
//            throw new Exception("not done");
//        }
        Optional<Organiser> organiserFoundById = organiserRepository.findById(event.getOrganiserId());

        Event newEvent = Event.builder()
                .title(event.getTitle())
                .startDay(event.getStartDay())
                .endDay(event.getEndDay())
                .location(event.getLocation())
                .capacity(event.getCapacity())
                .organiser(organiserFoundById.get())
                .build();

        Event save = this.eventRepository.save(newEvent);
        sessionJobRunner.scheduleTaskWithDelay(save.getEndDay());
        speakerJobRunner.scheduleTaskWithDelay(save.getEndDay());
        return save;

    }

    public List<Event> getAllEvents() {
        return this.eventRepository.findAll();
    }

    public Event getEventById(Long id) {
        return this.eventRepository.findById(id).orElse(null);
    }

    public List<Event> deleteEvent(Long id) {
        this.eventRepository.deleteById(id);
        return this.eventRepository.findAll();
    }

    public Event updateEvent(Event event) {
        Event existingEvent = this.eventRepository.findById(event.getId()).get();

        return this.eventRepository.save(event);
    }

    public List<EventDTO> getAllEventsByOrganiserId(Long id) {
        Optional<Organiser> optionalOrganiser = this.organiserRepository.findById(id);
        Organiser organiser = optionalOrganiser.get();

        List<Event> eventByOrganiser = this.eventRepository.findByOrganiser(organiser);
        return eventByOrganiser.stream().map(EventMap::toDto).collect(Collectors.toList());
    }
}
