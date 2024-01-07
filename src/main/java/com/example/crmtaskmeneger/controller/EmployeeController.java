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
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
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
            @ModelAttribute(name = "userDto")UserDto userDto
            ){

        List<UserEntity> entityList = userService.getAll();
        List<EmployeeDto> employeeList = UserMapper.mapEntityListToEmployeeDtoList(entityList);

        for (int i = 0; i < employeeList.size(); i++) {
            if(Objects.nonNull(entityList.get(i).getExecutedTask())){
                TaskDto taskDto =  TaskMapper.mapEntityToDto(entityList.get(i).getExecutedTask());
                employeeList.get(i).setTaskPerformsEmployee(taskDto);
            }
        }

        for (int i = 0; i < employeeList.size(); i++) {
            if(employeeList.get(i).getEmployeeId().equals(userDto.getUserId())){
                employeeList.remove(i);
                i--;
            }
            if(userDto.getUserRole().equals(UserRole.EMPLOYEE) &&
                    employeeList.get(i).getEmployeeRole().equals(UserRole.DIRECTOR)
            ){
                employeeList.remove(i);
                i--;
            }
        }
        model.addObject("employee_list", employeeList);
        model.addObject("userDto",userDto);
        model.setViewName("employes_page/all_employes_page.html");
        return model;
    }


    @GetMapping(value = "/new_employee")
    public ModelAndView registerNewUser(
            ModelAndView model,
            @ModelAttribute(name = "UserDto") UserDto userDto
    ){
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
    ){

        if(!userDtoRequest.getRequestPassword().equals(equalsPassword)){
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
        user = userService.saveNewUser(user);
        model.addObject("userDto", userDto);

        model.setViewName("personal_space/personal_space.html");

        return model;
    }
}
