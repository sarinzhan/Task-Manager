package com.example.crmtaskmeneger.dto.response;

public class TaskDtoResponse {

    // TODO добавить id задачи
    // добавил
    private Long id;
    private String description;
    private String creationDate;
    private String assignedDate;
    private String completionDate;
    private Long assignedTo;
    private Long createdBy;
    private String activity;
    private String status;

    public TaskDtoResponse() {
    }

    public TaskDtoResponse(Long id,String description, String creationDate, String assignedDate, String completionDate, Long assignedTo, Long createdBy, String activity) {
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
