package com.example.crmtaskmeneger.dto;

import com.example.crmtaskmeneger.entities.Role;

public class TaskAuthorDto {
    private Long   authorId;
    private String authorName;
    private String authorPatrol;
    private String authorSerName;
    private String authorEmail;
    private String authorHireDate;
    private String authorPhone;
    private Role   authorRole;
    private String authorLogin;

    public Long getAuthorId() {
        return authorId;
    }

    public TaskAuthorDto setAuthorId(Long authorId) {
        this.authorId = authorId;
        return this;
    }

    public String getAuthorName() {
        return authorName;
    }

    public TaskAuthorDto setAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }

    public String getAuthorPatrol() {
        return authorPatrol;
    }

    public TaskAuthorDto setAuthorPatrol(String authorPatrol) {
        this.authorPatrol = authorPatrol;
        return this;
    }

    public String getAuthorSerName() {
        return authorSerName;
    }

    public TaskAuthorDto setAuthorSerName(String authorSerName) {
        this.authorSerName = authorSerName;
        return this;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public TaskAuthorDto setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
        return this;
    }

    public String getAuthorHireDate() {
        return authorHireDate;
    }

    public TaskAuthorDto setAuthorHireDate(String authorHireDate) {
        this.authorHireDate = authorHireDate;
        return this;
    }

    public String getAuthorPhone() {
        return authorPhone;
    }

    public TaskAuthorDto setAuthorPhone(String authorPhone) {
        this.authorPhone = authorPhone;
        return this;
    }

    public Role getAuthorRole() {
        return authorRole;
    }

    public TaskAuthorDto setAuthorRole(Role authorRole) {
        this.authorRole = authorRole;
        return this;
    }

    public String getAuthorLogin() {
        return authorLogin;
    }

    public TaskAuthorDto setAuthorLogin(String authorLogin) {
        this.authorLogin = authorLogin;
        return this;
    }

    @Override
    public String toString() {
        return "TaskAuthorDto{" +
                "authorId=" + authorId +
                ", authorName='" + authorName + '\'' +
                ", authorPatrol='" + authorPatrol + '\'' +
                ", authorSerName='" + authorSerName + '\'' +
                ", authorEmail='" + authorEmail + '\'' +
                ", authorHireDate='" + authorHireDate + '\'' +
                ", authorPhone='" + authorPhone + '\'' +
                ", authorRole=" + authorRole +
                ", authorLogin='" + authorLogin + '\'' +
                '}';
    }
}
