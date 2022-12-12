package al.crystal.conferenceApp.dto;

import al.crystal.conferenceApp.model.Track;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SessionDTO {

    private String title;
    private String description;
    private String type;
    private int capacity;
    private Date startTime;
    private Date endTime;
    private Track track;

}
