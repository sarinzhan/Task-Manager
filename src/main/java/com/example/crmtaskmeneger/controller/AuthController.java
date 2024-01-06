package com.example.crmtaskmeneger.controller;

import com.example.crmtaskmeneger.dto.UserDto;
import com.example.crmtaskmeneger.entity.UserEntity;
import com.example.crmtaskmeneger.service.AuthService;
import com.example.crmtaskmeneger.util.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping(value = "/login")
    public ModelAndView login(ModelAndView model){
        model.setViewName("enter/login_page.html");
        return model;
    }
    @PostMapping(value = "/login")
    public ModelAndView login(
            ModelAndView model,
            @ModelAttribute(name = "login") String login,
            @ModelAttribute(name = "password") String password
    ){
        UserDto userDto;
        try {
            UserEntity entity = authService.loginUser(login,password);
           userDto = UserMapper.mapEntityToUserDto(entity);
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
