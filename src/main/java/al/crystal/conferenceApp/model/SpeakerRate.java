package al.crystal.conferenceApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "speaker-rates")
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
