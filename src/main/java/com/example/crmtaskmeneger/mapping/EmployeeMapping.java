package com.example.crmtaskmeneger.mapping;

import com.example.crmtaskmeneger.dto.request.EmployeeDtoRequest;
import com.example.crmtaskmeneger.entities.Employee;

import java.time.LocalDate;

public class EmployeeMapping {

    public static Employee mapModelDtoToEntity(EmployeeDtoRequest dtoRequest){
        Employee entity = new Employee();

        entity.setFirstName(dtoRequest.getFirstName());
        entity.setMiddleName(dtoRequest.getMiddleName());
        entity.setLastName(dtoRequest.getLastName());
        entity.setEmail(dtoRequest.getEmail());
        entity.setHireDate(LocalDate.parse(dtoRequest.getHireDate()));
        entity.setPhoneNum(dtoRequest.getPhoneNum());
        entity.setPassword(dtoRequest.getPassword());
        entity.setRole(dtoRequest.getRole());
        entity.setLogin(dtoRequest.getLogin());

        return entity;
    }
}
