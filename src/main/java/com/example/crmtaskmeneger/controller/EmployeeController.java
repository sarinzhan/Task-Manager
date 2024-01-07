package com.example.crmtaskmeneger.controller;

import com.example.crmtaskmeneger.dto.EmployeeDto;
import com.example.crmtaskmeneger.dto.TaskDto;
import com.example.crmtaskmeneger.dto.UserDto;
import com.example.crmtaskmeneger.entity.UserEntity;
import com.example.crmtaskmeneger.entity.enumeric.UserRole;
import com.example.crmtaskmeneger.service.UserService;
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
}
