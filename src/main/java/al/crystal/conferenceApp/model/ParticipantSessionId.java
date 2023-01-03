package al.crystal.conferenceApp.model;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ParticipantSessionId implements Serializable {

    private long participantId;
    private long sessionId;
}
