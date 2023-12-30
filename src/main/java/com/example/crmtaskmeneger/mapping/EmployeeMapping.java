package com.example.crmtaskmeneger.mapping;

import com.example.crmtaskmeneger.dto.request.EmployeeDtoRequest;
import com.example.crmtaskmeneger.dto.response.EmployeeDtoResponse;
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
    public static Employee mapModelDtoToEntity(EmployeeDtoResponse res){
        Employee entity = new Employee();

        entity.setEmployeeId(res.getId());
        entity.setFirstName(res.getFirstName());
        entity.setMiddleName(res.getMiddleName());
        entity.setLastName(res.getLastName());
        entity.setEmail(res.getEmail());
        entity.setHireDate(res.getHireDate());
        entity.setPhoneNum(res.getPhoneNum());
        entity.setPassword(res.getPassword());
        entity.setRole(res.getRole());
        entity.setLogin(res.getLogin());

        return entity;
    }

    public static EmployeeDtoRequest mapEntityToDtoEmployeeRequest(Employee entity){
        EmployeeDtoRequest request = new EmployeeDtoRequest();
        request.setFirstName(entity.getFirstName());
        request.setMiddleName(entity.getMiddleName());
        request.setLastName(entity.getLastName());
        request.setEmail(entity.getEmail());
        request.setHireDate(entity.getHireDate().toString());
        request.setPhoneNum(entity.getPhoneNum());
        request.setPassword(entity.getPassword());
        request.setRole(entity.getRole());
        request.setLogin(entity.getLogin());
        return request;
    }

    public static EmployeeDtoResponse mapEntityToDtoEmployeeResponse(Employee entity){
        EmployeeDtoResponse dtoResponse = new EmployeeDtoResponse();
        dtoResponse.setId(entity.getEmployeeId());
        dtoResponse.setFirstName(entity.getFirstName());
        dtoResponse.setMiddleName(entity.getMiddleName());
        dtoResponse.setLastName(entity.getLastName());
        dtoResponse.setEmail(entity.getEmail());
        dtoResponse.setHireDate(entity.getHireDate());
        dtoResponse.setPhoneNum(entity.getPhoneNum());
        dtoResponse.setPassword(entity.getPassword());
        dtoResponse.setRole(entity.getRole());
        dtoResponse.setLogin(entity.getLogin());
        return dtoResponse;
    }
}