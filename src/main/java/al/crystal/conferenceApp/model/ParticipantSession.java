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
    private ParticipantSessionId id=new ParticipantSessionId();
    private int rating;

    @ManyToOne
    @MapsId("sessionId")
    @JoinColumn(name = "session_id")
    private Session session;

    @ManyToOne
    @MapsId("participantId")
    @JoinColumn(name = "user_id")
    private Participant participant;

    public ParticipantSession(int rating, Session session, Participant participant) {
        this.rating = rating;
        this.session = session;
        this.participant = participant;
    }
}
