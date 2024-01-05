package com.example.crmtaskmeneger.dto.response;

import com.example.crmtaskmeneger.dto.TaskAuthorDto;
import com.example.crmtaskmeneger.dto.TaskExecutorDto;

public class TaskDtoResponse {

    // TODO добавить id задачи
    // добавил
    private Long id;
    private String description;
    private String creationDate;
    private String assignedDate;
    private String completionDate;
    private TaskExecutorDto assignedTo;
    private TaskAuthorDto createdBy;
    private String activity;
    private String status;

    public TaskDtoResponse() {
    }

    public TaskDtoResponse(Long id,String description, String creationDate, String assignedDate, String completionDate, TaskExecutorDto assignedTo, TaskAuthorDto createdBy, String activity) {
        this.description = description;
        this.creationDate = creationDate;
        this.assignedDate = assignedDate;
        this.completionDate = completionDate;
        this.assignedTo = assignedTo;
        this.createdBy = createdBy;
        this.activity = activity;
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public TaskDtoResponse setStatus(String status) {
        this.status = status;
        return this;
    }

    public Long getId() {
        return id;
    }

    public TaskDtoResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public TaskDtoResponse setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(String assignedDate) {
        this.assignedDate = assignedDate;
    }

    public String getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(String completionDate) {
        this.completionDate = completionDate;
    }

    public TaskExecutorDto getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(TaskExecutorDto assignedTo) {
        this.assignedTo = assignedTo;
    }

    public TaskAuthorDto getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(TaskAuthorDto createdBy) {
        this.createdBy = createdBy;
    }

    public String getActivity() {
        return activity;
    }

    public TaskDtoResponse setActivity(String activity) {
        this.activity = activity;
        return this;
    }

    @Override
    public String toString() {
        return "TaskDtoResponse{" +
                "description='" + description + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", assignedDate='" + assignedDate + '\'' +
                ", completionDate='" + completionDate + '\'' + "\n"+
                ", assignedTo=" + assignedTo + "\n"+
                ", createdBy=" + createdBy + "\n"+
                ", status='" + activity + '\'' +
                '}';
    }
}
