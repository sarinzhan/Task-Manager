package com.example.crmtaskmeneger.mapping;

import com.example.crmtaskmeneger.dto.TaskAuthorDto;
import com.example.crmtaskmeneger.dto.TaskExecutorDto;
import com.example.crmtaskmeneger.dto.UserDto;
import com.example.crmtaskmeneger.dto.request.EmployeeDtoRequest;
import com.example.crmtaskmeneger.dto.response.EmployeeDtoResponse;
import com.example.crmtaskmeneger.entities.Employee;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MappingUser {

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
    //название совпадает с вторым
    public static List<Employee> mapEnttytoDtoModelRequestList(List<EmployeeDtoRequest> dtoRequest){
        List<Employee> employeeList = new ArrayList<>();
        for (EmployeeDtoRequest employeeDtoRequest : dtoRequest){
            employeeList.add( mapModelDtoToEntity(employeeDtoRequest));
        }
        return employeeList;
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
    public static List<EmployeeDtoRequest> mapEntityToDtoEmployeeRequestList(List<Employee> employeeList){
        List<EmployeeDtoRequest> dtoRequestList = new ArrayList<>();
        for(Employee employee : employeeList){
            dtoRequestList.add(mapEntityToDtoEmployeeRequest(employee));
        }
        return dtoRequestList;
    }

    public static Employee mapModelDtoToEntity(EmployeeDtoResponse res){
        Employee entity = new Employee();

        entity.setEmployeeId(res.getEmployeeId());
        entity.setFirstName(res.getFirstName());
        entity.setMiddleName(res.getMiddleName());
        entity.setLastName(res.getLastName());
        entity.setEmail(res.getEmail());
        entity.setHireDate(LocalDate.parse(res.getHireDate()));
        entity.setPhoneNum(res.getPhoneNum());
        entity.setRole(res.getRole());
        entity.setLogin(res.getLogin());

        return entity;
    }
    public static Employee mapModelDtoToEntity(UserDto dto){
        Employee entity = new Employee();

        entity.setEmployeeId(dto.getUserId());
        entity.setFirstName(dto.getUserName());
        entity.setMiddleName(dto.getUserPatrol());
        entity.setLastName(dto.getUserSerName());
        entity.setEmail(dto.getUserEmail());
        entity.setHireDate(LocalDate.parse(dto.getUserHireDate()));
        entity.setPhoneNum(dto.getUserPhone());
        entity.setRole(dto.getUserRole());
        entity.setLogin(dto.getUserLogin());

        return entity;
    }
    public static List<Employee> mapModelDtoToEntityList(List<EmployeeDtoResponse> responseList){
        return responseList.stream().map(MappingUser::mapModelDtoToEntity).collect(Collectors.toList());
    }

    public static EmployeeDtoResponse mapEntityToDtoEmployeeResponse(Employee entity){
        EmployeeDtoResponse dtoResponse = new EmployeeDtoResponse();
        dtoResponse.setEmployeeId(entity.getEmployeeId());
        dtoResponse.setFirstName(entity.getFirstName());
        dtoResponse.setMiddleName(entity.getMiddleName());
        dtoResponse.setLastName(entity.getLastName());
        dtoResponse.setEmail(entity.getEmail());
        dtoResponse.setHireDate(entity.getHireDate().toString());
        dtoResponse.setPhoneNum(entity.getPhoneNum());
//        dtoResponse.setPassword(entity.getPassword());
        dtoResponse.setRole(entity.getRole());
        dtoResponse.setLogin(entity.getLogin());
        return dtoResponse;
    }
    public static List<EmployeeDtoResponse> mapEntityToDtoEmployeeResponseList(List<Employee> employeeList){
        return employeeList.stream().map(MappingUser::mapEntityToDtoEmployeeResponse).collect(Collectors.toList());
    }


    public static UserDto mapEntityToUserDTO(Employee entity){
        UserDto userDto = new UserDto();
        userDto
                .setUserId(entity.getEmployeeId())
                .setUserName(entity.getFirstName())
                .setUserPatrol(entity.getMiddleName())
                .setUserSerName(entity.getLastName())
                .setUserEmail(entity.getEmail())
                .setUserHireDate(entity.getHireDate().toString())
                .setUserPhone(entity.getPhoneNum())
                .setUserRole(entity.getRole())
                .setUserLogin(entity.getLogin());

        return userDto;
    }
    public static TaskAuthorDto mapEntityToTaskAuthorDTO(Employee entity){
        TaskAuthorDto userDto = new TaskAuthorDto();
        userDto
                .setAuthorId(entity.getEmployeeId())
                .setAuthorName(entity.getFirstName())
                .setAuthorPatrol(entity.getMiddleName())
                .setAuthorSerName(entity.getLastName())
                .setAuthorEmail(entity.getEmail())
                .setAuthorHireDate(entity.getHireDate().toString())
                .setAuthorPhone(entity.getPhoneNum())
                .setAuthorRole(entity.getRole())
                .setAuthorLogin(entity.getLogin());

        return userDto;
    }
    public static TaskAuthorDto mapUserDtoToTaskAuthorDTO(UserDto userDto){
        TaskAuthorDto taskAuthorDto = new TaskAuthorDto();
        taskAuthorDto
                .setAuthorId(userDto.getUserId())
                .setAuthorName(userDto.getUserName())
                .setAuthorPatrol(userDto.getUserPatrol())
                .setAuthorSerName(userDto.getUserSerName())
                .setAuthorEmail(userDto.getUserEmail())
                .setAuthorHireDate(userDto.getUserHireDate())
                .setAuthorPhone(userDto.getUserPhone())
                .setAuthorRole(userDto.getUserRole())
                .setAuthorLogin(userDto.getUserLogin());

        return taskAuthorDto;
    }
    public static TaskExecutorDto mapUserDtoToTaskExecutorDto(UserDto userDto){
        TaskExecutorDto taskExecutorDto = new TaskExecutorDto();
        taskExecutorDto
                .setExecutorId(userDto.getUserId())
                .setExecutorName(userDto.getUserName())
                .setExecutorPatrol(userDto.getUserPatrol())
                .setExecutorSerName(userDto.getUserSerName())
                .setExecutorEmail(userDto.getUserEmail())
                .setExecutorHireDate(userDto.getUserHireDate())
                .setExecutorPhone(userDto.getUserPhone())
                .setExecutorRole(userDto.getUserRole())
                .setExecutorLogin(userDto.getUserLogin());

        return taskExecutorDto;
    }
    public static TaskExecutorDto mapUserDtoToTaskExecutorDto(EmployeeDtoResponse employeeDtoResponse){
        TaskExecutorDto taskExecutorDto = new TaskExecutorDto();
        taskExecutorDto
                .setExecutorId(employeeDtoResponse.getEmployeeId())
                .setExecutorName(employeeDtoResponse.getFirstName())
                .setExecutorPatrol(employeeDtoResponse.getMiddleName())
                .setExecutorSerName(employeeDtoResponse.getLastName())
                .setExecutorEmail(employeeDtoResponse.getEmail())
                .setExecutorHireDate(employeeDtoResponse.getHireDate())
                .setExecutorPhone(employeeDtoResponse.getPhoneNum())
                .setExecutorRole(employeeDtoResponse.getRole())
                .setExecutorLogin(employeeDtoResponse.getLogin());

        return taskExecutorDto;
    }


}
