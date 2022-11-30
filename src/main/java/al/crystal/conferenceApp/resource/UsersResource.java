package al.crystal.conferenceApp.resource;

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
@RequestMapping(value = "/")
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
    public List<User> addUser(@RequestBody User user) {
        usersService.saveUser(user);
        return this.usersService.getAll();
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
