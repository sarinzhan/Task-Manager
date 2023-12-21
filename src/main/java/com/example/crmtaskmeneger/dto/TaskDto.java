package com.example.crmtaskmeneger.dto;

import com.example.crmtaskmeneger.entities.Employee;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TaskDto {
    private Long id;
    private String description;
    private LocalDateTime creationDate;
    private LocalDateTime assignedDate;
    private LocalDate completionDate;
    private Employee assignedTo;
    private Employee createdBy;
    private String status;

    public TaskDto() {
    }

    public Long getId() {
        return id;
    }

    public TaskDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public TaskDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public TaskDto setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public LocalDateTime getAssignedDate() {
        return assignedDate;
    }

    public TaskDto setAssignedDate(LocalDateTime assignedDate) {
        this.assignedDate = assignedDate;
        return this;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public TaskDto setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
        return this;
    }

    public Employee getAssignedTo() {
        return assignedTo;
    }

    public TaskDto setAssignedTo(Employee assignedTo) {
        this.assignedTo = assignedTo;
        return this;
    }

    public Employee getCreatedBy() {
        return createdBy;
    }

    public TaskDto setCreatedBy(Employee createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public TaskDto setStatus(String status) {
        this.status = status;
        return this;
    }
}
