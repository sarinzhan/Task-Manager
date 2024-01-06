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


}
