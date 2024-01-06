package com.example.crmtaskmeneger.controller;


import com.example.crmtaskmeneger.dto.TaskDto;
import com.example.crmtaskmeneger.dto.UserDto;
import com.example.crmtaskmeneger.entity.TaskEntity;
import com.example.crmtaskmeneger.entity.UserEntity;
import com.example.crmtaskmeneger.entity.enumeric.TaskStatus;
import com.example.crmtaskmeneger.entity.enumeric.UserRole;
import com.example.crmtaskmeneger.service.AuthService;
import com.example.crmtaskmeneger.service.TaskService;
import com.example.crmtaskmeneger.util.TaskMapper;
import com.example.crmtaskmeneger.util.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Objects;

@Controller
public class TaskController {

    private final TaskService taskService;
    private final AuthService authService;

    @Autowired
    public TaskController(TaskService taskService, AuthService authService) {
        this.taskService = taskService;
        this.authService = authService;
    }

    @GetMapping(value = "/all_tasks")
    public ModelAndView getAllTask(
            ModelAndView model,
            @ModelAttribute(name = "userDto") UserDto userDto
    ) throws Exception {

        List<TaskEntity> taskEntityList = null;

        if(userDto.getUserRole().equals(UserRole.EMPLOYEE)){
            taskEntityList = taskService.getTaskListByStatus(TaskStatus.AWAITING_CONTRACTOR);
        }else if(userDto.getUserRole().equals(UserRole.DIRECTOR)){
            taskEntityList = taskService.getAll();
        }

        List<TaskDto> taskDtoList = TaskMapper.mapEntityListToTaskDtoList(taskEntityList);
        model.addObject("userDto", userDto);
        model.addObject("taskDto_list", taskDtoList);
        model.setViewName("task_pages/all_tasks.html");
        return model;
    }
}
