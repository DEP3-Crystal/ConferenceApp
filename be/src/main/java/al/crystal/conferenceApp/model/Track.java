package al.crystal.conferenceApp.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity(name = "track")
public class Track {
    @Id
    private UUID id;
    private int roomNr;
    private String roomType;
    private Date startTime;
    private Date endTime;
    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private Event event;

    @OneToMany(mappedBy = "track")
    private List<Session> sessions;

}
