package al.crystal.conferenceApp.service;

import al.crystal.conferenceApp.dto.speaker.SpeakerDTO;
import al.crystal.conferenceApp.mapper.SpeakerMapper;
import al.crystal.conferenceApp.model.Speaker;
import al.crystal.conferenceApp.repository.SpeakerRateRepository;
import al.crystal.conferenceApp.repository.SpeakerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpeakerService {

    @Autowired
    private SpeakerRepository speakerRepository;
    @Autowired
    private SpeakerRateRepository speakerRateRepository;

    public String saveSpeaker(Speaker speaker) {
        speakerRepository.save(speaker);
        return "done";
    }
    public List<SpeakerDTO> saveListOfSpeaker(List<Speaker> speakers){
        List<Speaker> speakers1 = speakerRepository.saveAll(speakers);
        return speakers1.stream().map(speaker -> SpeakerMapper.Instance.speakerDto(speaker)).collect(Collectors.toList());
    }

    public List<Speaker> saveAll(List<Speaker> speakers) {
        return speakerRepository.saveAll(speakers);
    }
    public List<SpeakerDTO> getAllSpeakers(){
        List<Speaker> speakers = speakerRepository.findAll();
        return speakers.stream().map(speaker -> SpeakerMapper.Instance.speakerDto(speaker)).collect(Collectors.toList());
    }


}
