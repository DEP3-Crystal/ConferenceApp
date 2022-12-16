package al.crystal.conferenceApp.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SpeakerRate {

    @EmbeddedId
    private SpeakerRateId id=new SpeakerRateId();

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
