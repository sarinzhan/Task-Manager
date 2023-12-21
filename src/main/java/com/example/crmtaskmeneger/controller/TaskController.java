package com.example.crmtaskmeneger.controller;

import com.example.crmtaskmeneger.dto.TaskDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    @GetMapping("getAllTasks")
    public List<TaskDto> getAllTask(){
        return null;
    }
}
