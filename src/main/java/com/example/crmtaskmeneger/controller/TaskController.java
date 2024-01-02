package com.example.crmtaskmeneger.controller;

import com.example.crmtaskmeneger.dto.TaskDto;
import com.example.crmtaskmeneger.dto.request.TaskDtoRequest;
import com.example.crmtaskmeneger.dto.response.TaskDtoResponse;
import com.example.crmtaskmeneger.entities.Employee;
import com.example.crmtaskmeneger.entities.Task;
import com.example.crmtaskmeneger.entities.TaskStatus;
import com.example.crmtaskmeneger.mapping.TaskMapping;
import com.example.crmtaskmeneger.service.TaskService;
import com.example.crmtaskmeneger.service.impl.TaskServiceEmpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    TaskService taskService;
    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/getAllTasks")
    public List<TaskDto> getAllTask() throws Exception {
        return null;
    }
    @GetMapping("/getTaskById")
    public Task getTaskById(Long id) throws Exception {
        return taskService.getById(id);
    }
    @PostMapping("/createTask")
    public  void createTask(TaskDtoRequest taskDto){
        Task task = new Task();
        taskService.createTask(task);
    }
}
