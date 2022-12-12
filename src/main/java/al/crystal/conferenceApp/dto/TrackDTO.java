package al.crystal.conferenceApp.dto;

import al.crystal.conferenceApp.model.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TrackDTO {

    private String trackName;
    private String roomLocation;
    private String roomType;
    private Event event;
}
