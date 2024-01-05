package com.example.crmtaskmeneger.service.impl;

import com.example.crmtaskmeneger.entity.TaskEntity;
import com.example.crmtaskmeneger.entity.UserEntity;
import com.example.crmtaskmeneger.entity.enumeric.UserRole;
import com.example.crmtaskmeneger.service.BusinessService;
import com.example.crmtaskmeneger.service.TaskService;
import com.example.crmtaskmeneger.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BusinessServiceImpl implements BusinessService {

    private final UserService userService;
    private final TaskService taskService;

    public BusinessServiceImpl(
            UserService userService,
            TaskService taskService
    ) {
        this.userService = userService;
        this.taskService = taskService;
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

    @Override
    public List<UserEntity> getAllUsers(UserEntity authUser){
        List<UserEntity> userEntityList = userService.getAll();
        for (int i = 0; i < userEntityList.size(); i++) {
            if(!authUser.getUserRole().equals(UserRole.DIRECTOR)){
                if(userEntityList.get(i).getUserRole().equals(UserRole.DIRECTOR)){
                    userEntityList.remove(userEntityList.get(i));
                }
            }
            if(userEntityList.get(i).equals(authUser)){
                userEntityList.remove(authUser);
            }
        }
        return userEntityList;
    }

    @Override
    public List<TaskEntity> getAllTask(UserEntity auth) throws Exception {
        List<TaskEntity> allTask = taskService.getAll();
        if(Objects.isNull(allTask) || allTask.isEmpty()){
            throw new Exception("В системе нет задач");
        }

        if(auth.getUserRole().equals(UserRole.EMPLOYEE)) {
            for (int i = 0; i < allTask.size(); i++) {
                if (allTask.get(i).getExecutor() != null) {
                    allTask.remove(allTask.get(i));
                }
            }
        }
        if(allTask.isEmpty()){
            throw new Exception("Задач ожидающих выполнения нет в системе");
        }
        return allTask;
    }
}
