package com.example.crmtaskmeneger.service.impl;

import com.example.crmtaskmeneger.entities.Task;
import com.example.crmtaskmeneger.repository.TaskRepository;
import com.example.crmtaskmeneger.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceEmpl implements TaskService {
    TaskRepository taskRepository;
    @Autowired
    public TaskServiceEmpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getAll() throws Exception {
        List<Task> all = taskRepository.findAll();
        if(all.isEmpty()){
            throw new Exception("Not task in database");
        }
        return all;
    }

    @Override
    public Task getById(Long id) throws Exception {
        Optional<Task> taskById = taskRepository.getTaskById(id);
        if(taskById.isEmpty()){
            throw  new Exception("Task wasn't found");
        }
        return taskById.get();
    }

    @Override
    public Task getByStatus(String status) throws Exception {
        Optional<Task> taskByStatus = taskRepository.getTaskByStatus(status);
        if(taskByStatus.isEmpty()){
            throw  new Exception("Task wasn't found");
        }
        return taskByStatus.get();
    }
}
