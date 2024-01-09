package com.example.crmtaskmeneger.entity;

import com.example.crmtaskmeneger.entity.enumeric.TaskStatus;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "task_app")
public class TaskEntity  extends BaseEntities{
    @Column(name = "task_them")
    private String taskThem;
    @Column(name = "description")
    private String description;
    @Column(name = "date_create")
    private LocalDateTime dateCreate;
    @Column(name = "date_completion")
    private LocalDate dateCompletion;
    @Column(name = "task_start_time")
    private LocalDate taskStartTime;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private TaskStatus status;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private UserEntity author;

    @ManyToOne
    @JoinColumn(name = "executor_id")
    private UserEntity executor;


    public String getTaskThem() {
        return taskThem;
    }

    public TaskEntity setTaskThem(String taskThem) {
        this.taskThem = taskThem;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public TaskEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDateTime getDateCreate() {
        return dateCreate;
    }

    public TaskEntity setDateCreate(LocalDateTime dateCreate) {
        this.dateCreate = dateCreate;
        return this;
    }

    public LocalDate getDateCompletion() {
        return dateCompletion;
    }

    public TaskEntity setDateCompletion(LocalDate dateCompletion) {
        this.dateCompletion = dateCompletion;
        return this;
    }

    public LocalDate getTaskStartTime() {
        return taskStartTime;
    }

    public TaskEntity setTaskStartTime(LocalDate taskStartTime) {
        this.taskStartTime = taskStartTime;
        return this;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public TaskEntity setStatus(TaskStatus status) {
        this.status = status;
        return this;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public TaskEntity setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    public UserEntity getExecutor() {
        return executor;
    }

    public TaskEntity setExecutor(UserEntity executor) {
        this.executor = executor;
        return this;
    }
    @PrePersist
    public void createDateNow(){
        this.dateCreate = LocalDateTime.now();
    }

}
