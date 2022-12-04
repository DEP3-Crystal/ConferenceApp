package al.crystal.conferenceApp.services;

import al.crystal.conferenceApp.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class TrackService {

    @Autowired
    TrackRepository trackRepository;
}
