package com.booky.learning.controllers;

import com.booky.learning.dtos.UserDto;
import com.booky.learning.models.UserModel;
import com.booky.learning.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/user")
    public List<UserDto> getUsers() {
        return userRepository.findAll().stream()
                .map(user -> new UserDto(user.getId(), user.getUsername(), user.getEmail()))
                .toList();
    }

    @GetMapping("/user/{id}")
    public UserDto getUserById(@PathVariable String id) {
        return userRepository.findById(id)
                .map(user -> new UserDto(user.getUsername(), user.getEmail()))
                .orElse(null);
    }

    // search by email by send email to body to POST /user/search
    @PostMapping("/user/search")
    public UserDto getUserByEmail(@Valid @RequestBody UserDto userDto) {
        UserDto foundUser = userRepository.findByEmail(userDto.getEmail())
                .map(user -> new UserDto(user.getUsername(), user.getEmail()))
                .orElse(null);
        if (foundUser == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        return foundUser;
    }

    @PostMapping("/user")
    public String createUser(@RequestBody UserDto userDto) {
        userRepository.save(new UserModel(userDto.getUsername(), userDto.getEmail()));
        return "Created Successfully";
    }

    @PutMapping("/user/{id}")
    public String updateUser(@PathVariable String id, @RequestBody UserDto userDto) {
        UserModel userModel = new UserModel(userDto.getUsername(), userDto.getEmail());
        userRepository.save(userModel);
        return "Updated Successfully";
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable String id) {
        UserModel userToDelete = userRepository.findById(id).orElse(null);
        if (userToDelete != null) {
            userRepository.delete(userToDelete);
            return "Deleted Successfully";
        }
        return "Deleted Failed";
    }
}
