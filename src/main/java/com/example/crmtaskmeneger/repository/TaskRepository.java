package com.example.crmtaskmeneger.repository;

import com.example.crmtaskmeneger.entity.TaskEntity;
import com.example.crmtaskmeneger.entity.UserEntity;
import com.example.crmtaskmeneger.entity.enumeric.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    Optional<TaskEntity> findById(Long id);
    Optional<List<TaskEntity>> findByTaskThem(String them);
    Optional<List<TaskEntity>> findByDateCreate(LocalDateTime dateCreate);
    Optional<List<TaskEntity>> findByDateCompletion(LocalDate dateCompletion);
    Optional<List<TaskEntity>> findByTaskStartTime(LocalDate taskStartTime);
    Optional<List<TaskEntity>> findByStatus(TaskStatus taskStatus);
    Optional<List<TaskEntity>> findByAuthor(UserEntity author);
    Optional<List<TaskEntity>> findByExecutor(UserEntity executor);



}
