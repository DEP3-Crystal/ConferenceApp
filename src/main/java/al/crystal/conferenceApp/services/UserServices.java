package al.crystal.conferenceApp.services;

import al.crystal.conferenceApp.dto.UserDto;
import al.crystal.conferenceApp.mapper.UserMapper;
import al.crystal.conferenceApp.model.User;
import al.crystal.conferenceApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServices {
    @Autowired
    UserRepository userRepository;

    public boolean createUser(User newUser) {
        try {
            userRepository.saveAndFlush(newUser);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public User getUserById(UUID id) {
        return userRepository.findById(id).get();
    }

    public UserDto loginUser(String email, String password) {
        User byEmailAndPassword = userRepository.findByEmailAndPassword(email, password);
        if(byEmailAndPassword != null)
        return UserMapper.toDto(byEmailAndPassword);
        else
            return null;
    }
}
