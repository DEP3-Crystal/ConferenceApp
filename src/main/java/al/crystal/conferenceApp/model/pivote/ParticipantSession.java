package al.crystal.conferenceApp.model.pivote;

import al.crystal.conferenceApp.model.id.ParticipantSessionId;
import al.crystal.conferenceApp.model.Session;
import al.crystal.conferenceApp.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ParticipantSession {

    @EmbeddedId
    private ParticipantSessionId id;
    private String zone;
    private int chairNumber;
    private double price;
    private int rating;

    @JsonIgnore
    @ManyToOne
    @MapsId("sessionId")
    @JoinColumn(name = "session_id")
    private Session session;

    @JsonIgnore
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User participantSession;
}
