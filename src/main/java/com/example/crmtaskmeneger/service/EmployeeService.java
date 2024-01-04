package com.example.crmtaskmeneger.service;

import com.example.crmtaskmeneger.entities.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAll();

    Employee getById(Long id);

    Employee getByLogin(String login);

    Employee save(Employee entity);
    List<Employee> getFreeEmployee();
    void deleteEmployeeEntity(Employee entity);
    void deleteEmployeeEntity(long id);
    void deleteEmployeeEntity(String login);
}
