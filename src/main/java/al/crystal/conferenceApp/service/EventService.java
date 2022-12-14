package al.crystal.conferenceApp.service;

import al.crystal.conferenceApp.model.*;
import al.crystal.conferenceApp.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.OneToMany;
import java.util.List;
import java.util.UUID;
@Service
public class EventService {
    @Autowired
    EventRepository eventRepository;



    @OneToMany(mappedBy = "event")
    private List<Track> tracks;

    public String saveEvent(Event event) {

        Event newEvent = new Event();
        newEvent.setTitle(event.getTitle());
        newEvent.setStartDay(event.getStartDay());
        newEvent.setEndDay(event.getEndDay());
        newEvent.setLocation(event.getLocation());
        newEvent.setCapacity(event.getCapacity());
        newEvent.setOrganiser(event.getOrganiser());
        newEvent.setTracks(event.getTracks());
            this.eventRepository.save(newEvent);
        return "Saved";
    }

    public List<Event> getAllEvents() {
        return this.eventRepository.findAll();
    }

    public Event getEventById(UUID id) {
        return this.eventRepository.findById(id).orElse(null);
    }

    public List<Event> deleteEvent(UUID id){
        this.eventRepository.deleteById(id);
        return this.eventRepository.findAll();
    }

    public List<Event> updateEvent(Event event){
            Event existingEvent = this.eventRepository.findById(event.getId()).orElse(null);
            if(existingEvent != null) {
                existingEvent.setTitle(event.getTitle());
                existingEvent.setStartDay(event.getStartDay());
                existingEvent.setEndDay(event.getEndDay());
                existingEvent.setLocation(event.getLocation());
                existingEvent.setCapacity(event.getCapacity());
                existingEvent.setOrganiser(event.getOrganiser());
                existingEvent.setTracks(event.getTracks());
                this.eventRepository.save(existingEvent);
            }
        return this.getAllEvents();
    }
}
