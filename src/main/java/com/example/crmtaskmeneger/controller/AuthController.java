package com.example.crmtaskmeneger.controller;

import com.example.crmtaskmeneger.dto.request.EmployeeDtoRequest;
import com.example.crmtaskmeneger.mapping.EmployeeMapping;
import org.hibernate.boot.model.source.spi.EmbeddableMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @PostMapping("/login")
    public String login(Model model, @ModelAttribute(name = "employee_dto_request")EmployeeDtoRequest dtoRequest ) {

        return "redirect:/";
    }
    @PostMapping("/register")
    public String register(Model model, @ModelAttribute(name = "employee_dto_request") EmployeeDtoRequest dtoRequest ) {

        System.out.println(EmployeeMapping.mapModelDtoToEntity(dtoRequest));
        return "redirect:/";
    }

}
