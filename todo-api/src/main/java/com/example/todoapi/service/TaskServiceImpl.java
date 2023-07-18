package com.example.todoapi.service;

import com.example.todoapi.entity.Task;
import com.example.todoapi.entity.User;
import com.example.todoapi.repository.TaskRepository;
import com.example.todoapi.repository.UserRepository;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;

import java.util.List;


@Service
public class TaskServiceImpl implements TaskService{
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }


    @Override
    public Task getTask(long taskId) {
        return taskRepository.findById(taskId);
    }

    @Override
    public Task getUserTaskById(long taskId, long userId) {
        return taskRepository.findByIdAndUserId(taskId, userId);
    }

    @Override
    public List<Task> getAllUserTasks(long userId) {
        return taskRepository.findAllByUserId(userId);
    }

    @Override
    public Task addNewTask(Task task, long userID) {
        User u = userRepository.findById(userID);
        if (u != null)
        {
            task.setUser(u);
            taskRepository.save(task);
            return task;
        }
       return null;
    }

    public Task updateTask(Task task) {
        Task existingTask = taskRepository.findById(task.getId());
        if (existingTask != null) {
            existingTask.setTitle(task.getTitle());
            existingTask.setText(task.getText());
            return taskRepository.save(existingTask);
        }
        return null;
    }


    @Transactional
    public boolean deleteTask(long taskId) {
        Task existingTask = taskRepository.findById(taskId);
        if (existingTask != null) {
            taskRepository.delete(existingTask);
            return true;
        }
        return false;
    }

}
