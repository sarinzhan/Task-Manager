package com.example.crmtaskmeneger.dto.response;

public class TaskDtoResponse {

    // TODO добавить id задачи
    private String description;
    private String creationDate;
    private String assignedDate;
    private String completionDate;
    private Long assignedTo;
    private Long createdBy;
    private String activity;

    public TaskDtoResponse() {
    }

    public TaskDtoResponse(String description, String creationDate, String assignedDate, String completionDate, Long assignedTo, Long createdBy, String activity) {
        this.description = description;
        this.creationDate = creationDate;
        this.assignedDate = assignedDate;
        this.completionDate = completionDate;
        this.assignedTo = assignedTo;
        this.createdBy = createdBy;
        this.activity = activity;
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

    public Long getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Long assignedTo) {
        this.assignedTo = assignedTo;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
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
                ", completionDate='" + completionDate + '\'' +
                ", assignedTo=" + assignedTo +
                ", createdBy=" + createdBy +
                ", status='" + activity + '\'' +
                '}';
    }
}
