package al.crystal.conferenceApp.controller;

import al.crystal.conferenceApp.dto.EventDTO;
import al.crystal.conferenceApp.model.Organiser;
import al.crystal.conferenceApp.repository.EventRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
//@WebMvcTest(EventController.class)
class EventControllerTest {
    @MockBean
    private EventRepository eventRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateEvent() throws Exception {
        EventDTO eventDTO = new EventDTO(
                "Python conference",
                new Date(),
                new Date(),
                "Room nr1",
                50,
                new Organiser("jon",
                        "doe",
                        "jon@email.eu",
                        "sd sd",
                        "sas",
                        "bio",
                        "sas",
                        "asa",
                        "fb",
                        "instut")
        );

        mockMvc.perform(
                post("/events/add/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(eventDTO)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void getEventById() throws Exception {
        mockMvc.perform(get("/events/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andDo(print());
    }
}