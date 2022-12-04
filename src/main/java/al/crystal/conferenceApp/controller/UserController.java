package al.crystal.conferenceApp.controller;

import al.crystal.conferenceApp.dto.LoginModel;
import al.crystal.conferenceApp.dto.UserDto;
import al.crystal.conferenceApp.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserServices userServices;

    @PostMapping("/login")
    public ResponseEntity<UserDto> loginUser(@RequestBody LoginModel loginModel) {
        UserDto userDto = userServices.loginUser(loginModel.getEmail(), loginModel.getPassword());
        if (userDto != null) {
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
