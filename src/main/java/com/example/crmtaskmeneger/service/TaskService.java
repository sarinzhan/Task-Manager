package com.example.crmtaskmeneger.service;

import com.example.crmtaskmeneger.entity.TaskEntity;
import com.example.crmtaskmeneger.entity.UserEntity;
import com.example.crmtaskmeneger.entity.enumeric.TaskStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TaskService {

    TaskEntity saveNewTask(TaskEntity task);

    List<TaskEntity> getAll();

    TaskEntity getTaskById(Long id) throws Exception;

    List<TaskEntity> getTaskByTaskThem(String them) throws Exception;
    List<TaskEntity> getTaskListByDateCompletion(LocalDate dateCompletion) throws Exception;
    List<TaskEntity> getTaskListByStartedTime(LocalDate dateStartExecutedTask) throws Exception;
    List<TaskEntity> getTaskListByDataCreate(LocalDateTime dateCreate) throws Exception;
    List<TaskEntity> getTaskListByStatus(TaskStatus taskStatus) throws Exception;
    List<TaskEntity> getTaskListByAuthor(UserEntity entity) throws Exception;
    List<TaskEntity> getTaskListByExecutedUser(UserEntity executor) throws Exception;
    TaskEntity updateTask(TaskEntity taskEntity, UserEntity executor) throws Exception;
    TaskEntity updateTask(TaskEntity taskEntity, TaskStatus status) throws Exception;
    TaskEntity updateTask(TaskEntity taskEntity, LocalDate startTaskExecuted) throws Exception;

    void deleteTask(Long id) throws Exception;

    void deleteTask(TaskEntity taskEntity) throws Exception;
}
