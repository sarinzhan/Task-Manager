package com.example.crmtaskmeneger.dto;

import com.example.crmtaskmeneger.entity.enumeric.UserRole;

import java.time.LocalDate;

public class UserDtoRequest {
    private String requestLogin;
    private String requestPassword;
    private String requestName;
    private String requestSerName;
    private String requestPatronymic;
    private UserRole requestRole;
    private LocalDate requestDateEmployment;

    public String getRequestLogin() {
        return requestLogin;
    }

    public UserDtoRequest setRequestLogin(String requestLogin) {
        this.requestLogin = requestLogin;
        return this;
    }

    public String getRequestPassword() {
        return requestPassword;
    }

    public UserDtoRequest setRequestPassword(String requestPassword) {
        this.requestPassword = requestPassword;
        return this;
    }

    public String getRequestName() {
        return requestName;
    }

    public UserDtoRequest setRequestName(String requestName) {
        this.requestName = requestName;
        return this;
    }

    public String getRequestSerName() {
        return requestSerName;
    }

    public UserDtoRequest setRequestSerName(String requestSerName) {
        this.requestSerName = requestSerName;
        return this;
    }

    public String getRequestPatronymic() {
        return requestPatronymic;
    }

    public UserDtoRequest setRequestPatronymic(String requestPatronymic) {
        this.requestPatronymic = requestPatronymic;
        return this;
    }

    public UserRole getRequestRole() {
        return requestRole;
    }

    public UserDtoRequest setRequestRole(UserRole requestRole) {
        this.requestRole = requestRole;
        return this;
    }

    public LocalDate getRequestDateEmployment() {
        return requestDateEmployment;
    }

    public UserDtoRequest setRequestDateEmployment(LocalDate requestDateEmployment) {
        this.requestDateEmployment = requestDateEmployment;
        return this;
    }

    @Override
    public String toString() {
        return "UserDtoRequest{" +
                "requestLogin='" + requestLogin + '\'' +
                ", requestPassword='" + requestPassword + '\'' +
                ", requestName='" + requestName + '\'' +
                ", requestSerName='" + requestSerName + '\'' +
                ", requestPatronymic='" + requestPatronymic + '\'' +
                ", requestRole=" + requestRole +
                '}';
    }
}
