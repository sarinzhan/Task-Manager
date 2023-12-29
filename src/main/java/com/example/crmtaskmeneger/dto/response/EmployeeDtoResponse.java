package com.example.crmtaskmeneger.dto.response;

import java.time.LocalDate;

public class EmployeeDtoResponse {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private LocalDate hireDate;
    private String phoneNum;
    private String password; // Зачем тут нужен пароль Мы же эту ДТО отдаем?
    private String role;
    private String login;

    public EmployeeDtoResponse() {
    }

    public EmployeeDtoResponse(String firstName, String middleName, String lastName, String role) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.role = role;
    }

    public EmployeeDtoResponse(String firstName, String middleName, String lastName, String email, LocalDate hireDate, String phoneNum, String role, String login) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.hireDate = hireDate;
        this.phoneNum = phoneNum;
        this.role = role;
        this.login = login;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public EmployeeDtoResponse setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getMiddleName() {
        return middleName;
    }

    public EmployeeDtoResponse setMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public EmployeeDtoResponse setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public EmployeeDtoResponse setEmail(String email) {
        this.email = email;
        return this;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public EmployeeDtoResponse setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
        return this;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public EmployeeDtoResponse setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public EmployeeDtoResponse setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getRole() {
        return role;
    }

    public EmployeeDtoResponse setRole(String role) {
        this.role = role;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public EmployeeDtoResponse setLogin(String login) {
        this.login = login;
        return this;
    }
}
