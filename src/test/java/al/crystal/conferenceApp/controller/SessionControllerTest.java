package al.crystal.conferenceApp.controller;

import al.crystal.conferenceApp.repository.EventRepository;
import al.crystal.conferenceApp.repository.SessionRepository;
import al.crystal.conferenceApp.service.EventService;
import al.crystal.conferenceApp.service.OrganizerService;
import al.crystal.conferenceApp.service.SessionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
@WebMvcTest(SessionController.class)

class SessionControllerTest {
    @MockBean
    private SessionRepository  sessionRepository;
    @MockBean
    private SessionService sessionService;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    void addSession() {

    }

    @Test
    void getSession() {
    }

    @Test
    void testGetSession() {
    }

    @Test
    void getSessionByDate() {
    }
}