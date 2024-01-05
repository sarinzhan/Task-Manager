package com.example.crmtaskmeneger.dto.response;

import com.example.crmtaskmeneger.entities.Role;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class EmployeeDtoResponse {
    private Long employeeId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String hireDate;
    private String phoneNum;
    private Role role;
    private String login;

    public EmployeeDtoResponse() {
    }

    public EmployeeDtoResponse(String firstName, String middleName, String lastName, Role role) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.role = role;
    }

    public EmployeeDtoResponse(String firstName, String middleName, String lastName, String email, String hireDate, String phoneNum, Role role, String login) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.hireDate = hireDate;
        this.phoneNum = phoneNum;
        this.role = role;
        this.login = login;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public EmployeeDtoResponse setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getMiddleName() {
        return middleName;
    }

    public EmployeeDtoResponse setMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public EmployeeDtoResponse setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public EmployeeDtoResponse setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getHireDate() {
        return hireDate;
    }

    public EmployeeDtoResponse setHireDate(String hireDate) {
        this.hireDate = hireDate;
        return this;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public EmployeeDtoResponse setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public EmployeeDtoResponse setLogin(String login) {
        this.login = login;
        return this;
    }

    @Override
    public String toString() {
        return "EmployeeDtoResponse{" +
                "id=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", hireDate=" + hireDate +
                ", phoneNum='" + phoneNum + '\'' +
//                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}
