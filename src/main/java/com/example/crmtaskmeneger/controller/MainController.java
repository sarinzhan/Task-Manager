package com.example.crmtaskmeneger.controller;

import com.example.crmtaskmeneger.dto.request.EmployeeDtoRequest;
import com.example.crmtaskmeneger.dto.response.EmployeeDtoResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class MainController {

    @GetMapping("/")
    public String startMain(Model model){
        return "main.html";
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("employee_dto_request", new EmployeeDtoRequest());
        return "second_floor/login.html";
    }
    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("employee_dto_request", new EmployeeDtoRequest());
        return "second_floor/register.html";
    }

    @GetMapping("/all_users")
    public String getAllUsers(Model model){
        Random ran = new Random();
        List<EmployeeDtoResponse> dtoResponseList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            EmployeeDtoResponse dto = new EmployeeDtoResponse();
            dto.setEmail(i + "_email@mail.ru");
            dto.setLogin("login_" + i);
            dto.setFirstName("first_name_"+ i);
            dto.setPassword("password_"+i);
            dto.setRole(ran.nextBoolean()?"employee":"director");
            dto.setLastName("last_name_"+i);
            dto.setHireDate(LocalDate.now());
            dto.setMiddleName("middle_name_"+ i);
            dto.setPhoneNum("" + ran.nextInt(996999999 - 996000000 + 1 ) + 996000000);
            dtoResponseList.add(dto);
        }
        model.addAttribute("all_dtos", dtoResponseList);
        return "second_floor/all_users.html";
    }

}
