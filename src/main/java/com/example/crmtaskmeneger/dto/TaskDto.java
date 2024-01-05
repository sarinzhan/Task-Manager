package com.example.crmtaskmeneger.dto;

import com.example.crmtaskmeneger.entities.Employee;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TaskDto {
    private Long id;
    private String description;
    private String creationDate;
    private String assignedDate;
    private String completionDate;
    private Long assignedTo;
    private Long createdBy;
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

    public String getCreationDate() {
        return creationDate;
    }

    public TaskDto setCreationDate(String creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public String getAssignedDate() {
        return assignedDate;
    }

    public TaskDto setAssignedDate(String assignedDate) {
        this.assignedDate = assignedDate;
        return this;
    }

    public String getCompletionDate() {
        return completionDate;
    }

    public TaskDto setCompletionDate(String completionDate) {
        this.completionDate = completionDate;
        return this;
    }

    public Long getAssignedTo() {
        return assignedTo;
    }

    public TaskDto setAssignedTo(Long assignedTo) {
        this.assignedTo = assignedTo;
        return this;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public TaskDto setCreatedBy(Long createdBy) {
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
