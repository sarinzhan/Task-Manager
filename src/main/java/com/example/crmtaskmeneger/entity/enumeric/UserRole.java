package com.example.crmtaskmeneger.entity.enumeric;

public enum UserRole {

    EMPLOYEE("Сотрудник"),
    DIRECTOR("Директор");

    private String description;


    UserRole(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
