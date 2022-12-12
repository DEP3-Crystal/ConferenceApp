package al.crystal.conferenceApp.repository;

import al.crystal.conferenceApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

//@Repository
public interface UserRepository extends JpaRepository<User, Long> {
public User findByEmail(String email);
public User findByEmailAndPassword(String email, String password);
}
