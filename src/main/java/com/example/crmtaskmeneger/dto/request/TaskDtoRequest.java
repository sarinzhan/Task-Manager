package com.example.crmtaskmeneger.dto.request;

import com.example.crmtaskmeneger.entities.Employee;

import java.time.LocalDate;

/**
 * если есть на кого назначен то дата устанавливается через localdate.now
 * если нету то нету
 */


public class TaskDtoRequest {
    private String description;
    private String completionDate;
    private String assignedTo;

    public TaskDtoRequest(String description, String completionDate, String assignedTo) {
        this.description = description;
        this.completionDate = completionDate;
        this.assignedTo = assignedTo;
    }

    public TaskDtoRequest() {
    }

    public String getDescription() {
        return description;
    }

    public TaskDtoRequest setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getCompletionDate() {
        return completionDate;
    }

    public TaskDtoRequest setCompletionDate(String completionDate) {
        this.completionDate = completionDate;
        return this;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public TaskDtoRequest setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
        return this;
    }
}
