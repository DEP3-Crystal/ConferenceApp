package al.crystal.conferenceApp.model.pivote;

import al.crystal.conferenceApp.model.Session;
import al.crystal.conferenceApp.model.Speaker;
import al.crystal.conferenceApp.model.id.SessionSpeakerId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "session_speaker")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionSpeaker {
    @EmbeddedId
    private SessionSpeakerId id;

    @JsonIgnore
    @ManyToOne
    @MapsId("sessionId")
    @JoinColumn(name = "session_id", insertable = false, updatable = false)
    private Session session;

    @ManyToOne
    @MapsId("speakerId")
    @JoinColumn(name = "speaker_id", insertable = false, updatable = false)
    private Speaker speaker;

    public SessionSpeaker(Session session, Speaker speaker) {
        this.session = session;
        this.speaker = speaker;
        id=new SessionSpeakerId();
        id.setSessionId(session.getId());
        id.setSpeakerId(speaker.getId());
    }
}
