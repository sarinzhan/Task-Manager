package com.example.crmtaskmeneger.dto;

import com.example.crmtaskmeneger.entity.enumeric.UserRole;

public class ExecutorDto {
    private Long     executorId;
    private String   executorLogin;
    private String   executorName;
    private String   executorSerName;
    private String   executorPatronymic;
    private String   executorDateOfEmployment;
    private UserRole executorRole;


    public Long getExecutorId() {
        return executorId;
    }

    public ExecutorDto setExecutorId(Long executorId) {
        this.executorId = executorId;
        return this;
    }

    public String getExecutorLogin() {
        return executorLogin;
    }

    public ExecutorDto setExecutorLogin(String executorLogin) {
        this.executorLogin = executorLogin;
        return this;
    }

    public String getExecutorName() {
        return executorName;
    }

    public ExecutorDto setExecutorName(String executorName) {
        this.executorName = executorName;
        return this;
    }

    public String getExecutorSerName() {
        return executorSerName;
    }

    public ExecutorDto setExecutorSerName(String executorSerName) {
        this.executorSerName = executorSerName;
        return this;
    }

    public String getExecutorPatronymic() {
        return executorPatronymic;
    }

    public ExecutorDto setExecutorPatronymic(String executorPatronymic) {
        this.executorPatronymic = executorPatronymic;
        return this;
    }

    public String getExecutorDateOfEmployment() {
        return executorDateOfEmployment;
    }

    public ExecutorDto setExecutorDateOfEmployment(String executorDateOfEmployment) {
        this.executorDateOfEmployment = executorDateOfEmployment;
        return this;
    }

    public UserRole getExecutorRole() {
        return executorRole;
    }

    public ExecutorDto setExecutorRole(UserRole executorRole) {
        this.executorRole = executorRole;
        return this;
    }

    @Override
    public String toString() {
        return "ExecutorDto{" +
                "executorId=" + executorId +
                ", executorLogin='" + executorLogin + '\'' +
                ", executorName='" + executorName + '\'' +
                ", executorSerName='" + executorSerName + '\'' +
                ", executorPatronymic='" + executorPatronymic + '\'' +
                ", executorDateOfEmployment='" + executorDateOfEmployment + '\'' +
                ", executorRole=" + executorRole +
                '}';
    }
}
