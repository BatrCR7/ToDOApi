package com.example.todoapi.controller;

import com.example.todoapi.entity.Task;
import com.example.todoapi.service.TaskServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskServiceImpl taskServiceImpl;

    public TaskController(TaskServiceImpl taskServiceImpl) {
        this.taskServiceImpl = taskServiceImpl;
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable long taskId){
        return ResponseEntity.ok(taskServiceImpl.getTask(taskId));
    }
    @GetMapping("/{userId}/{taskId}")
    public ResponseEntity<Task> getUserTaskById(@PathVariable long userId, @PathVariable long taskId) {
        Task task = taskServiceImpl.getUserTaskById(taskId, userId);
        if (task != null) {
            return ResponseEntity.ok(task);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Task>> getAllUserTasks(@PathVariable long userId) {
        List<Task> tasks = taskServiceImpl.getAllUserTasks(userId);
        return ResponseEntity.ok(tasks);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Task> addNewTask(@RequestBody Task task, @PathVariable long userId) {
        var response = taskServiceImpl.addNewTask(task, userId);
        if (response != null)
            return ResponseEntity.ok(response);
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@RequestBody Task task, @PathVariable long id) {
        task.setId(id);

        var response = taskServiceImpl.updateTask(task);
        if (response != null)
            return ResponseEntity.ok(response);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<Void> deleteTask(@PathVariable long Id){
        var response = taskServiceImpl.deleteTask(Id);
        if(response)
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }
}

