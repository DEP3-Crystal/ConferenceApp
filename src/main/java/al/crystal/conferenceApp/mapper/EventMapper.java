package al.crystal.conferenceApp.mapper;

import al.crystal.conferenceApp.dto.EventDTO;
import al.crystal.conferenceApp.dto.speaker.SpeakerDTO;
import al.crystal.conferenceApp.model.Event;
import al.crystal.conferenceApp.model.Speaker;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EventMapper {

        al.crystal.conferenceApp.mapper.EventMapper Instance= Mappers.getMapper(al.crystal.conferenceApp.mapper.EventMapper.class);


        EventDTO eventDto(Event event);

        Event event(EventDTO eventDTO);
}

