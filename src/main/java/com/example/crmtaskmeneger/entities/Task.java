package com.example.crmtaskmeneger.entities;


import javax.persistence.*;
import java.time.*;

@Entity
@Table(name="task")
public class Task {
    @Id
    @Column(name = "task_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "description")
    private String description;
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
    @Column(name = "assigned_date")
    private LocalDateTime assignedDate;
    @Column(name = "completion_date")
    private LocalDate completionDate;
    @OneToOne
    @JoinColumn(name = "assigned_to", referencedColumnName = "employee_id")
    private Employee executor; // исполнитель
    @ManyToOne
    @JoinColumn(name = "create_by", referencedColumnName = "employee_id")
    private Employee author; // Создатель задачи

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private TaskStatus status;

    public Task() {
    }


    public Long getId() {
        return id;
    }

    public Task setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Task setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Task setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public LocalDateTime getAssignedDate() {
        return assignedDate;
    }

    public Task setAssignedDate(LocalDateTime assignedDate) {
        this.assignedDate = assignedDate;
        return this;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public Task setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
        return this;
    }

    public Employee getExecutor() {
        return executor;
    }

    public Task setExecutor(Employee assignedTo) {
        this.executor = assignedTo;
        return this;
    }

    public Employee getAuthor() {
        return author;
    }

    public Task setAuthor(Employee createdBy) {
        this.author = createdBy;
        return this;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public Task setStatus(TaskStatus status) {
        this.status = status;
        return this;
    }
}
