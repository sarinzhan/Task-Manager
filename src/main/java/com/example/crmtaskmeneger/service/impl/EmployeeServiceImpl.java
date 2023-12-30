package com.example.crmtaskmeneger.service.impl;

import com.example.crmtaskmeneger.entities.Employee;
import com.example.crmtaskmeneger.repository.EmployeeRepository;
import com.example.crmtaskmeneger.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Employee> getAll() {
        return repository.findAll();
    }

    @Override
    public Employee getById(Long id){
        return repository.getById(id);
    }

    @Override
    public Employee getByLogin(String login){
        return repository.getByLogin(login).orElse(null);
    }

    @Override
    public Employee save(Employee entity) {
        return repository.save(entity);
    }

    @Override
    public void deleteEmployeeEntity(Employee entity) {
        repository.delete(entity);
    }

    @Override
    public void deleteEmployeeEntity(long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteEmployeeEntity(String login) {
        repository.deleteByLogin(login);
    }
}
