package al.crystal.conferenceApp.service;

import al.crystal.conferenceApp.dto.EventDTO;
import al.crystal.conferenceApp.mapper.EventMap;
import al.crystal.conferenceApp.model.Event;
import al.crystal.conferenceApp.model.Organiser;
import al.crystal.conferenceApp.repository.EventRepository;
import al.crystal.conferenceApp.repository.OrganiserRepository;
import al.crystal.conferenceApp.service.job_ruunner.SessionJobRunner;
import al.crystal.conferenceApp.service.job_ruunner.SpeakerJobRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService {
    @Autowired
    private OrganiserRepository organiserRepository;
    @Autowired
    private SessionJobRunner sessionJobRunner;
    @Autowired
    public SpeakerJobRunner speakerJobRunner;

    @Autowired
    EventRepository eventRepository;

    public Event saveEvent(Event event) throws Exception {

//        LocalDate today = LocalDate.now();
//        if (event.getStartDay().isAfter(event.getEndDay())) {
//            throw new Exception("The day to start is a after the day to end!");
//        }
//        if (!(eventRepository.findEventsDate(event.getStartDay(), event.getEndDay()).isEmpty())) {
//            throw new Exception
//                    ("Between this Start Date and End Date there is another event!");
//        }

        sessionJobRunner.scheduleTaskWithDelay(event.getEndDay());
        speakerJobRunner.scheduleTaskWithDelay(event.getEndDay());
        System.out.println(event);
        return this.eventRepository.save(event);
    }

    public List<Event> getAllEvents() {
        return this.eventRepository.findAll();
    }

    public List<Event> eventToShow() {
        LocalDate today = LocalDate.now();
        if (!(eventRepository.eventToShowNow(today)).isEmpty()) {
            return eventRepository.eventToShowNow(today);
        } else {
            return eventRepository.eventsToShowAfter(today);
        }
    }

    public Event getEventById(Long id) {
        return this.eventRepository.findById(id).orElse(null);
    }

    public List<Event> deleteEvent(Long id) {
        this.eventRepository.deleteById(id);
        return this.eventRepository.findAll();
    }

    public List<Event> updateEvent(Event event) {
        Event existingEvent = this.eventRepository.findById(event.getId()).orElse(null);
        if (existingEvent != null) {
            existingEvent.setTitle(event.getTitle());
            existingEvent.setStartDay(event.getStartDay());
            existingEvent.setEndDay(event.getEndDay());
            existingEvent.setLocation(event.getLocation());
            existingEvent.setCapacity(event.getCapacity());
            existingEvent.setEventImage(event.getEventImage());
            existingEvent.setDescription(event.getDescription());
            existingEvent.setOrganiser(event.getOrganiser());
            this.eventRepository.save(existingEvent);
        }
        return this.getAllEvents();
    }

    public List<EventDTO> getAllEventsByOrganiserId(Long id) {
        Optional<Organiser> optionalOrganiser = this.organiserRepository.findById(id);
        Organiser organiser = optionalOrganiser.get();

        List<Event> eventByOrganiser = this.eventRepository.findByOrganiser(organiser);
        return eventByOrganiser.stream().map(EventMap::toDto).collect(Collectors.toList());
    }
}
