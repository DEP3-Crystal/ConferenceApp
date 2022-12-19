package al.crystal.conferenceApp.service;

import al.crystal.conferenceApp.dto.EventDTO;
import al.crystal.conferenceApp.model.Event;
import al.crystal.conferenceApp.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventService {
    @Autowired
    EventRepository eventRepository;
    public String saveEvent(Event event) throws Exception {

//        if (event.getStartDay().toInstant().isAfter(event.getEndDay().toInstant())) {
//            throw new Exception("not done");
//        }
        LocalDate today =  LocalDate.now();
        if(today.isAfter(event.getStartDay())){
            throw new Exception("The day to start is a past Day!");
        }else if(event.getStartDay().isAfter(event.getEndDay())){ throw new Exception("The day to start is a after the day to end!");}
        Event newEvent = Event.builder()
                .title(event.getTitle())
                .startDay(event.getStartDay())
                .endDay(event.getEndDay())
                .location(event.getLocation())
                .capacity(event.getCapacity())
                .organiser(event.getOrganiser())
                .eventImage(event.getEventImage())
                .description(event.getDescription())
                .build();
        this.eventRepository.save(newEvent);
        return "Saved";
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
}
