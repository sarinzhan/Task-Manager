package com.example.crmtaskmeneger.service;

import com.example.crmtaskmeneger.entity.TaskEntity;
import com.example.crmtaskmeneger.entity.UserEntity;
import com.example.crmtaskmeneger.entity.enumeric.UserRole;
import com.sun.istack.NotNull;

import java.time.LocalDate;
import java.util.List;

public interface UserService {

    UserEntity saveNewUser(UserEntity entity);

    List<UserEntity> getAll();

    UserEntity getUserById(Long id) throws Exception;
    UserEntity getUserByExecuteTask(TaskEntity task) throws Exception;
    UserEntity getUserByLogin(String login) throws Exception;
    List<UserEntity> getAllUsersByName(String name) throws Exception;
    List<UserEntity> getAllUsersBySerName(String serName) throws Exception;
    List<UserEntity> getAllUsersByPatronymic(String patronymic) throws Exception;
    List<UserEntity> getAllUsersByDateOfEmployment(LocalDate dateOfEmployment) throws Exception;
    List<UserEntity> getAllUsersByRole(UserRole role) throws Exception;

    UserEntity updateUserData(@NotNull Long id, String name, String serName, String Patronymic) throws Exception;
    UserEntity updateUserRole(@NotNull Long id, UserRole role) throws Exception;
    UserEntity updateUserPassword(@NotNull Long id, String passwordOld, String passwordNew) throws Exception;

    void deleteUser(Long id) throws Exception;
    void deleteUser(UserEntity entity) throws Exception;

}
