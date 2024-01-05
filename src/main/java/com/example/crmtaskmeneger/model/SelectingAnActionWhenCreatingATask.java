package com.example.crmtaskmeneger.model;

public enum SelectingAnActionWhenCreatingATask {
    SELECT_FREE_TASK("Свободная задача"),
    SELECT_EMPLOYEE("Выбрать Сотрудника");

    private String description;

    SelectingAnActionWhenCreatingATask(String description) {
        this.description = description;
    }

    public String getDescription(){
        return description;
    }
}
