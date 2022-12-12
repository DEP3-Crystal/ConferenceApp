package al.crystal.conferenceApp.service;

import al.crystal.conferenceApp.dto.OrganizerDTO;
import al.crystal.conferenceApp.model.Organiser;
import al.crystal.conferenceApp.repository.OrganiserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizerService {

    @Autowired
    private OrganiserRepository repository;


    public String addOrganizer(OrganizerDTO organizer) {
        Organiser organiser = new Organiser(organizer.getFirstName()
                , organizer.getLastName(), organizer.getEmail(),
                organizer.getPassword(), organizer.getCompanyName(), organizer.getBiography()
                , organizer.getLinkedinUrl(), organizer.getTweeterUrl(), organizer.getFacebookUrl(), organizer.getInstagramUrl());

        repository.save(organiser);
        return "Saved" ;
    }


    public Organiser getOrganizer(Long id) {
        return repository.findById(id).get();
    }
}
