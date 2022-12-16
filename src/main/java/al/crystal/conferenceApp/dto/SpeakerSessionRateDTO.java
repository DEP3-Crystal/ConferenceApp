package al.crystal.conferenceApp.dto;

import al.crystal.conferenceApp.model.Participant;
import al.crystal.conferenceApp.model.Speaker;
import al.crystal.conferenceApp.model.SpeakerRateId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpeakerSessionRateDTO {

    private Participant participant;
    private Speaker speaker;
    private int rating;
}
