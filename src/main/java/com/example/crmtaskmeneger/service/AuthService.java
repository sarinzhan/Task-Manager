package com.example.crmtaskmeneger.service;


import com.example.crmtaskmeneger.entity.UserEntity;

import java.util.List;

public interface AuthService {

    UserEntity loginUser(String login, String password) throws Exception;
    UserEntity getUserInfo(UserEntity entity, String password) throws Exception;

}
