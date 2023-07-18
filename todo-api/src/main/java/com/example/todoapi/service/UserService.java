package com.example.todoapi.service;


import com.example.todoapi.entity.User;

import java.util.List;

public interface UserService {
    User getUserWithTasksById(long id);
    List<User> getAllUsers();
    User loginUser(String loginUser, String passwordUser);
    User registrationUser(User user);

}
