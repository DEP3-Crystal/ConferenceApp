package al.crystal.conferenceApp;

import al.crystal.conferenceApp.service.UsersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ConferenceAppApplicationTests {
    @Autowired
    UsersService user_service;

    @Test
    void getUsers() {
		assertTrue(true);
    }
}
