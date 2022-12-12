package al.crystal.conferenceApp.resource;

import al.crystal.conferenceApp.LoginUser;
import al.crystal.conferenceApp.model.Organiser;
import al.crystal.conferenceApp.model.Participant;
import al.crystal.conferenceApp.model.User;
import al.crystal.conferenceApp.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "")
public class UsersResource {

    @Autowired
    private UsersService usersService;

    @GetMapping(value="/users")
    public List<User> getAllUsers(){
        return usersService.getAll();
    }

    @GetMapping("/users/{id}")
    public User findUserById(@PathVariable UUID id){
        return usersService.getUserById(id);
    }

//    @GetMapping("/users/{email}")
//    public User findUserByEmail(@PathVariable String email){
//        return usersService.getUserByEmail(email);
//    }
    @GetMapping("/users/participants")
    public List<Participant> getAllParticipants(){
        return this.usersService.getAllParticipants();
    }

    @GetMapping("/users/organisers")
    public List<Organiser> getAllOrganisers(){
        return this.usersService.getAllOrganisers();
    }

    @PostMapping(value = "/users/save",produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public List<User> addUser(@RequestBody User user) throws Exception {
        String existingEmail = user.getEmail();
        if (existingEmail != null && !"".equals(existingEmail)) {
            User existingUser = usersService.getUserByEmail(existingEmail);
            if (existingUser != null) {
                throw new Exception("User with " + existingEmail + " is already exist");
            }
        }
            usersService.saveUser(user);
            return this.usersService.getAll();
    }

    @PostMapping(value="/login")
    @CrossOrigin(origins = "http://localhost:4200")
    public User loginUser(@RequestBody LoginUser loginUser){
        User ifExist = null;
        if(loginUser.getEmail() !=null && loginUser.getEmail() !=null){
            ifExist = this.usersService.getUserByEmailAndPassword(loginUser.getEmail(),loginUser.getPassword());
        }
    return ifExist;
    }

    @DeleteMapping(value = "/users/delete/{id}")
    public List<User> deleteUser(@PathVariable UUID id){
         return this.usersService.deleteUser(id);
    }

    @PutMapping(value = "users/update")
    public List<User> updateUser(@RequestBody User user){
        return this.usersService.updateUser(user);
    }

}
