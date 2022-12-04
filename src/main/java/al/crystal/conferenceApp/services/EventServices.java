package al.crystal.conferenceApp.services;

import al.crystal.conferenceApp.model.Event;
import al.crystal.conferenceApp.model.User;
import al.crystal.conferenceApp.repository.EventRepository;
import al.crystal.conferenceApp.repository.UserRepository;
import al.crystal.conferenceApp.validator.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class EventServices {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    UserRepository userRepository;


    public boolean createEvent(User organiser, Event newEvent) {
        EmailValidator emailValidator=new EmailValidator();

        boolean emailCanBeUsed = false;
        try {
            if (emailValidator.isValidEmailAddress(organiser.getEmail()) && emailValidator.isCompanyEmail(organiser.getEmail())) {
                emailCanBeUsed = true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        boolean allOrganiserMustFieldsCompiled = organiser.getBiography() != null && organiser.getCompanyName() != null;
        if (emailCanBeUsed && allOrganiserMustFieldsCompiled) {
            newEvent.setOrganiser(organiser);
            eventRepository.saveAndFlush(newEvent);
            return true;
        } else {
            return false;
        }

    }

    public boolean deleteEvent(UUID id) {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if (optionalEvent.isPresent()) {
            eventRepository.delete(optionalEvent.get());
            return true;
        } else {
            return false;
        }
    }

    public Event updateEvent(Event eventToBeUpdated) {
        if (eventRepository.findById(eventToBeUpdated.getId()).isPresent()) {
            return eventRepository.saveAndFlush(eventToBeUpdated);
        }
        return null;
    }


    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public List<Event> getEventByOrganiser(User organiser) {
        User user = userRepository.findById(organiser.getId()).orElseThrow();
        return eventRepository.findByOrganiser(organiser);
    }


    public Event getEventById(UUID id) {
        return eventRepository.findById(id).get();
    }





}
