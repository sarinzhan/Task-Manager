package com.example.crmtaskmeneger.controller;

import com.example.crmtaskmeneger.dto.UserDto;
import com.example.crmtaskmeneger.dto.UserDtoRequest;
import com.example.crmtaskmeneger.entity.UserEntity;
import com.example.crmtaskmeneger.entity.enumeric.UserRole;
import com.example.crmtaskmeneger.service.AuthService;
import com.example.crmtaskmeneger.service.UserService;
import com.example.crmtaskmeneger.util.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

@Controller
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @Autowired
    public AuthController(
            AuthService authService,
            UserService userService
    ) {
        this.authService = authService;
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public ModelAndView getMain(ModelAndView model){
        model.setViewName("main_page.html");
        return model;
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

    @GetMapping(value = "/personal_space")
    public ModelAndView transitionToPersonalSpace(
            ModelAndView model,
            @ModelAttribute(name = "userDto")UserDto userDto
    ){
        try {




        } catch (Exception e) {
            model.addObject("message", e.getMessage());
            model.setViewName("error/error_page.html");
            return model;
        }
        model.addObject("userDto", userDto);
        if(userDto.getUserRole().equals(UserRole.DIRECTOR)){
            model.setViewName("personal_space/personal_space.html");
        }else {
            model.setViewName("personal_space/personal_space.html");
        }
        return model;
    }

    @GetMapping(value = "/new_user")
    public ModelAndView registerNewUser(
            ModelAndView model
            ){


       model.setViewName("users_page/new_user_or_employee.html");

        return model;
    }
    @PostMapping(value = "/new_user")
    public ModelAndView registerNewUserAction(
            ModelAndView model,
            @ModelAttribute UserDtoRequest userDtoRequest,
            @ModelAttribute(name = "equalsPassword") String equalsPassword
    ){

        if(!userDtoRequest.getRequestPassword().equals(equalsPassword)){
            try {
                throw new Exception("Пароли не совпадают повторите попытку");
            } catch (Exception e) {
                model.addObject("message", e.getMessage());
                model.setViewName("error/error_page.html");
                return model;
            }
        }
        UserEntity user = UserMapper.mapDtoToEntity(userDtoRequest);
        user.setDateOfEmployment(LocalDate.now())
                        .setRole(UserRole.EMPLOYEE);
        user = userService.saveNewUser(user);
        UserDto userDto = UserMapper.mapEntityToUserDto(user);
        model.addObject("userDto", userDto);

        model.setViewName("personal_space/personal_space.html");

        return model;
    }
}
