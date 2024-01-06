package com.example.crmtaskmeneger.service.impl;



import com.example.crmtaskmeneger.entity.UserEntity;
import com.example.crmtaskmeneger.service.AuthService;
import com.example.crmtaskmeneger.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserService userService;

    public AuthServiceImpl(
            UserService userService
    ) {
        this.userService = userService;
    }

    @Override
    public UserEntity loginUser(String login, String password) throws Exception {
        UserEntity entity = null;
        if(Objects.nonNull(login) && !login.isEmpty()){
            entity = userService.getUserByLogin(login);
        }
        if(Objects.isNull(entity)){
            throw new Exception("Такого пользователь не зарегистрирован в системе");
        }
        if(!entity.getPassword().equals(password)){
            throw new Exception("Неверный пароль пользователя");
        }
        return entity;
    }
    @Override
    public UserEntity getUserInfo(UserEntity entity, String password) throws Exception {
        if(entity.getPassword().equals(password)){
            throw new Exception("Отказано в доступе");
        }
        return entity;
    }




}
