package al.crystal.conferenceApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@Configuration
//@EnableJpaRepositories(basePackages = "al.crystal.conferenceApp")
public class ConferenceAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConferenceAppApplication.class, args);
    }
}
