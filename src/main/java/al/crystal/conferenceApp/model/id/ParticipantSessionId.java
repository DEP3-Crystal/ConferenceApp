package al.crystal.conferenceApp.model.id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ParticipantSessionId implements Serializable {

    private long userId;
    private long sessionId;
}
