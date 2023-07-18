package com.example.todoapi.repository;

import com.example.todoapi.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Task findByIdAndUserId(long taskId, long userId);
    Task findById(long id);
    List<Task> findAllByUserId(long userId);

}
