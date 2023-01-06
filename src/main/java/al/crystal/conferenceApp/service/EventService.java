package al.crystal.conferenceApp.service;

import al.crystal.conferenceApp.dto.EventDTO;
import al.crystal.conferenceApp.mapper.EventMap;
import al.crystal.conferenceApp.model.Event;
import al.crystal.conferenceApp.model.Organiser;
import al.crystal.conferenceApp.repository.EventRepository;
import al.crystal.conferenceApp.repository.OrganiserRepository;
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
    EventRepository eventRepository;
    public String saveEvent(EventDTO event) throws Exception {

        LocalDate today =  LocalDate.now();
        if(today.isAfter(event.getStartDay())){
            throw new Exception("The day to start is a past Day!");
        }else if(event.getStartDay().isAfter(event.getEndDay())){
            throw new Exception("The day to start is a after the day to end!");}

        if(!(eventRepository.findEvents(event.getStartDay(),event.getEndDay()).isEmpty())){
            throw new Exception
                    ("Between this Start Date and End Date there is another event!");
        }
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
                .eventImage(event.getEventImage())
                .description(event.getDescription())
                .organiser(organiserFoundById.get())
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

    public List<EventDTO> getAllEventsByOrganiserId(Long id) {
        Optional<Organiser> optionalOrganiser = this.organiserRepository.findById(id);
        Organiser organiser = optionalOrganiser.get();

        List<Event> eventByOrganiser = this.eventRepository.findByOrganiser(organiser);
        return eventByOrganiser.stream().map(EventMap::toDto).collect(Collectors.toList());
    }
}
