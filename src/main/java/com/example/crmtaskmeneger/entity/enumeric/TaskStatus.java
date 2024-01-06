package com.example.crmtaskmeneger.entity.enumeric;

public enum TaskStatus {
    AWAITING_CONTRACTOR("Ожидает исполнителя"),
    IN_PROGRESS("Выполняется"),
    COMPLETED ("Выполнено");

    private String description;

    TaskStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
