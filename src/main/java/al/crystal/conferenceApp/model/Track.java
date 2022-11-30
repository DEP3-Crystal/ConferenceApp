package al.crystal.conferenceApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tracks")
public class Track {
    @Id
    private UUID id;
    private int roomNum;
    private String roomType;
    private Date startTime;
    private Date endTime;
    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @OneToMany(mappedBy = "track")
    private List<Session> sessions;

}
