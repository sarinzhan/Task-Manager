package com.example.crmtaskmeneger.dto.request;

import com.example.crmtaskmeneger.entities.Employee;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * DTO tast request
 * конструктор с назначенным сотрудник и без назначенного сотрдуника
 */
public class TaskDtoRequest {
    private String description;
    //дата создания автоматически
    private LocalDate completionDate;
    private Employee assignedTo;
    private Employee createdBy;
   // private String status;


    public TaskDtoRequest() {
    }

    public TaskDtoRequest(String description, LocalDate completionDate, Employee assignedTo, Employee createdBy) {
        this.description = description;
        this.completionDate = completionDate;
        this.assignedTo = assignedTo;
        this.createdBy = createdBy;
    }

    public TaskDtoRequest(String description, LocalDate completionDate, Employee createdBy) {
        this.description = description;
        this.completionDate = completionDate;
        this.createdBy = createdBy;
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

    public Employee getCreatedBy() {
        return createdBy;
    }

    public TaskDtoRequest setCreatedBy(Employee createdBy) {
        this.createdBy = createdBy;
        return this;
    }
}
