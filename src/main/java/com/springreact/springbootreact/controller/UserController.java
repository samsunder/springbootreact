package com.springreact.springbootreact.controller;

import com.springreact.springbootreact.exceptions.ResourceNotFoundException;
import com.springreact.springbootreact.model.User;
import com.springreact.springbootreact.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class UserController {
    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/user/save")
    public User saveUser(@RequestBody User user) {
        return this.userRepository.save(user);
    }

    @GetMapping("/user/all")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(
                this.userRepository.findAll()
        );
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@   PathVariable(value = "id") Integer id) {
        log.info("!*!*!INTO GET MAPPING");
        User user = this.userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User not found")
        );

        return ResponseEntity.ok().body(user);
    }

    @PutMapping("user/{id}")
    public User updateUser(@RequestBody User newUser, @PathVariable(value = "id") Integer id) {
        return this.userRepository.findById(id)
                .map(user -> {
                    user.setName(newUser.getName());
                    user.setSurname(newUser.getSurname());
                    user.setEmail(newUser.getEmail());
                    user.setUsername(newUser.getUsername());
                    user.setPassword(newUser.getPassword());
                    return this.userRepository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return this.userRepository.save(newUser);
                });
    }

    @DeleteMapping("user/{id}")
    public ResponseEntity<Void> removeUser(@PathVariable(value = "id") Integer id) {
        User user = this.userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User not found" + id)
        );

        this.userRepository.delete(user);
        return ResponseEntity.ok().build();
    }


}
