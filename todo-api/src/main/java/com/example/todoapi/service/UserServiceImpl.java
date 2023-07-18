package com.example.todoapi.service;

import com.example.todoapi.entity.Task;
import com.example.todoapi.entity.User;
import com.example.todoapi.repository.TaskRepository;
import com.example.todoapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;


    public User getUserWithTasksById(long id) {
        return userRepository.findById(id);
    }
    public UserServiceImpl(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User loginUser(String loginUser, String passwordUser) {
        return userRepository.findByLoginAndPassword(loginUser, passwordUser);
    }

    @Override
    public User registrationUser(User user) {
        return userRepository.save(user);
    }
}
