package al.crystal.conferenceApp.repository;

import al.crystal.conferenceApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

User findByEmailAndPassword(String email, String password);
User findByEmail(String email);
}
