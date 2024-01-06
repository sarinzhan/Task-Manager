package com.example.crmtaskmeneger.controller;


import com.example.crmtaskmeneger.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @GetMapping(value = "/personal_area")
    public ModelAndView getPersonalPage(
            ModelAndView model,
            @ModelAttribute(name = "userDto")UserDto userDto
    ){

        model.addObject("userDto", userDto);
        model.setViewName("personal_space/personal_space.html");

        return model;
    }
}
