package com.example.crmtaskmeneger.repository;

import com.example.crmtaskmeneger.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    Optional<Task> getTaskById (Long id);
    Optional<Task> getTaskByStatus (String status);
    List<Task> getAll();
}
