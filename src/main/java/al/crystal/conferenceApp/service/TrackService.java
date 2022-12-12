package al.crystal.conferenceApp.service;

import al.crystal.conferenceApp.dto.TrackDTO;
import al.crystal.conferenceApp.model.Track;
import al.crystal.conferenceApp.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrackService {

    @Autowired
    private TrackRepository trackRepository;

    public String createTrack(TrackDTO trackDTO) {
        Track newTrack = Track.builder()
                .trackName(trackDTO.getTrackName())
                .event(trackDTO.getEvent())
                .roomLocation(trackDTO.getRoomLocation())
                .roomType(trackDTO.getRoomType()).build();
        trackRepository.save(newTrack);
        return "done";
    }

    public Track getTrack(Long id) {
        return trackRepository.getReferenceById(id);
    }
}
