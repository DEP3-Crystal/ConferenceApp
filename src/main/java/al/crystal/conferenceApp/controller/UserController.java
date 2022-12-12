package al.crystal.conferenceApp.controller;


import al.crystal.conferenceApp.dto.LoginModel;
import al.crystal.conferenceApp.dto.UserDto;
import al.crystal.conferenceApp.model.Organiser;
import al.crystal.conferenceApp.model.Participant;
import al.crystal.conferenceApp.model.User;
import al.crystal.conferenceApp.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UsersService userServices;

    @PostMapping("/login")
    public ResponseEntity<UserDto> loginUser(@RequestBody LoginModel loginModel) {
        UserDto userDto = userServices.loginUser(loginModel.getEmail(), loginModel.getPassword());
        if (userDto != null) {
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/users")
    public List<User> getAllUsers() {
        return userServices.getAll();
    }

    @GetMapping("/users/{id}")
    public User findUserById(@PathVariable Long id) {
        return userServices.getUserById(id);
    }

    @GetMapping("/users/participants")
    public List<Participant> getAllParticipants() {
        return this.userServices.getAllParticipants();
    }

    @GetMapping("/users/organisers")
    public List<Organiser> getAllOrganisers() {
        return this.userServices.getAllOrganisers();
    }

    @PostMapping(value = "/users/save")
    public List<User> addUser(@RequestBody User user) throws Exception {
        String existingEmail = user.getEmail();
        if (existingEmail != null && !"".equals(existingEmail)) {
            User existingUser = userServices.getUserByEmail(existingEmail);
            if (existingUser != null) {
                throw new Exception("User with " + existingEmail + " is already exist");
            }
        }
        userServices.saveUser(user);
        return this.userServices.getAll();
    }

    @DeleteMapping(value = "/users/delete/{id}")
    public List<User> deleteUser(@PathVariable Long id) {
        return this.userServices.deleteUser(id);
    }

    @PutMapping(value = "users/update")
    public List<User> updateUser(@RequestBody User user) {
        return this.userServices.updateUser(user);
    }

}
