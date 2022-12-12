package al.crystal.conferenceApp.model;

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

    @ManyToOne
    @MapsId("sessionId")
    @JoinColumn(name = "session_id")
    private Session session;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User participantSession;
}
