package al.crystal.conferenceApp.controller;

import al.crystal.conferenceApp.dto.EventDTO;
import al.crystal.conferenceApp.model.Organiser;
import al.crystal.conferenceApp.repository.EventRepository;
import al.crystal.conferenceApp.service.EventService;
import al.crystal.conferenceApp.service.OrganizerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest
//@AutoConfigureMockMvc
@WebMvcTest(EventController.class)
class EventControllerTest {
    @MockBean
    private EventRepository eventRepository;
    @MockBean
    private EventService eventService;
    @MockBean
    private OrganizerController organizerController;
    @MockBean
    private OrganizerService organizerService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateEvent() throws Exception {
        Organiser organizer = organizerService.getOrganizer(1L);
        System.out.println(organizer);
        EventDTO eventDTO = new EventDTO(
                "Python conference",
                new Date(),
                new Date(),
                "Room nr1",
                50,
                organizer
        );

        mockMvc.perform(
                        post("/events/add/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(eventDTO)))
//                .andExpect(status().isOk())
                .andExpect(status().isCreated())
                .andDo(print());

        mockMvc.perform(
                        post("/events/add/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(null)))
//                .andExpect(status().isOk())
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    void getEvents() throws Exception {
        mockMvc.perform(get("/events/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andDo(print());
    }
}