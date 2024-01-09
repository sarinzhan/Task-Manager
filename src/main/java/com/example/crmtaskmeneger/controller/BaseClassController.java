package com.example.crmtaskmeneger.controller;

import com.example.crmtaskmeneger.dto.UserDto;
import com.example.crmtaskmeneger.service.AuthService;
import com.example.crmtaskmeneger.service.TaskService;
import com.example.crmtaskmeneger.service.UserService;
import com.example.crmtaskmeneger.util.OutInfoPointExcecud;
import com.example.crmtaskmeneger.util.UserMapper;
import org.springframework.stereotype.Controller;

@Controller
public abstract class BaseClassController implements OutInfoPointExcecud {
    protected final UserService userService;
    protected final TaskService taskService;
    protected final AuthService authService;

    protected BaseClassController(
            UserService userService,
            TaskService taskService,
            AuthService authService) {
        this.userService = userService;
        this.taskService = taskService;
        this.authService = authService;
    }

    protected UserDto refreshUser(UserDto userDto) throws Exception {
        try {
            userDto = UserMapper.mapEntityToUserDto(userService.getUserById(userDto.getUserId()));
        } catch (Exception e) {
            throw  new Exception("Что то пошло не так при обновлении авторизированного пользователя");
        }
        return userDto;
    }

}
