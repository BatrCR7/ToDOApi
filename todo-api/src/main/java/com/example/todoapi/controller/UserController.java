package com.example.todoapi.controller;


import com.example.todoapi.entity.User;
import com.example.todoapi.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserServiceImpl userServiceImpl;


    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserWithTasks(@PathVariable long id) {
        User user = userServiceImpl.getUserWithTasksById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
//    @GetMapping("/{userId}")
//    public User getUserById(@PathVariable long userId){
//        return userServiceImpl.getUserById(userId);
//    }

    @GetMapping
    public List<User> getAllUsers(){
        return userServiceImpl.getAllUsers();
    }

    @GetMapping("/login")
    public ResponseEntity<User> loginUser(@RequestParam("login") String loginUser, @RequestParam("password") String passwordUser){
        var user = userServiceImpl.loginUser(loginUser, passwordUser);
        if (user != null || user.getLogin() != "")
            return ResponseEntity.ok(user);
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<User> registrationUser(@RequestBody User user){
        return ResponseEntity.ok(userServiceImpl.registrationUser(user));
    }
}