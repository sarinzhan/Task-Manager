package com.example.crmtaskmeneger.entity;

import com.example.crmtaskmeneger.entity.enumeric.UserRole;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "user_app")
public class UserEntity extends BaseEntities {


    @Column(name = "login", unique = true, nullable = false)
    private String login;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name = "ser_name")
    private String serName;
    @Column(name = "patronymic")
    private String patronymic;
    @Column(name = "date_of_employment")
    private LocalDate dateOfEmployment;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private UserRole role;

    @OneToOne
    @JoinColumn(name = "executed_task")
    private TaskEntity executedTask;



    public String getLogin() {
        return login;
    }

    public UserEntity setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getSerName() {
        return serName;
    }

    public UserEntity setSerName(String serName) {
        this.serName = serName;
        return this;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public UserEntity setPatronymic(String patronymic) {
        this.patronymic = patronymic;
        return this;
    }

    public LocalDate getDateOfEmployment() {
        return dateOfEmployment;
    }

    public UserEntity setDateOfEmployment(LocalDate dateOfEmployment) {
        this.dateOfEmployment = dateOfEmployment;
        return this;
    }

    public UserRole getRole() {
        return role;
    }

    public UserEntity setRole(UserRole userRole) {
        this.role = userRole;
        return this;
    }

    public TaskEntity getExecutedTask() {
        return executedTask;
    }

    public UserEntity setExecutedTask(TaskEntity executedTask) {
        this.executedTask = executedTask;
        return this;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", serName='" + serName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", dateOfEmployment=" + dateOfEmployment +
                ", role=" + role +
                ", executedTask=" + executedTask +
                ", id=" + id +
                '}';
    }
}
