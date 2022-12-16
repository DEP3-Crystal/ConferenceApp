package al.crystal.conferenceApp.mapper;

import al.crystal.conferenceApp.dto.SpeakerSessionRateDTO;
import al.crystal.conferenceApp.model.SpeakerRate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SpeakerSessionRateMapper {

    SpeakerSessionRateMapper Instance= Mappers.getMapper(SpeakerSessionRateMapper.class);

    SpeakerRate speakerRate(SpeakerSessionRateDTO speakerSessionRateDTO);
}
