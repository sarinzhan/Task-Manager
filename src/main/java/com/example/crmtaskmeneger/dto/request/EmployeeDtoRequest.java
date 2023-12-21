package com.example.crmtaskmeneger.dto.request;

import java.time.LocalDate;

public class EmployeeDtoRequest {
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private LocalDate hireDate;
    private String phoneNum;
    private String password;
    private String role;
    private String login;

    public EmployeeDtoRequest() {
    }

    public EmployeeDtoRequest(String firstName, String middleName, String lastName, String role) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.role = role;
    }

    public EmployeeDtoRequest(String firstName, String middleName, String lastName, String email, LocalDate hireDate, String phoneNum, String role, String login) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.hireDate = hireDate;
        this.phoneNum = phoneNum;
        this.role = role;
        this.login = login;
    }

    public EmployeeDtoRequest(String firstName, String middleName, String lastName, String email, LocalDate hireDate, String phoneNum, String password, String role, String login) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.hireDate = hireDate;
        this.phoneNum = phoneNum;
        this.password = password;
        this.role = role;
        this.login = login;
    }
}
