package com.example.crmtaskmeneger.dto.response;

import com.example.crmtaskmeneger.entities.Employee;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TaskDtoResponse {
    private String description;
    private LocalDateTime creationDate;
    private LocalDateTime assignedDate;
    private LocalDate completionDate;
    private Employee assignedTo;
    private Employee createdBy;
    private String status;

    public TaskDtoResponse() {
    }

    public TaskDtoResponse(String description, LocalDateTime creationDate, LocalDateTime assignedDate, LocalDate completionDate, Employee assignedTo, Employee createdBy, String status) {
        this.description = description;
        this.creationDate = creationDate;
        this.assignedDate = assignedDate;
        this.completionDate = completionDate;
        this.assignedTo = assignedTo;
        this.createdBy = createdBy;
        this.status = status;
    }

    public TaskDtoResponse(String description, LocalDateTime creationDate, LocalDate completionDate, Employee createdBy) {
        this.description = description;
        this.creationDate = creationDate;
        this.completionDate = completionDate;
        this.createdBy = createdBy;
    }

    public String getDescription() {
        return description;
    }

    public TaskDtoResponse setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public TaskDtoResponse setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public LocalDateTime getAssignedDate() {
        return assignedDate;
    }

    public TaskDtoResponse setAssignedDate(LocalDateTime assignedDate) {
        this.assignedDate = assignedDate;
        return this;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public TaskDtoResponse setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
        return this;
    }

    public Employee getAssignedTo() {
        return assignedTo;
    }

    public TaskDtoResponse setAssignedTo(Employee assignedTo) {
        this.assignedTo = assignedTo;
        return this;
    }

    public Employee getCreatedBy() {
        return createdBy;
    }

    public TaskDtoResponse setCreatedBy(Employee createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public TaskDtoResponse setStatus(String status) {
        this.status = status;
        return this;
    }
}
