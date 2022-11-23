package al.crystal.conferenceApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity(name = "session")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Session {
    @Id
    private UUID id;
    private String title;
    private String description;
    private String ype;
    private int capacity;
    private Date startTime;
    private Date endTime;

    @ManyToOne
    @JoinColumn(name = "track_id", nullable = false)
    private Track track;

    @ManyToMany
    @JoinTable(name="session_speaker",
            joinColumns=@JoinColumn(name="session_id"),
            inverseJoinColumns=@JoinColumn(name="speaker_id")
    )
    Set<Speaker> speakers;

    @OneToMany(mappedBy = "session")
    private List<ParticipantSession> sessionRatings;
}
