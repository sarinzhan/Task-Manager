package com.example.crmtaskmeneger.controller;

import com.example.crmtaskmeneger.dto.UserDto;
import com.example.crmtaskmeneger.entity.enumeric.UserRole;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @GetMapping(value = "/")
    public ModelAndView getMain(ModelAndView model){
        model.setViewName("main_page.html");
        return model;
    }

    @GetMapping(value = "/personal_space")
    public ModelAndView transitionToPersonalSpace(
            ModelAndView model,
            @ModelAttribute(name = "userDto")UserDto userDto
            ){

        System.out.println(userDto);
        try {

            // your code


        } catch (Exception e) {
            model.addObject("message", e.getMessage());
            model.setViewName("error/error_page.html");
            return model;
        }
        model.addObject("userDto", userDto);
        if(userDto.getUserRole().equals(UserRole.DIRECTOR)){
            model.setViewName("personal_space/personal_space.html");
        }else {
            model.setViewName("personal_space/employee_personal_space.html");
        }
        return model;
    }
}
