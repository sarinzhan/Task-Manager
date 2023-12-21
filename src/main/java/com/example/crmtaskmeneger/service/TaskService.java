package com.example.crmtaskmeneger.service;

import com.example.crmtaskmeneger.entities.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAll() throws Exception;
    Task getById(Long id) throws Exception;
    Task getByStatus(String status) throws Exception;
    void createTask(Task task);
}
