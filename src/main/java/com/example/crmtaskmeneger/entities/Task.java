package com.example.crmtaskmeneger.entities;


import javax.persistence.*;
import java.time.*;

@Entity
@Table(name="task")
public class Task {
    @Id
    @Column(name = "task_id")
    private Integer id;
    @Column(name = "description")
    private String description;
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
    @Column(name = "assigned_date")
    private LocalDateTime assignedDate;
    @Column(name = "completion_date")
    private LocalDate completionDate;
//    @Column(name = "assigned_to")
//    private Integer assignedTo;
    @ManyToOne
    @JoinColumn(name = "assigned_to", referencedColumnName = "employee_id")
    private Employee assignedTo;
//    @Column(name = "create_by")
//    private Integer createdBy;
    @ManyToOne
    @JoinColumn(name = "create_by", referencedColumnName = "employee_id")
    private Employee createdBy;

    @Column(name = "status")
    private String status;

    public Task() {
    }


    public Integer getId() {
        return id;
    }

    public Task setId(Integer id) {
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

    public Employee getAssignedTo() {
        return assignedTo;
    }

    public Task setAssignedTo(Employee assignedTo) {
        this.assignedTo = assignedTo;
        return this;
    }

    public Employee getCreatedBy() {
        return createdBy;
    }

    public Task setCreatedBy(Employee createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Task setStatus(String status) {
        this.status = status;
        return this;
    }
}
