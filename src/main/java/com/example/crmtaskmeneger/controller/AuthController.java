package com.example.crmtaskmeneger.controller;

import com.example.crmtaskmeneger.dto.request.EmployeeDtoRequest;
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
        System.out.println("Зашол сюда");
        System.out.println(dtoRequest);
        return "main.html";
    }

}
