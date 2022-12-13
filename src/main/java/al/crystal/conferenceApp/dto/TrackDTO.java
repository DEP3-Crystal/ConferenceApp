package al.crystal.conferenceApp.dto;


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

}
