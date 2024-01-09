package com.example.crmtaskmeneger.entity.enumeric;

public enum TaskStatusExecutorUpdate {
   LEAVE_THE_EXECUTOR("Оставить исполнителя"),
    DELETE_THE_EXECUTOR("Снять исполнителя с данной задачи");

    private String description;

    TaskStatusExecutorUpdate(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
