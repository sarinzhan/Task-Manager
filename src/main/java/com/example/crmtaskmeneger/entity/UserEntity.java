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
    private UserRole userRole;

    @OneToOne
    @JoinColumn(name = "executed_task")
    private TaskEntity executedTask;


    public UserEntity() {
    }

    public UserEntity(
            String login,
            String password,
            String name,
            String serName,
            String patronymic,
            LocalDate dateOfEmployment,
            UserRole userRole
    ) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.serName = serName;
        this.patronymic = patronymic;
        this.dateOfEmployment = dateOfEmployment;
        this.userRole = userRole;
    }

    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public BaseEntities setId(Long id) {
        return this.setId(id);
    }

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

    public UserEntity setRole(UserRole userRole) {
        this.userRole = userRole;
        return this;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public UserEntity setUserRole(UserRole userRole) {
        this.userRole = userRole;
        return this;
    }

    public TaskEntity getExecutedTask() {
        return executedTask;
    }

    public UserEntity setExecutedTask(TaskEntity executedTask) {
        this.executedTask = executedTask;
        return this;
    }
}
