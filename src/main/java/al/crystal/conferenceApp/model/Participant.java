package al.crystal.conferenceapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity(name = "participant")
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue(value = "P") //Type.Values.PARTICIPANT
public class Participant extends User {
//    @JsonProperty("participantNumber")
    private int participantNumber;

    @OneToMany(mappedBy = "participant")
//    @JsonProperty("speakerRatings")
    private List<SpeakerRate> speakerRatings;

    @OneToMany(mappedBy = "participantSession")
//    @JsonProperty("participantSessionList")
    private List<ParticipantSession> participantSessionList;
    {
        this.participantNumber = participantNumber;
        this.speakerRatings = speakerRatings;
        this.participantSessionList = participantSessionList;
    }
}
