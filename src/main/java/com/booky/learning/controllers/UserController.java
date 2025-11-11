package com.booky.learning.controllers;

import com.booky.learning.dtos.UserDto;

import com.booky.learning.models.UserModel;
import com.booky.learning.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return userRepository.getUsers().stream()
                .map(user -> new UserDto(user.getId(), user.getUsername()))
                .toList();
    }

    @GetMapping("/user/{id}")
    public UserDto getUserById(@PathVariable String id) {
        return userRepository.getUsers().stream()
                .filter(user -> user.getId().equals(id))
                .map(user -> new UserDto(user.getId(), user.getUsername()))
                .findFirst()
                .orElse(null);
    }
    @PostMapping("/user")
    public String createUser(@RequestBody UserDto userDto) {
        userRepository.addUser(new UserModel(userDto.getId(), userDto.getUsername()));
        return "Created Successfully";
    }
    @PutMapping("/user/{id}")
    public String updateUser(@PathVariable String id, @RequestBody UserDto userDto) {
        UserModel userModel = new UserModel(userDto.getId(), userDto.getUsername());
        userRepository.editUser(userModel);
        return  "Updated Successfully";
    }
    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable String id) {
        UserModel userToDelete = userRepository.getUsers().stream()
                .filter(user -> user.getId().equals(id)).findFirst().orElse(null);
        if (userToDelete != null) {
            userRepository.removeUser(userToDelete);
            return "Deleted Successfully";
        }
        return "Deleted Failed";
    }
}
