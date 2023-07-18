package com.example.todoapi.service;

import com.example.todoapi.entity.Task;


import java.util.List;
import java.util.Optional;

public interface TaskService {
    Task getTask(long taskId);
    Task getUserTaskById(long taskId, long userId);
    List<Task> getAllUserTasks(long userId);
    Task addNewTask(Task task,long userID);
    Task updateTask(Task task);

    boolean deleteTask(long taskId);

}
