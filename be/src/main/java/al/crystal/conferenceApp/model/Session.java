package al.crystal.conferenceApp.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity(name = "speaker")
@Data
public class Session {
    @Id
    private UUID id;
    private String title;
    private String description;
    private String type;
    private int capacity;
    private Date startTime;
    private Date endTime;

    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private Track track;

    @ManyToMany
    Set<Speaker> speakers;
}
