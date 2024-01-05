package com.example.crmtaskmeneger.service.impl;

import com.example.crmtaskmeneger.entities.Employee;
import com.example.crmtaskmeneger.entities.Role;
import com.example.crmtaskmeneger.repository.EmployeeRepository;
import com.example.crmtaskmeneger.repository.TaskRepository;
import com.example.crmtaskmeneger.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository repository, TaskRepository taskRepository) {
        this.employeeRepository = repository;
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getById(Long id){
        return employeeRepository.getById(id);
    }

    @Override
    public Employee getByLogin(String login){
        return employeeRepository.getByLogin(login).orElse(null);
    }

    @Override
    public Employee save(Employee entity) {
        return employeeRepository.save(entity);
    }

    @Override
    public void deleteEmployeeEntity(Employee entity) {
        employeeRepository.delete(entity);
    }

    @Override
    public void deleteEmployeeEntity(long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public void deleteEmployeeEntity(String login) {
        employeeRepository.deleteByLogin(login);
    }

    @Override
    public List<Employee> getFreeEmployee() {
        List<Employee> collect = employeeRepository.findAll().stream()
                .filter(em -> em.getRole() == Role.EMPLOYEE)
                .filter(em -> Objects.isNull(em.getAssignedTask()))
                .toList();
        if(collect.isEmpty()){
            throw  new NoSuchElementException("No free employee");
        }else {
            return collect;
        }
    }
}
