//package al.crystal.conferenceApp.model;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.Column;
//import javax.persistence.DiscriminatorValue;
//import javax.persistence.Entity;
//import javax.persistence.OneToMany;
//import java.util.List;
//
//@Data
//@Entity(name = "participant")
//@AllArgsConstructor
//@NoArgsConstructor
//@DiscriminatorValue(value = "P") //Type.Values.PARTICIPANT
//public class Participant extends User {
//
//    private int participantNumber;
//
//    @OneToMany(mappedBy = "participant")
//    private List<SpeakerRate> speakerRatings;
//
//    @OneToMany(mappedBy = "participantSession")
//    private List<ParticipantSession> participantSessionList;
//}
