package com.example.crmtaskmeneger.dto;

import com.example.crmtaskmeneger.entity.enumeric.UserRole;

public class UserDto {
    private Long userId;
    private String userLogin;
    private String userName;
    private String userSerName;
    private String userPatronymic;
    private String userDateOfEmployment;
    private UserRole userRole;
    private TaskDto userExecutedTask;
    public Long getUserId() {
        return userId;
    }

    public UserDto setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public UserDto setUserLogin(String userLogin) {
        this.userLogin = userLogin;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public UserDto setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getUserSerName() {
        return userSerName;
    }

    public UserDto setUserSerName(String userSerName) {
        this.userSerName = userSerName;
        return this;
    }

    public String getUserPatronymic() {
        return userPatronymic;
    }

    public UserDto setUserPatronymic(String userPatronymic) {
        this.userPatronymic = userPatronymic;
        return this;
    }

    public String getUserDateOfEmployment() {
        return userDateOfEmployment;
    }

    public UserDto setUserDateOfEmployment(String userDateOfEmployment) {
        this.userDateOfEmployment = userDateOfEmployment;
        return this;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public UserDto setUserRole(UserRole userRole) {
        this.userRole = userRole;
        return this;
    }

    public TaskDto getUserExecutedTask() {
        return userExecutedTask;
    }

    public UserDto setUserExecutedTask(TaskDto userExecutedTask) {
        this.userExecutedTask = userExecutedTask;
        return this;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "userId=" + userId +
                ", userLogin='" + userLogin + '\'' +
                ", userName='" + userName + '\'' +
                ", userSerName='" + userSerName + '\'' +
                ", userPatronymic='" + userPatronymic + '\'' +
                ", userDateOfEmployment='" + userDateOfEmployment + '\'' +
                ", userRole=" + userRole +
                ", userExecutedTask=" + userExecutedTask +
                '}';
    }
}
