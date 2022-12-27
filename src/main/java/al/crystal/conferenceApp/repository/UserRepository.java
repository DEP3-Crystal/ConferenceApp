package al.crystal.conferenceApp.repository;

import al.crystal.conferenceApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //    @Query(value = "Select * from user u where u.email =:email and u.password =:password and u.dtype in ('o','p')", nativeQuery = true)
    User findByEmailAndPassword(String email, String password);


    User findByEmail(String email);
}
