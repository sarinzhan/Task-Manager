package com.example.crmtaskmeneger.dto;

import com.example.crmtaskmeneger.entity.enumeric.TaskStatus;

public class TaskDto {
    private Long taskId;
    private String taskThem;
    private String taskDescription;
    private String taskDataCreate;
    private String taskDataCompletion;
    private String taskStartTime;
    private TaskStatus taskStatus;
    private AuthorDto taskAuthor;
    private ExecutorDto taskExecutor;

    public Long getTaskId() {
        return taskId;
    }

    public TaskDto setTaskId(Long taskId) {
        this.taskId = taskId;
        return this;
    }

    public String getTaskThem() {
        return taskThem;
    }

    public TaskDto setTaskThem(String taskThem) {
        this.taskThem = taskThem;
        return this;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public TaskDto setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
        return this;
    }

    public String getTaskDataCreate() {
        return taskDataCreate;
    }

    public TaskDto setTaskDataCreate(String taskDataCreate) {
        this.taskDataCreate = taskDataCreate;
        return this;
    }

    public String getTaskDataCompletion() {
        return taskDataCompletion;
    }

    public TaskDto setTaskDataCompletion(String taskDataCompletion) {
        this.taskDataCompletion = taskDataCompletion;
        return this;
    }

    public String getTaskStartTime() {
        return taskStartTime;
    }

    public TaskDto setTaskStartTime(String taskStartTime) {
        this.taskStartTime = taskStartTime;
        return this;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public TaskDto setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
        return this;
    }

    public AuthorDto getTaskAuthor() {
        return taskAuthor;
    }

    public TaskDto setTaskAuthor(AuthorDto taskAuthor) {
        this.taskAuthor = taskAuthor;
        return this;
    }

    public ExecutorDto getTaskExecutor() {
        return taskExecutor;
    }

    public TaskDto setTaskExecutor(ExecutorDto taskExecutor) {
        this.taskExecutor = taskExecutor;
        return this;
    }

    @Override
    public String toString() {
        return "TaskDto{" +
                "taskId=" + taskId +
                ", taskThem='" + taskThem + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", taskDataCreate='" + taskDataCreate + '\'' +
                ", taskDataCompletion='" + taskDataCompletion + '\'' +
                ", taskStartTime='" + taskStartTime + '\'' +
                ", taskStatus=" + taskStatus +
                ", taskAuthor=" + taskAuthor +
                ", taskExecutor=" + taskExecutor +
                '}';
    }
}
