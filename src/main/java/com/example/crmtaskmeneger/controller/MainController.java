package com.example.crmtaskmeneger.controller;

import com.example.crmtaskmeneger.dto.UserDto;
import com.example.crmtaskmeneger.dto.request.EmployeeDtoRequest;
import com.example.crmtaskmeneger.dto.response.EmployeeDtoResponse;
import com.example.crmtaskmeneger.entities.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class MainController {

    @GetMapping("/")
    public String startMain(ModelAndView model){
        return "main.html";
    }

    @GetMapping("/login")
    public ModelAndView login(ModelAndView model){
        model.addObject("user", new EmployeeDtoRequest());
        model.setViewName("second_floor/login");
        return model;
    }
    @GetMapping("/register")
    public ModelAndView register(ModelAndView model){
        model.addObject("user", new EmployeeDtoRequest());
        model.setViewName("second_floor/register");
        return model;
    }

    @GetMapping("/go_back_my_area")
    public ModelAndView goBackMyArea(ModelAndView model, @ModelAttribute(name = "user")UserDto userDto){



        model.addObject("user", userDto);
        if(userDto.getUserRole().equals(Role.DIRECTOR)){
            model.setViewName("thirt_floor/area_director");
        }else {
            model.setViewName("thirt_floor/area_employee");
        }
        return model;
    }


}
