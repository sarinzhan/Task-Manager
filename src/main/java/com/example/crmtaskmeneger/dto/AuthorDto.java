package com.example.crmtaskmeneger.dto;

import com.example.crmtaskmeneger.entity.enumeric.UserRole;

public class AuthorDto {
    private Long     authorId;
    private String   authorLogin;
    private String   authorName;
    private String   authorSerName;
    private String   authorPatronymic;
    private String   authorDateOfEmployment;
    private UserRole authorRole;

    public Long getAuthorId() {
        return authorId;
    }

    public AuthorDto setAuthorId(Long authorId) {
        this.authorId = authorId;
        return this;
    }

    public String getAuthorLogin() {
        return authorLogin;
    }

    public AuthorDto setAuthorLogin(String authorLogin) {
        this.authorLogin = authorLogin;
        return this;
    }

    public String getAuthorName() {
        return authorName;
    }

    public AuthorDto setAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }

    public String getAuthorSerName() {
        return authorSerName;
    }

    public AuthorDto setAuthorSerName(String authorSerName) {
        this.authorSerName = authorSerName;
        return this;
    }

    public String getAuthorPatronymic() {
        return authorPatronymic;
    }

    public AuthorDto setAuthorPatronymic(String authorPatronymic) {
        this.authorPatronymic = authorPatronymic;
        return this;
    }

    public String getAuthorDateOfEmployment() {
        return authorDateOfEmployment;
    }

    public AuthorDto setAuthorDateOfEmployment(String authorDateOfEmployment) {
        this.authorDateOfEmployment = authorDateOfEmployment;
        return this;
    }

    public UserRole getAuthorRole() {
        return authorRole;
    }

    public AuthorDto setAuthorRole(UserRole authorRole) {
        this.authorRole = authorRole;
        return this;
    }


    @Override
    public String toString() {
        return "AuthorDto{" +
                "authorId=" + authorId +
                ", authorLogin='" + authorLogin + '\'' +
                ", authorName='" + authorName + '\'' +
                ", authorSerName='" + authorSerName + '\'' +
                ", authorPatronymic='" + authorPatronymic + '\'' +
                ", authorDateOfEmployment='" + authorDateOfEmployment + '\'' +
                ", authorRole=" + authorRole +
                '}';
    }
}
