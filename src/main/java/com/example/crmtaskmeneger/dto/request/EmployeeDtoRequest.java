package com.example.crmtaskmeneger.dto.request;

import com.example.crmtaskmeneger.entities.Role;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Scope("session")
public class EmployeeDtoRequest {
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
        private String hireDate;
    private String phoneNum;
    private String password;
    private Role role;
    private String login;

    public EmployeeDtoRequest() {
    }

    public EmployeeDtoRequest(String firstName, String middleName, String lastName, String email, String hireDate, String phoneNum, String password, Role role, String login) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.hireDate = hireDate;
        this.phoneNum = phoneNum;
        this.password = password;
        this.role = role;
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "EmployeeDtoRequest{" +
                "firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", hireDate=" + hireDate +
                ", phoneNum='" + phoneNum + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}
