package com.example.crmtaskmeneger.dto;

import com.example.crmtaskmeneger.entities.Role;

public class UserDto {
    private Long userId;
    private String userName;
    private String userPatrol;
    private String userSerName;
    private String userEmail;
    private String userHireDate;
    private String userPhone;
    private Role userRole;
    private String userLogin;

    public Long getUserId() {
        return userId;
    }

    public UserDto setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public UserDto setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getUserPatrol() {
        return userPatrol;
    }

    public UserDto setUserPatrol(String userPatrol) {
        this.userPatrol = userPatrol;
        return this;
    }

    public String getUserSerName() {
        return userSerName;
    }

    public UserDto setUserSerName(String userSerName) {
        this.userSerName = userSerName;
        return this;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public UserDto setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }

    public String getUserHireDate() {
        return userHireDate;
    }

    public UserDto setUserHireDate(String userHireDate) {
        this.userHireDate = userHireDate;
        return this;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public UserDto setUserPhone(String userPhone) {
        this.userPhone = userPhone;
        return this;
    }

    public Role getUserRole() {
        return userRole;
    }

    public UserDto setUserRole(Role userRole) {
        this.userRole = userRole;
        return this;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public UserDto setUserLogin(String userLogin) {
        this.userLogin = userLogin;
        return this;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPatrol='" + userPatrol + '\'' +
                ", userSerName='" + userSerName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userHireDate='" + userHireDate + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userRole=" + userRole +
                ", userLogin='" + userLogin + '\'' +
                '}';
    }
}
