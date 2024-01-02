package com.example.crmtaskmeneger.controller;

import com.example.crmtaskmeneger.dto.request.EmployeeDtoRequest;
import com.example.crmtaskmeneger.dto.response.EmployeeDtoResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AreaController {

    @GetMapping(name = "/area_employee")
    public ModelAndView areaEmployee(ModelAndView model, @ModelAttribute("user")EmployeeDtoResponse response){
        System.out.println("Зашли в личный кабинет: com.example.crmtaskmeneger.controller.AreaController");
        System.out.println(response);
        return model;

    }
}
