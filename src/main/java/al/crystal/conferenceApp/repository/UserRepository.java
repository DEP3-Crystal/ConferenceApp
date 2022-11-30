package al.crystal.conferenceApp.repository;

import al.crystal.conferenceApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
//    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM User a WHERE a.id = :id")
//    Boolean ifExistByUuid(@Param("id") UUID id);
//    @Query("SELECT u FROM User u WHERE u.id= ?" )
//    User findUserById(UUID id);

//    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm", "root", "MySQLPass");
//    PreparedStatement preparedStmt = connection.prepareStatement( "INSERT INTO `atm`.`bank_account` (`IBAN`,`idPerson`, `email`, `firstname`,`lastname`,`cardNo`,`cardType`,`pin`,`balance`) VALUES (?,?,?,?,?,?,?,?,?)" );
//            preparedStmt.setInt(1, IBAN);
//            preparedStmt.setInt(2, person.getIdPerson());
}
