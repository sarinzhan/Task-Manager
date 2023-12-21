package com.example.crmtaskmeneger.dto.request;

import com.example.crmtaskmeneger.entities.Employee;

import java.time.LocalDate;

/**
 * DTO tast request
 * конструктор с назначенным сотрудник и без назначенного сотрдуника
 */
public class TaskDtoRequest {
    private String description;
    private LocalDate completionDate;
    private Employee assignedTo;


    public TaskDtoRequest() {
    }

    public TaskDtoRequest(String description, LocalDate completionDate, Employee assignedTo, Employee createdBy, String status) {
        this.description = description;
        this.completionDate = completionDate;
        this.assignedTo = assignedTo;
    }

    public TaskDtoRequest(String description, LocalDate completionDate) {
        this.description = description;
        this.completionDate = completionDate;
    }

    public String getDescription() {
        return description;
    }

    public TaskDtoRequest setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public TaskDtoRequest setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
        return this;
    }

    public Employee getAssignedTo() {
        return assignedTo;
    }

    public TaskDtoRequest setAssignedTo(Employee assignedTo) {
        this.assignedTo = assignedTo;
        return this;
    }
}
