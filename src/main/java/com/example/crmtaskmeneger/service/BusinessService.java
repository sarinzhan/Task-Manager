package com.example.crmtaskmeneger.service;

import com.example.crmtaskmeneger.entity.TaskEntity;
import com.example.crmtaskmeneger.entity.UserEntity;

import java.util.List;

public interface BusinessService {

    UserEntity loginUser(String login, String password) throws Exception;


    UserEntity getUserInfo(UserEntity entity, String password) throws Exception;

    List<UserEntity> getAllUsers(UserEntity authUser);

    List<TaskEntity> getAllTask(UserEntity auth) throws Exception;
}
