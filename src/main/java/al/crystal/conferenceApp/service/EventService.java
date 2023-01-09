package al.crystal.conferenceApp.service;

import al.crystal.conferenceApp.dto.EventDTO;
import al.crystal.conferenceApp.mapper.EventMap;
import al.crystal.conferenceApp.model.Event;
import al.crystal.conferenceApp.model.Organiser;
import al.crystal.conferenceApp.repository.EventRepository;
import al.crystal.conferenceApp.repository.OrganiserRepository;
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

        return this.eventRepository.save(newEvent);
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
