package com.example.crmtaskmeneger.entities;


import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "employee")
@Entity
public class Employee {
    @Id
    @Column(name = "employee_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "hire_date")
    private LocalDate hireDate;
    @Column(name = "phone_num")
    private String phoneNum;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String role;
    @Column(name = "login")
    private String login;
//    @OneToMany(mappedBy = "assignedTo")
//    private List<Task> tasks;
//    @OneToMany(mappedBy = "createBy")
//    private List<Task> tasks2;

    public Employee() {
    }


//    public List<Task> getTasks() {
//        return tasks;
//    }
//
//    public Employee setTasks(List<Task> tasks) {
//        this.tasks = tasks;
//        return this;
//    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public Employee setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Employee setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getMiddleName() {
        return middleName;
    }

    public Employee setMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Employee setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Employee setEmail(String email) {
        this.email = email;
        return this;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public Employee setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
        return this;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public Employee setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Employee setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getRole() {
        return role;
    }

    public Employee setRole(String role) {
        this.role = role;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public Employee setLogin(String login) {
        this.login = login;
        return this;
    }

    @Override
    public String toString() {
        return "Employee{" + "\n" +
                "employeeId=" + employeeId + "\n" +
                ", firstName='" + firstName + '\'' + "\n" +
                ", middleName='" + middleName + '\'' + "\n" +
                ", lastName='" + lastName + '\'' + "\n" +
                ", email='" + email + '\'' + "\n" +
                ", hireDate=" + hireDate + "\n" +
                ", phoneNum='" + phoneNum + '\'' + "\n" +
                ", password='" + password + '\'' + "\n" +
                ", role='" + role + '\'' + "\n" +
                ", login='" + login + '\'' + "\n" +
                '}';
    }
}
