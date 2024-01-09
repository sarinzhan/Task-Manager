package com.example.crmtaskmeneger.controller;


import com.example.crmtaskmeneger.dto.*;
import com.example.crmtaskmeneger.entity.TaskEntity;
import com.example.crmtaskmeneger.entity.UserEntity;
import com.example.crmtaskmeneger.entity.enumeric.SelectingAnActionWhenCreatingATask;
import com.example.crmtaskmeneger.entity.enumeric.TaskStatus;
import com.example.crmtaskmeneger.entity.enumeric.TaskStatusExecutorUpdate;
import com.example.crmtaskmeneger.entity.enumeric.UserRole;
import com.example.crmtaskmeneger.service.AuthService;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Controller
public class TaskController extends BaseClassController {

    @Autowired
    public TaskController(UserService userService, TaskService taskService, AuthService authService) {
        super(userService, taskService, authService);
    }

    @GetMapping(value = "/all_tasks")
    public ModelAndView getAllTask(
            ModelAndView model,
            @ModelAttribute(name = "userDto") UserDto userDto,
            @RequestParam(name = "action", required = false) SelectingAnActionWhenCreatingATask action
    ) {

        try {
            userDto = refreshUser(userDto);
        } catch (Exception e) {
            model.addObject("message", e.getMessage());
            model.setViewName("error/error_page.html");
            return model;
        }


        List<TaskEntity> taskEntityList = null;


        if (Objects.nonNull(action)) {
            if (action.equals(SelectingAnActionWhenCreatingATask.SELECT_FREE_TASK)) {
                try {
                    taskEntityList = taskService.getAllFreeTasks();
                } catch (Exception e) {
                    model.addObject("message", e.getMessage());
                    model.setViewName("error/error_page.html");
                    return model;
                }
            }
        } else {
            if (userDto.getUserRole().equals(UserRole.EMPLOYEE)) {
                try {
                    taskEntityList = taskService.getTaskListByStatus(TaskStatus.AWAITING_CONTRACTOR);
                } catch (Exception e) {
                    model.addObject("message", e.getMessage());
                    model.setViewName("error/error_page.html");
                    return model;
                }
            } else if (userDto.getUserRole().equals(UserRole.DIRECTOR)) {
                taskEntityList = taskService.getAll();
            }
        }


        List<TaskDto> taskDtoList = TaskMapper.mapEntityListToTaskDtoList(taskEntityList);
        model.addObject("userDto", userDto);
        model.addObject("task_list", taskDtoList);
        model.setViewName("task_pages/all_tasks.html");
        checkingTheOperationOfTheMethodAndThePassedParameters("getAllTask", "завшел в контроллер и смотрю почему не проходит", userDto, action, taskDtoList);
        return model;
    }

    @GetMapping(value = "/new_task")
    public ModelAndView getPageNewTas(
            ModelAndView model,
            @ModelAttribute(name = "userDto") UserDto userDto
    ) {

        try {
            userDto = refreshUser(userDto);
        } catch (Exception e) {
            model.addObject("message", e.getMessage());
            model.setViewName("error/error_page.html");
            return model;
        }

        if (userDto.getUserRole().equals(UserRole.EMPLOYEE)) {
            try {
                if (Objects.nonNull(userService.getUserById(userDto.getUserId()).getExecutedTask())) {
                    throw new Exception("Вы не можите создать для себя задачу так как у вас уже есть активная задача");
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
            @ModelAttribute(name = "taskDto") TaskDto taskDto,
            @ModelAttribute(name = "authorDto") AuthorDto authorDto,
            @ModelAttribute(name = "executorDto") ExecutorDto executorDto,
            @RequestParam(name = "action", required = false) SelectingAnActionWhenCreatingATask action
    ) {

        try {
            userDto = refreshUser(userDto);
        } catch (Exception e) {
            model.addObject("message", e.getMessage());
            model.setViewName("error/error_page.html");
            return model;
        }

        taskDto.setTaskExecutor(executorDto);
        taskDto.setTaskAuthor(authorDto);

        TaskEntity taskEntity = TaskMapper.mapTaskDtoToEntity(taskDto);
        UserEntity authorEntity = null;
        UserEntity executorEntity = null;
        try {
            if (taskDto.getTaskAuthor() != null && taskDto.getTaskAuthor().getAuthorId() != null)
                authorEntity = userService.getUserById(taskDto.getTaskAuthor().getAuthorId());
            taskEntity.setAuthor(authorEntity);
            if (Objects.nonNull(taskDto.getTaskExecutor()) && taskDto.getTaskExecutor().getExecutorId() != null) {
                executorEntity = userService.getUserById(taskDto.getTaskExecutor().getExecutorId());
                if (taskEntity.getId() != null) {
                    taskEntity = taskService.getTaskById(taskDto.getTaskId());
                }
                taskEntity.setExecutor(executorEntity);
                taskEntity.setStatus(TaskStatus.IN_PROGRESS);
                taskEntity.setTaskStartTime(LocalDate.now());
            }
        } catch (Exception e) {
            model.addObject("userDto", userDto);
            model.addObject("message", e.getMessage());
            model.setViewName("error/error_page.html");
            return model;
        }
        model.addObject("userDto", userDto);

        if (Objects.nonNull(action)) {
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

        checkingTheOperationOfTheMethodAndThePassedParameters(
                "setNewTask",
                "Проверяем пересозранение задачи когда сотружник ее принял сам",
                userDto,
                authorDto,
                executorDto);
        taskEntity = taskService.saveNewTask(taskEntity);
        if (Objects.nonNull(executorEntity)) {
            executorEntity.setExecutedTask(taskEntity);
            userService.saveNewUser(executorEntity);
        }
        try {
            userDto = refreshUser(userDto);
        } catch (Exception e) {
            model.addObject("message", e.getMessage());
            model.setViewName("error/error_page.html");
            return model;
        }
        model.addObject("userDto", userDto);
        model.setViewName("personal_space/personal_space.html");
        return model;
    }

    @GetMapping("/search_task")
    public ModelAndView searchTaskGet(
            ModelAndView model,
            @ModelAttribute("userDto") UserDto userDto
    ) {

        try {
            userDto = refreshUser(userDto);
        } catch (Exception e) {
            model.addObject("message", e.getMessage());
            model.setViewName("error/error_page.html");
            return model;
        }
        model.addObject("userDto", userDto);
        model.setViewName("task_pages/search_tasks.html");

        return model;
    }

    @PostMapping("/search_task")
    public ModelAndView searchTaskPost(
            ModelAndView model,
            @ModelAttribute(value = "userDto") UserDto userDto,
            @RequestParam(name = "taskId", required = false) Long taskId,
            @RequestParam(name = "taskThem", required = false) String taskThem,
            @RequestParam(name = "executorId", required = false) Long executorId,
            @RequestParam(name = "executorName", required = false) String executorName,
            @RequestParam(name = "authorId", required = false) Long authorId,
            @RequestParam(name = "authorName", required = false) String authorName,
            @RequestParam(name = "taskDataCreate", required = false) String taskDataCreate,
            @RequestParam(name = "taskStartTime", required = false) String taskStartTime,
            @RequestParam(name = "taskDataCompletion", required = false) String taskDataCompletion
    ) {

        try {
            userDto = refreshUser(userDto);
        } catch (Exception e) {
            model.addObject("message", e.getMessage());
            model.setViewName("error/error_page.html");
            return model;
        }

        model.addObject("userDto", userDto);
        boolean error = true;
        if (
                taskId != null ||
                        (taskThem != null && !taskThem.isEmpty()) ||
                        executorId != null ||
                        (executorName != null && !executorName.isEmpty()) ||
                        authorId != null ||
                        (authorName != null && !authorName.isEmpty()) ||
                        (taskDataCreate != null && !taskDataCreate.isEmpty()) ||
                        (taskStartTime != null && !taskStartTime.isEmpty()) ||
                        (taskDataCompletion != null && !taskDataCompletion.isEmpty())
        ) {
            error = false;
        }

        if (error) try {
            throw new Exception("Для поиска задачи хотя бы одно поле должно быть заполнено");
        } catch (Exception e) {
            model.addObject("message", e.getMessage());
            model.setViewName("error/error_page.html");
            return model;
        }

        TaskEntity resultId = null;
        List<TaskEntity> resultThem = null;
        UserEntity resultExecutorId = null;
        List<UserEntity> resultExecutorName = null;
        UserEntity resultAuthorId = null;
        List<UserEntity> resultAuthorName = null;
        List<TaskEntity> resultTasksCreateDate = null;
        List<TaskEntity> resultTasksStartExecute = null;
        List<TaskEntity> resultTasksCompletionDate = null;
        List<TaskEntity> resultTasksExecutorId = null;
        List<TaskEntity> resultTasksExecutorName = new ArrayList<>();
        List<TaskEntity> resultTasksAuthorId = null;
        List<TaskEntity> resultTasksAuthorName = new ArrayList<>();
        try {
            resultId = taskService.getTaskById(taskId);
        } catch (Exception e) {
        }
        try {
            resultThem = taskService.getTaskByTaskThem(taskThem);
        } catch (Exception e) {
        }

        try {
            resultExecutorId = userService.getUserById(executorId);
        } catch (Exception e) {
        }
        try {
            resultExecutorName = userService.getAllUsersByName(executorName);
        } catch (Exception e) {
        }
        try {
            resultAuthorId = userService.getUserById(authorId);
        } catch (Exception e) {
        }
        try {
            resultAuthorName = userService.getAllUsersByName(authorName);
        } catch (Exception e) {
        }
        try {
            resultTasksCreateDate = taskService.getTaskListByDataCreate(LocalDateTime.parse(taskDataCreate));
        } catch (Exception e) {
        }
        try {
            resultTasksCreateDate = taskService.getTaskListByStartedTime(LocalDate.parse(taskStartTime));
        } catch (Exception e) {
        }
        try {
            resultTasksCreateDate = taskService.getTaskListByDateCompletion(LocalDate.parse(taskDataCompletion));
        } catch (Exception e) {
        }
        if (resultAuthorId != null) {
            try {
                resultTasksExecutorId = taskService.getTaskListByAuthor(resultAuthorId);
            } catch (Exception e) {
            }
        }
        if (resultAuthorId != null) {
            try {
                resultTasksAuthorId = taskService.getTaskListByAuthor(resultAuthorId);
            } catch (Exception e) {
            }
        }
        if (resultAuthorName != null) {
            try {
                for (UserEntity el : resultAuthorName) {
                    List<TaskEntity> authorTmp = taskService.getTaskListByAuthor(el);
                    if (authorTmp != null && !authorTmp.isEmpty()) {
                        resultTasksAuthorName.addAll(authorTmp);
                    }
                }
            } catch (Exception e) {
            }
        }
        if (resultAuthorId != null) {
            try {
                resultTasksExecutorId = taskService.getTaskListByExecutedUser(resultExecutorId);
            } catch (Exception e) {
            }
        }
        if (resultExecutorName != null) {
            try {
                for (UserEntity el : resultExecutorName) {
                    List<TaskEntity> executeTmp = taskService.getTaskListByAuthor(el);
                    if (executeTmp != null && !executeTmp.isEmpty()) {
                        resultTasksExecutorName.addAll(executeTmp);
                    }
                }
            } catch (Exception e) {
            }
        }

        List<TaskEntity> result = new ArrayList<>();

        if (resultId != null) {
            result.add(resultId);
        }
        if (resultThem != null && !resultThem.isEmpty()) {
            result.addAll(resultThem);
        }
        if (resultTasksCreateDate != null && !resultTasksCreateDate.isEmpty()) {
            result.addAll(resultTasksCreateDate);
        }
        if (resultTasksStartExecute != null && !resultTasksStartExecute.isEmpty()) {
            result.addAll(resultTasksStartExecute);
        }
        if (resultTasksCompletionDate != null && !resultTasksCompletionDate.isEmpty()) {
            result.addAll(resultTasksCompletionDate);
        }
        if (resultTasksExecutorId != null && !resultTasksExecutorId.isEmpty()) {
            result.addAll(resultTasksExecutorId);
        }
        if (resultTasksExecutorName != null && !resultTasksExecutorName.isEmpty()) {
            result.addAll(resultTasksExecutorName);
        }
        if (resultTasksAuthorId != null && !resultTasksAuthorId.isEmpty()) {
            result.addAll(resultTasksAuthorId);
        }
        if (resultTasksAuthorName != null && !resultTasksAuthorName.isEmpty()) {
            result.addAll(resultTasksAuthorName);
        }

        if (result.isEmpty()) {
            try {
                throw new Exception("По данным параметрам задачи не были найдены");
            } catch (Exception e) {
                model.addObject("message", e.getMessage());
                model.setViewName("error/error_page.html");
                return model;
            }
        }

        Set<TaskEntity> removingDuplicates = new HashSet<>(result);
        result = removingDuplicates.stream().toList();
        List<TaskDto> resultTaskDto = TaskMapper.mapEntityListToTaskDtoList(result);

        model.addObject("task_list", resultTaskDto);
        model.setViewName("task_pages/all_tasks.html");
        return model;
    }

    @GetMapping("/task_info")
    public ModelAndView getTaskInfoPGet(
            ModelAndView model,
            @ModelAttribute("userDto") UserDto userDto,
            @ModelAttribute("taskDto") TaskDto taskDto,
            @ModelAttribute("authorDto") AuthorDto authorDto,
            @ModelAttribute("executorDto") ExecutorDto executorDto
    ) {
        try {
            userDto = refreshUser(userDto);
            taskDto = TaskMapper.mapEntityToDto(taskService.getTaskById(taskDto.getTaskId()));
        } catch (Exception e) {
            model.addObject("message", e.getMessage());
            model.setViewName("error/error_page.html");
            return model;
        }

        checkingTheOperationOfTheMethodAndThePassedParameters("getTaskInfoPGet", "Проверка транзита данных", userDto, taskDto, authorDto, executorDto);
        model.addObject("userDto", userDto);
        model.addObject("taskDto", taskDto);
        model.setViewName("task_pages/task_info.html");
        return model;
    }

    @GetMapping(value = "/update_task_status_completed")
    public ModelAndView updateTaskStatus(
            ModelAndView model,
            @ModelAttribute(name = "userDto") UserDto userDto,
            @ModelAttribute(name = "taskStatus") TaskStatus taskStatus
    ) {
        try {
            userDto = refreshUser(userDto);
        } catch (Exception e) {
            model.addObject("message", e.getMessage());
            model.setViewName("error/error_page.html");
            return model;
        }
        UserEntity userEntity = null;
        try {
            userEntity = userService.getUserById(userDto.getUserId());
        } catch (Exception e) {
            model.addObject("message", e.getMessage());
            model.setViewName("error/error_page.html");
            return model;
        }

        TaskEntity taskEntity = userEntity.getExecutedTask();
        if (Objects.isNull(taskEntity)) {
            try {
                throw new Exception("У вас нет активной задачи для ее завершения");
            } catch (Exception e) {
                model.addObject("message", e.getMessage());
                model.setViewName("error/error_page.html");
                return model;
            }
        }
        userEntity.setExecutedTask(null);
        userEntity = userService.saveNewUser(userEntity);
        try {
            taskEntity = taskService.updateTask(taskEntity, taskStatus);
        } catch (Exception e) {
            model.addObject("message", e.getMessage());
            model.setViewName("error/error_page.html");
            return model;
        }
        userDto = UserMapper.mapEntityToUserDto(userEntity);
        model.addObject("userDto", userDto);
        model.setViewName("personal_space/personal_space.html");
        return model;
    }

    @GetMapping(value = "/task_update")
    public ModelAndView getUpdateTask(
            ModelAndView model,
            @ModelAttribute(name = "userDto") UserDto userDto,
            @ModelAttribute(name = "taskDto") TaskDto taskDto
    ) {
        try {
            userDto = refreshUser(userDto);
        } catch (Exception e) {
            model.addObject("message", e.getMessage());
            model.setViewName("error/error_page.html");
            return model;
        }

        try {
            taskDto = TaskMapper.mapEntityToDto(taskService.getTaskById(taskDto.getTaskId()));
        } catch (Exception e) {
            model.addObject("message", e.getMessage());
            model.setViewName("error/error_page.html");
            return model;
        }
        model.addObject("userDto", userDto);
        model.addObject("taskDto", taskDto);
        model.setViewName("task_pages/task_update.html");
        return model;
    }

    @PostMapping(value = "/task_update")
    public ModelAndView postUpdateTask(
            ModelAndView model,
            @ModelAttribute(name = "userDto") UserDto userDto,
            @RequestParam(name = "taskId") Long taskId,
            @RequestParam(name = "taskThem", required = false) String taskThem,
            @RequestParam(name = "taskDescription", required = false) String taskDescription,
            @RequestParam(name = "taskDataCompletion", required = false) String taskDataCompletion,
            @RequestParam(name = "taskStartTime", required = false) String taskStartTime,
            @RequestParam(name = "taskStatus", required = false) TaskStatus taskStatus,
            @RequestParam(name = "executor_action", required = false) TaskStatusExecutorUpdate executorAction
    ){

        checkingTheOperationOfTheMethodAndThePassedParameters("postUpdateTask", "Проверка входных параметров изменения данных задачи",
                model, userDto, taskId, taskThem, taskDescription, taskDataCompletion, taskStartTime, taskStatus, executorAction);
        try {
            userDto = refreshUser(userDto);
        } catch (Exception e) {
            model.addObject("message", e.getMessage());
            model.setViewName("error/error_page.html");
            return model;
        }
        TaskEntity taskEntity = null;
        try {
             taskEntity = taskService.getTaskById(taskId);
        } catch (Exception e) {
            model.addObject("message", e.getMessage());
            model.setViewName("error/error_page.html");
            return model;
        }
        if(Objects.nonNull(taskThem) && !taskThem.isEmpty()){
            taskEntity.setTaskThem(taskThem);
        }
        if(Objects.nonNull(taskDescription) && !taskDescription.isEmpty()){
            taskEntity.setDescription(taskDescription);
        }
        if(Objects.nonNull(taskDataCompletion) && !taskDataCompletion.isEmpty()){
            taskEntity.setDateCompletion(LocalDate.parse(taskDataCompletion));
        }
        if(Objects.nonNull(taskStartTime) && !taskStartTime.isEmpty()){
            taskEntity.setTaskStartTime(LocalDate.parse(taskStartTime));
        }
        if(Objects.nonNull(taskStatus)){
            taskEntity.setStatus(taskStatus);
        }
        if(Objects.nonNull(executorAction) && TaskStatusExecutorUpdate.DELETE_THE_EXECUTOR.equals(executorAction)){
            if(Objects.nonNull(taskEntity.getExecutor())){
                UserEntity userExecutorEntity = taskEntity.getExecutor();
                if(userExecutorEntity.getExecutedTask().equals(taskEntity)){
                    userExecutorEntity.setExecutedTask(null);
                    userService.saveNewUser(userExecutorEntity);
                }
                taskEntity.setExecutor(null);
            }
        }

        taskEntity = taskService.saveNewTask(taskEntity);

            model.addObject("userDto", userDto);
        model.addObject("message", "Данные задачи №:("+ taskId +" )были изменены");
        model.setViewName("error/error_page.html");
        return model;
    }



}
