package al.crystal.conferenceApp.model;

import al.crystal.conferenceApp.model.pivote.ParticipantSession;
import al.crystal.conferenceApp.model.pivote.SessionSpeaker;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity(name = "session")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String type;
    private int capacity;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "track_id", nullable = false)
    private Track track;

    @ManyToOne
    private Event event;
    //    @JsonIgnore
//    @JsonBackReference

//    @OneToMany(mappedBy = "session")
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Speaker> sessionSpeakers;

    @OneToMany(mappedBy = "session")
    private List<ParticipantSession> sessionRatings;
}
