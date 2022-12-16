package al.crystal.conferenceApp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity(name = "sessions")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Session {
    @Id
    private long id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String type;
    private int capacity;
    private Date startTime;
    private Date endTime;

    @ManyToOne
    @JoinColumn(name = "track_id", nullable = true)
    private Track track;

    @ManyToOne
    private Event event;

    @ManyToMany
    @JoinTable(name = "session_speakers",
            joinColumns = @JoinColumn(name = "session_id"),
            inverseJoinColumns = @JoinColumn(name = "speaker_id")
    )
    Set<Speaker> speakers;

    @OneToMany(mappedBy = "session")
    private List<ParticipantSession> sessionRatings;
}
