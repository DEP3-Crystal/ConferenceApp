package al.crystal.conferenceApp.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ParticipantSessionId implements Serializable {


//    @Column(name = "user_id")
    private long userId;

//    @Column(name = "session_id")
    private long sessionId;
}
