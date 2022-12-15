package al.crystal.conferenceApp.model.id;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class SessionSpeakerId implements Serializable{
    private long sessionId;
    private long speakerId;
}
