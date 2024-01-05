package com.example.crmtaskmeneger.entities;

public enum Role {
    DIRECTOR("Директор"),
    EMPLOYEE("Сотрудник");

    private String description;

    Role(String description) {
        this.description = description;
    }

   public String getDescription(){
        return description;
   }
}
