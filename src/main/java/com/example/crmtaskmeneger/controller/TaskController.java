package com.example.crmtaskmeneger.controller;


import com.example.crmtaskmeneger.dto.*;
import com.example.crmtaskmeneger.entity.TaskEntity;
import com.example.crmtaskmeneger.entity.UserEntity;
import com.example.crmtaskmeneger.entity.enumeric.SelectingAnActionWhenCreatingATask;
import com.example.crmtaskmeneger.entity.enumeric.TaskStatus;
import com.example.crmtaskmeneger.entity.enumeric.UserRole;
import com.example.crmtaskmeneger.service.TaskService;
import com.example.crmtaskmeneger.service.UserService;
import com.example.crmtaskmeneger.util.TaskMapper;
import com.example.crmtaskmeneger.util.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.swing.text.html.parser.Entity;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Controller
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;

    @Autowired
    public TaskController(
            TaskService taskService,
            UserService userService
    ) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping(value = "/all_tasks")
    public ModelAndView getAllTask(
            ModelAndView model,
            @ModelAttribute(name = "userDto") UserDto userDto
    ) throws Exception {

        List<TaskEntity> taskEntityList = null;

        if (userDto.getUserRole().equals(UserRole.EMPLOYEE)) {
            taskEntityList = taskService.getTaskListByStatus(TaskStatus.AWAITING_CONTRACTOR);
        } else if (userDto.getUserRole().equals(UserRole.DIRECTOR)) {
            taskEntityList = taskService.getAll();
        }

        List<TaskDto> taskDtoList = TaskMapper.mapEntityListToTaskDtoList(taskEntityList);
        model.addObject("userDto", userDto);
        model.addObject("task_list", taskDtoList);
        model.setViewName("task_pages/all_tasks.html");
        return model;
    }

    @GetMapping(value = "/new_task")
    public ModelAndView getPageNewTas(
            ModelAndView model,
            @ModelAttribute(name = "userDto") UserDto userDto
    ) {

        if(userDto.getUserRole().equals(UserRole.EMPLOYEE)){
            try {
                if(Objects.nonNull(userService.getUserById(userDto.getUserId()).getExecutedTask())){
                    throw  new Exception("Вы не можите создать для себя задачу так как у вас уже есть активная задача");
                }
            } catch (Exception e) {
                model.addObject("userDto", userDto);
                model.addObject("message", e.getMessage());
                model.setViewName("error/error_page.html");
                return model;
            }
        }

        model.addObject("userDto", userDto);
        model.setViewName("task_pages/new_task.html");
        return model;
    }

    @PostMapping(value = "/new_task")
    public ModelAndView setNewTask(
            ModelAndView model,
            @ModelAttribute(name = "userDto") UserDto userDto,
            @ModelAttribute(name = "task") TaskDto taskDto,
            @ModelAttribute(name = "authorDto") AuthorDto authorDto,
            @ModelAttribute(name = "executorDto") ExecutorDto executorDto,
            @RequestParam(name = "action", required = false)SelectingAnActionWhenCreatingATask action
            ) throws Exception {
        taskDto.setTaskExecutor(executorDto);
        taskDto.setTaskAuthor(authorDto);

        System.out.println(taskDto);

        TaskEntity taskEntity = TaskMapper.mapTaskDtoToEntity(taskDto);
        UserEntity authorEntity = null;
        UserEntity executorEntity = null;
//        try {
           authorEntity  = userService.getUserById(taskDto.getTaskAuthor().getAuthorId());
           taskEntity.setAuthor(authorEntity);


               if (Objects.nonNull(taskDto.getTaskExecutor()) && taskDto.getTaskExecutor().getExecutorId() != null) {
                   executorEntity = userService.getUserById(taskDto.getTaskExecutor().getExecutorId());
                   if(taskEntity.getId() != null){
                       taskEntity = taskService.getTaskById(taskDto.getTaskId());
                   }
                   taskEntity.setExecutor(executorEntity);
                   taskEntity.setStatus(TaskStatus.IN_PROGRESS);
                   taskEntity.setTaskStartTime(LocalDate.now());
               }


//        } catch (Exception e) {
//            model.addObject("userDto", userDto);
//            model.addObject("message", e.getMessage());
//            model.setViewName("error/error_page.html");
//            return model;
//        }
        model.addObject("userDto", userDto);

        if(Objects.nonNull(action)) {
            if (action.equals(SelectingAnActionWhenCreatingATask.SELECT_EMPLOYEE)) {
                taskEntity = taskService.saveNewTask(taskEntity);
                taskDto = TaskMapper.mapEntityToDto(taskEntity);

                List<UserEntity> userEntities = userService.getAlFreeEmployee();
                List<EmployeeDto> employeeDtoList = UserMapper.mapEntityListToEmployeeDtoList(userEntities);
                model.addObject("employee_list", employeeDtoList);
                model.addObject("taskDto", taskDto);
                model.setViewName("employes_page/all_employes_page.html");
                return model;
            }
        }
        taskEntity = taskService.saveNewTask(taskEntity);
        if(Objects.nonNull(executorEntity)){
            executorEntity.setExecutedTask(taskEntity);
            userService.saveNewUser(executorEntity);
        }



        model.setViewName("personal_space/personal_space.html");
        return model;
    }
}
