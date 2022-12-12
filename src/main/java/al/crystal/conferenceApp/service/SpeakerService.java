package al.crystal.conferenceApp.service;

import al.crystal.conferenceApp.controller.SpeakerController;
import al.crystal.conferenceApp.model.Speaker;
import al.crystal.conferenceApp.repository.SpeakerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpeakerService {

    @Autowired
    private SpeakerRepository speakerRepository;

    public String saveSpeaker(Speaker speaker){
        speakerRepository.save(speaker);
        return "done";
    }
}
