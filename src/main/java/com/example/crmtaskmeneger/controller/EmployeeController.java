package com.example.crmtaskmeneger.controller;

import com.example.crmtaskmeneger.dto.EmployeeDto;
import com.example.crmtaskmeneger.dto.TaskDto;
import com.example.crmtaskmeneger.dto.UserDto;
import com.example.crmtaskmeneger.dto.UserDtoRequest;
import com.example.crmtaskmeneger.entity.TaskEntity;
import com.example.crmtaskmeneger.entity.UserEntity;
import com.example.crmtaskmeneger.entity.enumeric.UserRole;
import com.example.crmtaskmeneger.service.AuthService;
import com.example.crmtaskmeneger.service.TaskService;
import com.example.crmtaskmeneger.service.UserService;
import com.example.crmtaskmeneger.util.TaskMapper;
import com.example.crmtaskmeneger.util.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class EmployeeController extends BaseClassController {


    @Autowired
    public EmployeeController(
            UserService userService,
            TaskService taskService,
            AuthService authService
            ) {
        super(userService,taskService, authService);
    }

    @GetMapping("/all_employees")
    public ModelAndView getAllEmployeeSystem(
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

        List<UserEntity> entityList = userService.getAll();
        List<EmployeeDto> employeeList = UserMapper.mapEntityListToEmployeeDtoList(entityList);

        for (int i = 0; i < employeeList.size(); i++) {
            if (Objects.nonNull(entityList.get(i).getExecutedTask())) {
                TaskDto taskDto = TaskMapper.mapEntityToDto(entityList.get(i).getExecutedTask());
                employeeList.get(i).setTaskPerformsEmployee(taskDto);
            }
        }

        for (int i = 0; i < employeeList.size(); i++) {
            if (employeeList.get(i).getEmployeeId().equals(userDto.getUserId())) {
                employeeList.remove(i);
                i--;
            }
            if (userDto.getUserRole().equals(UserRole.EMPLOYEE) &&
                    employeeList.get(i).getEmployeeRole().equals(UserRole.DIRECTOR)
            ) {
                employeeList.remove(i);
                i--;
            }
        }
        model.addObject("employee_list", employeeList);
        model.addObject("userDto", userDto);
        model.setViewName("employes_page/all_employes_page.html");
        return model;
    }


    @GetMapping(value = "/new_employee")
    public ModelAndView registerNewUser(
            ModelAndView model,
            @ModelAttribute(name = "UserDto") UserDto userDto
    ) {
        try {
            userDto = refreshUser(userDto);
        } catch (Exception e) {
            model.addObject("message", e.getMessage());
            model.setViewName("error/error_page.html");
            return model;
        }
        model.addObject("userDto", userDto);
        model.setViewName("users_page/new_user_or_employee.html");
        return model;
    }

    @PostMapping(value = "/new_employee")
    public ModelAndView registerNewUserAction(
            ModelAndView model,
            @ModelAttribute UserDtoRequest userDtoRequest,
            @ModelAttribute(name = "equalsPassword") String equalsPassword,
            @ModelAttribute(name = "UserDto") UserDto userDto
    ) {
        try {
            userDto = refreshUser(userDto);
        } catch (Exception e) {
            model.addObject("message", e.getMessage());
            model.setViewName("error/error_page.html");
            return model;
        }

        if (!userDtoRequest.getRequestPassword().equals(equalsPassword)) {
            try {
                throw new Exception("Пароли не совпадают повторите попытку");
            } catch (Exception e) {
                model.addObject("message", e.getMessage());
                model.setViewName("error/error_page.html");
                return model;
            }
        }
        UserEntity user = UserMapper.mapDtoToEntity(userDtoRequest);
        user.setDateOfEmployment(LocalDate.now());
        userService.saveNewUser(user);
        model.addObject("userDto", userDto);
        model.setViewName("personal_space/personal_space.html");

        return model;
    }

    @GetMapping("/search_employee")
    public ModelAndView searchEmployeeGet(
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
        model.setViewName("employes_page/search_employee.html");

        return model;
    }

    @PostMapping("/search_employee")
    public ModelAndView searchEmployeePost(
            ModelAndView model,
            @ModelAttribute("userDto") UserDto userDto,
            @RequestParam(name = "employeeId", required = false) Long employeeId,
            @RequestParam(name = "employeeLogin", required = false) String employeeLogin,
            @RequestParam(name = "employeeName", required = false) String employeeName,
            @RequestParam(name = "employeeSerName", required = false) String employeeSerName,
            @RequestParam(name = "employeePatronymic", required = false) String employeePatronymic,
            @RequestParam(name = "employeeDateOfEmployment", required = false) String employeeDateOfEmployment

    ) {
        try {
            userDto = refreshUser(userDto);
        } catch (Exception e) {
            model.addObject("message", e.getMessage());
            model.setViewName("error/error_page.html");
            return model;
        }

        boolean error = true;
        if (employeeId != null) error = false;
        if (employeeLogin != null && !employeeLogin.isEmpty()) error = false;
        if (employeeName != null && !employeeName.isEmpty()) error = false;
        if (employeeSerName != null && !employeeSerName.isEmpty()) error = false;
        if (employeePatronymic != null && !employeePatronymic.isEmpty()) error = false;
        if (employeeDateOfEmployment != null && !employeeDateOfEmployment.isEmpty()) error = false;

        if (error) {
            try {
                throw new Exception("Хотя бы одно поле при поиске должно быть заполнено");
            } catch (Exception e) {
                model.addObject("userDto", userDto);
                model.addObject("message", e.getMessage());
                model.setViewName("error/error_page.html");
                return model;
            }
        }
        UserEntity foundById = null;
        UserEntity foundByLogin = null;
        List<UserEntity> foundByName = null;
        List<UserEntity> foundBySerName = null;
        List<UserEntity> foundByPatronymic = null;
        List<UserEntity> foundByDateOfEmployment = null;
        if (employeeId != null && employeeId > 0) {
            try {
                 foundById = userService.getUserById(employeeId);
            } catch (Exception e) {            }
        }
        if (employeeLogin != null && !employeeLogin.isEmpty()) {
            try {
                foundByLogin = userService.getUserById(employeeId);
            } catch (Exception e) {            }
        }
        if (employeeName != null && !employeeName.isEmpty()) {
            try {
                foundByName = userService.getAllUsersByName(employeeName);
            } catch (Exception e) {            }
        }
        if (employeeSerName != null && !employeeSerName.isEmpty()) {
            try {
                foundBySerName = userService.getAllUsersBySerName(employeeSerName);
            } catch (Exception e) {            }
        }
        if (employeePatronymic != null && !employeePatronymic.isEmpty()) {
            try {
                foundByPatronymic = userService.getAllUsersByPatronymic(employeePatronymic);
            } catch (Exception e) {            }
        }
        if (employeeDateOfEmployment != null && !employeeDateOfEmployment.isEmpty()) {
            try {
                foundByDateOfEmployment = userService.getAllUsersByDateOfEmployment(LocalDate.parse(employeeDateOfEmployment));
            } catch (Exception e) {            }
        }

        List<UserEntity> resultAllUserSearch = new ArrayList<>();
        if(foundById != null) resultAllUserSearch.add(foundById);
        if(foundByLogin != null) resultAllUserSearch.add(foundByLogin);
        if(foundByName != null && !foundByName.isEmpty()){
            resultAllUserSearch.addAll(foundByName);
        }
        if(foundBySerName != null && !foundBySerName.isEmpty()){
            resultAllUserSearch.addAll(foundBySerName);
        }
        if(foundByPatronymic != null && !foundByPatronymic.isEmpty()){
            resultAllUserSearch.addAll(foundByPatronymic);
        }
        if(foundByDateOfEmployment != null && !foundByDateOfEmployment.isEmpty()){
            resultAllUserSearch.addAll(foundByDateOfEmployment);
        }

        if(resultAllUserSearch.isEmpty()){
            try {
                throw new Exception("Такой сотрудник не был найден в системе");
            } catch (Exception e) {
                model.addObject("userDto", userDto);
                model.addObject("message", e.getMessage());
                model.setViewName("error/error_page.html");
                return model;
            }
        }

        List<EmployeeDto> employeeList = UserMapper.mapEntityListToEmployeeDtoList(resultAllUserSearch);
        model.addObject("userDto", userDto);
        model.addObject("employee_list", employeeList);
        model.setViewName("employes_page/all_employes_page.html");


        return model;
    }

    @GetMapping(value = "/view_active_task")
    public ModelAndView getActivTAsk(
            ModelAndView model,
            @ModelAttribute(name = "userDto") UserDto userDto
    ){
        try {
            userDto = refreshUser(userDto);
        } catch (Exception e) {
            model.addObject("message", e.getMessage());
            model.setViewName("error/error_page.html");
            return model;
        }

        UserEntity user = null;

        try {
            user = userService.getUserById(userDto.getUserId());
        } catch (Exception e) {
            model.addObject("userDto", userDto);
            model.addObject("message", e.getMessage());
            model.setViewName("error/error_page.html");
            return model;
        }
        TaskEntity taskEntity = user.getExecutedTask();

        if(Objects.isNull(taskEntity)){
            try {
                throw new Exception("У вас нет активной задачи");
            } catch (Exception e) {
                model.addObject("userDto", userDto);
                model.addObject("message", e.getMessage());
                model.setViewName("error/error_page.html");
                return model;
            }
        }
        TaskDto taskDto = TaskMapper.mapEntityToDto(taskEntity);
        List<TaskDto> taskDtoList = new ArrayList<>();
        taskDtoList.add(taskDto);

        model.addObject("userDto", userDto);
        model.addObject("task_list", taskDtoList);
        model.setViewName("task_pages/all_tasks.html");

        return model;
    }


    @GetMapping(value = "/all_tasks_employee")
    public ModelAndView getTaskEmployee(
            ModelAndView model,
            @ModelAttribute(name = "userDto") UserDto userDto
    ){

        try {
            userDto = refreshUser(userDto);
        } catch (Exception e) {
            model.addObject("message", e.getMessage());
            model.setViewName("error/error_page.html");
            return model;
        }

        UserEntity user = null;

        try {
            user = userService.getUserById(userDto.getUserId());
        } catch (Exception e) {
            model.addObject("userDto", userDto);
            model.addObject("message", e.getMessage());
            model.setViewName("error/error_page.html");
            return model;
        }

        List<TaskEntity> taskExecuted = null;
        try {
             taskExecuted = taskService.getTaskListByExecutedUser(user);

            if(Objects.isNull(taskExecuted)){
                throw new Exception("Вы еще нет задач которые вы выполняли");
            }
        } catch (Exception e) {
            model.addObject("userDto", userDto);
            model.addObject("message", e.getMessage());
            model.setViewName("error/error_page.html");
            return model;
        }

        if(Objects.isNull(taskExecuted)){
            try {
                throw new Exception("У вас нет активной задачи");
            } catch (Exception e) {
                model.addObject("userDto", userDto);
                model.addObject("message", e.getMessage());
                model.setViewName("error/error_page.html");
                return model;
            }
        }

        List<TaskDto> taskDtoList = TaskMapper.mapEntityListToTaskDtoList(taskExecuted);


        model.addObject("userDto", userDto);
        model.addObject("task_list", taskDtoList);
        model.setViewName("task_pages/all_tasks.html");
        return model;
    }

    @GetMapping("/epmloyee_info")
    public ModelAndView getInfoEmployee(
            ModelAndView model,
            @ModelAttribute("userDto") UserDto userDto,
            @ModelAttribute("employeeDto") EmployeeDto employeeDto
    )  {

        try {
            userDto = refreshUser(userDto);
        } catch (Exception e) {
            model.addObject("message", e.getMessage());
            model.setViewName("error/error_page.html");
            return model;
        }


        try {
            UserEntity entity = userService.getUserById(employeeDto.getEmployeeId());
            employeeDto = UserMapper.mapEntityToEmployeeDto(entity);
            employeeDto.setTaskPerformsEmployee(Objects.nonNull(entity.getExecutedTask())?TaskMapper.mapEntityToDto(entity.getExecutedTask()): null);
        } catch (Exception e) {
            model.addObject("userDto", userDto);
            model.addObject("message", e.getMessage());
            model.setViewName("error/error_page.html");
            return model;
        }
        model.addObject("employeeDto", employeeDto);
        model.addObject("userDto", userDto);
        model.setViewName("employes_page/employee_info.html");
        return model;
    }

    @GetMapping("/fire_an_employee")
    public ModelAndView deleteEmployee(
            ModelAndView model,
            @ModelAttribute("userDto") UserDto userDto,
            @ModelAttribute("employeeDto") EmployeeDto employeeDto
    )  {

        try {
            userDto = refreshUser(userDto);
        } catch (Exception e) {
            model.addObject("message", e.getMessage());
            model.setViewName("error/error_page.html");
            return model;
        }


        model.addObject("userDto", userDto);
        try {
            userService.deleteUser(employeeDto.getEmployeeId());
        } catch (Exception e) {

            model.addObject("message", e.getMessage());
            model.setViewName("error/error_page.html");
            return model;
        }
        model.setViewName("personal_space/personal_space.html");
        return model;
    }

    @GetMapping("/update_employee")
    public ModelAndView updateEmployeeGet(
            ModelAndView model,
            @ModelAttribute("userDto") UserDto userDto,
            @ModelAttribute("employeeDto") EmployeeDto employeeDto
    ){
        try {
            userDto = refreshUser(userDto);
        } catch (Exception e) {
            model.addObject("message", e.getMessage());
            model.setViewName("error/error_page.html");
            return model;
        }

        model.addObject("userDto", userDto);
        model.addObject("employeeDto", employeeDto);
        model.setViewName("employes_page/uodate_employee.html");
        return model;
    }

}
