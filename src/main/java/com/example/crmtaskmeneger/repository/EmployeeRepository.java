package com.example.crmtaskmeneger.repository;

import com.example.crmtaskmeneger.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    Employee getById(Long id);
    Optional<Employee> getByLogin(String login);

    void deleteByLogin(String login);
}
