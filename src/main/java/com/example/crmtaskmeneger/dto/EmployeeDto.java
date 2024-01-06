package com.example.crmtaskmeneger.dto;

import com.example.crmtaskmeneger.entity.enumeric.UserRole;

public class EmployeeDto {
    private Long     employeeId;
    private String   employeeLogin;
    private String   employeeName;
    private String   employeeSerName;
    private String   employeePatronymic;
    private String   employeeDateOfEmployment;
    private UserRole employeeRole;
    private TaskDto taskPerformsEmployee;

    public Long getEmployeeId() {
        return employeeId;
    }

    public EmployeeDto setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    public String getEmployeeLogin() {
        return employeeLogin;
    }

    public EmployeeDto setEmployeeLogin(String employeeLogin) {
        this.employeeLogin = employeeLogin;
        return this;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public EmployeeDto setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
        return this;
    }

    public String getEmployeeSerName() {
        return employeeSerName;
    }

    public EmployeeDto setEmployeeSerName(String employeeSerName) {
        this.employeeSerName = employeeSerName;
        return this;
    }

    public String getEmployeePatronymic() {
        return employeePatronymic;
    }

    public EmployeeDto setEmployeePatronymic(String employeePatronymic) {
        this.employeePatronymic = employeePatronymic;
        return this;
    }

    public String getEmployeeDateOfEmployment() {
        return employeeDateOfEmployment;
    }

    public EmployeeDto setEmployeeDateOfEmployment(String employeeDateOfEmployment) {
        this.employeeDateOfEmployment = employeeDateOfEmployment;
        return this;
    }

    public UserRole getEmployeeRole() {
        return employeeRole;
    }

    public EmployeeDto setEmployeeRole(UserRole employeeRole) {
        this.employeeRole = employeeRole;
        return this;
    }

    public TaskDto getTaskPerformsEmployee() {
        return taskPerformsEmployee;
    }

    public EmployeeDto setTaskPerformsEmployee(TaskDto taskPerformsEmployee) {
        this.taskPerformsEmployee = taskPerformsEmployee;
        return this;
    }
}
