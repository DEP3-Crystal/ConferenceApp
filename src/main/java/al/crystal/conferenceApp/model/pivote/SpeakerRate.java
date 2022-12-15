package al.crystal.conferenceApp.model.pivote;

import al.crystal.conferenceApp.model.Participant;
import al.crystal.conferenceApp.model.Speaker;
import al.crystal.conferenceApp.model.id.SpeakerRateId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SpeakerRate {

    @EmbeddedId
    private SpeakerRateId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private Participant participant;

    @ManyToOne
    @MapsId("speakerId")
    @JoinColumn(name = "speaker_id")
    private Speaker speaker;

    private int rating;
}
