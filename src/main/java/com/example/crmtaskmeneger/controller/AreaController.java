package com.example.crmtaskmeneger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AreaController {

    @GetMapping(name = "/area_empl")
    public String areaEmployee(Model model){

        return "thirt_floor/area_employee.html";

    }
}
