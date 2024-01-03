package com.example.crmtaskmeneger.controller;

import com.example.crmtaskmeneger.dto.request.EmployeeDtoRequest;
import com.example.crmtaskmeneger.dto.response.EmployeeDtoResponse;
import com.example.crmtaskmeneger.entities.Employee;
import com.example.crmtaskmeneger.entities.Role;
import com.example.crmtaskmeneger.mapping.EmployeeMapping;
import com.example.crmtaskmeneger.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Controller
@Scope("request")
public class AuthController {

    private final EmployeeService employeeService;

    @Autowired
    public AuthController(
            EmployeeService employeeService
    ) {
        this.employeeService = employeeService;
    }

    @PostMapping("/register")
    public ModelAndView login(ModelAndView model, @ModelAttribute(name = "user") EmployeeDtoRequest dtoRequest ) {
        Employee entity = EmployeeMapping.mapModelDtoToEntity(dtoRequest);
        entity = employeeService.save(entity);
        EmployeeDtoResponse response = EmployeeMapping.mapEntityToDtoEmployeeResponse(entity);

        model.addObject("user",response);
        if(response.getRole().equals(Role.DIRECTOR)){
            model.setViewName("thirt_floor/area_director");
        }else {
            model.setViewName("thirt_floor/area_employee");
        }
        return model;
    }
    @PostMapping("/login")
    public ModelAndView register(
            @RequestParam String login,
            @RequestParam String password,
            ModelAndView model
    ) {

        Employee entity = employeeService.getByLogin(login);

        if(Objects.isNull(entity)){
            model.addObject("message", "Пользователя с таким логином в системе не найдено");
            model.setViewName("error/error_page");
            return model;
        }

        if(!entity.getPassword().equals(password)){
            model.addObject("message", "Пароль пользователя не совподают");
            model.setViewName("error/error_page");
            return model;
        }

        EmployeeDtoResponse response = EmployeeMapping.mapEntityToDtoEmployeeResponse(entity);
        model.addObject("user", response);

        if(response.getRole().equals(Role.DIRECTOR)){
            model.setViewName("thirt_floor/area_director");
        }else {
            model.setViewName("thirt_floor/area_employee");
        }

        return model;
    }

}
