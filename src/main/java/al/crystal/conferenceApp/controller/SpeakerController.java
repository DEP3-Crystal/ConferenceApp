package al.crystal.conferenceApp.controller;

import al.crystal.conferenceApp.model.Speaker;
import al.crystal.conferenceApp.service.SpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/speaker")
public class SpeakerController {

    @Autowired
    private SpeakerService speakerService;

    @PostMapping("/add")
    public String createSpeaker(@RequestBody Speaker speaker) {
        return speakerService.saveSpeaker(speaker);
    }

}
