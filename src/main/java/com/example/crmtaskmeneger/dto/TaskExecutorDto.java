package com.example.crmtaskmeneger.dto;

import com.example.crmtaskmeneger.entities.Role;

public class TaskExecutorDto {
    private Long   executorId;
    private String executorName;
    private String executorPatrol;
    private String executorSerName;
    private String executorEmail;
    private String executorHireDate;
    private String executorPhone;
    private Role executorRole;
    private String executorLogin;

    public Long getExecutorId() {
        return executorId;
    }

    public TaskExecutorDto setExecutorId(Long executorId) {
        this.executorId = executorId;
        return this;
    }

    public String getExecutorName() {
        return executorName;
    }

    public TaskExecutorDto setExecutorName(String executorName) {
        this.executorName = executorName;
        return this;
    }

    public String getExecutorPatrol() {
        return executorPatrol;
    }

    public TaskExecutorDto setExecutorPatrol(String executorPatrol) {
        this.executorPatrol = executorPatrol;
        return this;
    }

    public String getExecutorSerName() {
        return executorSerName;
    }

    public TaskExecutorDto setExecutorSerName(String executorSerName) {
        this.executorSerName = executorSerName;
        return this;
    }

    public String getExecutorEmail() {
        return executorEmail;
    }

    public TaskExecutorDto setExecutorEmail(String executorEmail) {
        this.executorEmail = executorEmail;
        return this;
    }

    public String getExecutorHireDate() {
        return executorHireDate;
    }

    public TaskExecutorDto setExecutorHireDate(String executorHireDate) {
        this.executorHireDate = executorHireDate;
        return this;
    }

    public String getExecutorPhone() {
        return executorPhone;
    }

    public TaskExecutorDto setExecutorPhone(String executorPhone) {
        this.executorPhone = executorPhone;
        return this;
    }

    public Role getExecutorRole() {
        return executorRole;
    }

    public TaskExecutorDto setExecutorRole(Role executorRole) {
        this.executorRole = executorRole;
        return this;
    }

    public String getExecutorLogin() {
        return executorLogin;
    }

    public TaskExecutorDto setExecutorLogin(String executorLogin) {
        this.executorLogin = executorLogin;
        return this;
    }

    @Override
    public String toString() {
        return "TaskExecutorDto{" +
                "executorId=" + executorId +
                ", executorName='" + executorName + '\'' +
                ", executorPatrol='" + executorPatrol + '\'' +
                ", executorSerName='" + executorSerName + '\'' +
                ", executorEmail='" + executorEmail + '\'' +
                ", executorHireDate='" + executorHireDate + '\'' +
                ", executorPhone='" + executorPhone + '\'' +
                ", executorRole=" + executorRole +
                ", executorLogin='" + executorLogin + '\'' +
                '}';
    }
}
