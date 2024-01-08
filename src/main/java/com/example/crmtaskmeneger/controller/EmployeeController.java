package com.example.crmtaskmeneger.controller;

import com.example.crmtaskmeneger.dto.EmployeeDto;
import com.example.crmtaskmeneger.dto.TaskDto;
import com.example.crmtaskmeneger.dto.UserDto;
import com.example.crmtaskmeneger.dto.UserDtoRequest;
import com.example.crmtaskmeneger.entity.UserEntity;
import com.example.crmtaskmeneger.entity.enumeric.UserRole;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class EmployeeController {

    private final UserService userService;

    @Autowired
    public EmployeeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all_employees")
    public ModelAndView getAllEmployeeSystem(
            ModelAndView model,
            @ModelAttribute(name = "userDto") UserDto userDto
    ) {

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

}
