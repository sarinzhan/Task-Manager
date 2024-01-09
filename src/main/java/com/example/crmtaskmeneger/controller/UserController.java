package com.example.crmtaskmeneger.controller;


import com.example.crmtaskmeneger.dto.UserDto;
import com.example.crmtaskmeneger.service.AuthService;
import com.example.crmtaskmeneger.service.TaskService;
import com.example.crmtaskmeneger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController extends BaseClassController {

    @Autowired
    public UserController(UserService userService, TaskService taskService, AuthService authService) {
        super(userService, taskService, authService);
    }

    @GetMapping(value = "/personal_area")
    public ModelAndView getPersonalPage(
            ModelAndView model,
            @ModelAttribute(name = "userDto")UserDto userDto
    ){
        try {
            userDto = refreshUser(userDto);
        } catch (Exception e) {
            model.addObject("message", e.getMessage());
            model.setViewName("error/error_page.html");
            return model;
        }

        model.addObject("userDto", userDto);
        model.setViewName("personal_space/personal_space.html");

        return model;
    }
}
