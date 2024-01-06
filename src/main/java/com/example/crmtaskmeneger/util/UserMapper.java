package com.example.crmtaskmeneger.util;

import com.example.crmtaskmeneger.dto.AuthorDto;
import com.example.crmtaskmeneger.dto.EmployeeDto;
import com.example.crmtaskmeneger.dto.ExecutorDto;
import com.example.crmtaskmeneger.dto.UserDto;
import com.example.crmtaskmeneger.entity.TaskEntity;
import com.example.crmtaskmeneger.entity.UserEntity;
import com.example.crmtaskmeneger.entity.enumeric.UserRole;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public  class UserMapper {


    // ==============================  Mapped Dto To Entity ==========================================
    public static UserEntity mapDtoToEntity(
            Long id,
            String login,
            String password,
            String name,
            String serName,
            String patronymic,
            String dateOfEmployment,
            UserRole userRole
            ){

        UserEntity entity = new UserEntity()
                .setLogin(login)
                .setPassword(password)
                .setName(name)
                .setSerName(serName)
                .setPatronymic(patronymic)
                .setDateOfEmployment(LocalDate.parse(dateOfEmployment))
                .setRole(userRole);

        entity.setId(id);
        return entity;
    }
    public static  UserEntity mapDtoToEntity(UserDto userDto) {
        return mapDtoToEntity(
                userDto.getUserId(),
                userDto.getUserLogin(),
                null,
                userDto.getUserName(),
                userDto.getUserSerName(),
                userDto.getUserPatronymic(),
                userDto.getUserDateOfEmployment(),
                userDto.getUserRole()
                );
    }
    public static  UserEntity mapDtoToEntity(EmployeeDto employeeDto) {
        return mapDtoToEntity(
                employeeDto.getEmployeeId(),
                employeeDto.getEmployeeLogin(),
                null,
                employeeDto.getEmployeeName(),
                employeeDto.getEmployeeSerName(),
                employeeDto.getEmployeePatronymic(),
                employeeDto.getEmployeeDateOfEmployment(),
                employeeDto.getEmployeeRole()
        );
    }
    public static  UserEntity mapDtoToEntity(AuthorDto authorDto)  {
        return mapDtoToEntity(
                authorDto.getAuthorId(),
                authorDto.getAuthorLogin(),
                null,
                authorDto.getAuthorName(),
                authorDto.getAuthorSerName(),
                authorDto.getAuthorPatronymic(),
                authorDto.getAuthorDateOfEmployment(),
                authorDto.getAuthorRole()

        );
    }
    public static  UserEntity mapDtoToEntity(ExecutorDto executorDto)  {
        return mapDtoToEntity(
                executorDto.getExecutorId(),
                executorDto.getExecutorLogin(),
                null,
                executorDto.getExecutorName(),
                executorDto.getExecutorSerName(),
                executorDto.getExecutorPatronymic(),
                executorDto.getExecutorDateOfEmployment(),
                executorDto.getExecutorRole()
        );
    }

    // =========================== Mapped Entity to DTO ========================================================
    public static UserDto mapEntityToUserDto(UserEntity entity){
        UserDto userDto = new UserDto()
                .setUserId(entity.getId())
                .setUserLogin(entity.getLogin())
                .setUserName(entity.getName())
                .setUserSerName(entity.getSerName())
                .setUserPatronymic(entity.getPatronymic())
                .setUserDateOfEmployment(entity.getDateOfEmployment().toString())
                .setUserRole(entity.getRole());
        return userDto;
    }

    public static EmployeeDto mapEntityToEmployeeDto(UserEntity entity){
        EmployeeDto employeeDto = new EmployeeDto()
                .setEmployeeId(entity.getId())
                .setEmployeeLogin(entity.getLogin())
                .setEmployeeName(entity.getName())
                .setEmployeeSerName(entity.getSerName())
                .setEmployeePatronymic(entity.getPatronymic())
                .setEmployeeDateOfEmployment(entity.getDateOfEmployment().toString())
                .setEmployeeRole(entity.getRole());
        return employeeDto;
    }

    public static AuthorDto mapEntityToAuthorDto(UserEntity entity){
        AuthorDto authorDto = new AuthorDto()
                .setAuthorId(entity.getId())
                .setAuthorLogin(entity.getLogin())
                .setAuthorName(entity.getName())
                .setAuthorSerName(entity.getSerName())
                .setAuthorPatronymic(entity.getPatronymic())
                .setAuthorDateOfEmployment(entity.getDateOfEmployment().toString())
                .setAuthorRole(entity.getRole());
        return authorDto;
    }
    public static ExecutorDto mapEntityToExecutorDto(UserEntity entity){
        ExecutorDto executorDto = new ExecutorDto()
                .setExecutorId(entity.getId())
                .setExecutorLogin(entity.getLogin())
                .setExecutorName(entity.getName())
                .setExecutorSerName(entity.getSerName())
                .setExecutorPatronymic(entity.getPatronymic())
                .setExecutorDateOfEmployment(entity.getDateOfEmployment().toString())
                .setExecutorRole(entity.getRole());
        return executorDto;
    }

    // =============================   Mapped To List  Entity    =============================================
    public static List<UserEntity> mapUserDtoListToEntityList(List<UserDto> userDtos) throws Exception {
        List<UserEntity> entityList = new ArrayList<>();
        for(UserDto el : userDtos){
            entityList.add(mapDtoToEntity(el));
        }
        return entityList;
    }

    public static List<UserEntity> mapEmployeeDtoListToEntityList(List<EmployeeDto> employeeDtos) throws Exception {
        List<UserEntity> entityList = new ArrayList<>();
        for(EmployeeDto el : employeeDtos){
            entityList.add(mapDtoToEntity(el));
        }
        return entityList;
    }
    public static List<UserEntity> mapAuthorDtoListToEntityList(List<AuthorDto> authorDtos) throws Exception {
        List<UserEntity> entityList = new ArrayList<>();
        for(AuthorDto el : authorDtos){
            entityList.add(mapDtoToEntity(el));
        }
        return entityList;
    }
    public static List<UserEntity> mapExecutorDtoListToEntityList(List<ExecutorDto> executorDtos) throws Exception {
        List<UserEntity> entityList = new ArrayList<>();
        for(ExecutorDto el : executorDtos){
            entityList.add(mapDtoToEntity(el));
        }
        return entityList;
    }


    // =============================   Mapped To List  Dto    =============================================
    public static List<UserDto> mapEntityListToUserDtoList(List<UserEntity> entityList){
        return entityList.stream().map(UserMapper::mapEntityToUserDto).collect(Collectors.toList());
    }
    public static List<UserDto> mapEntityListToEployeeDtoList(List<UserEntity> entityList){
        return entityList.stream().map(UserMapper::mapEntityToUserDto).collect(Collectors.toList());
    }
    public static List<AuthorDto> mapEntityListToAuthorDtoList(List<UserEntity> entityList){
        return entityList.stream().map(UserMapper::mapEntityToAuthorDto).collect(Collectors.toList());
    }
    public static List<ExecutorDto> mapEntityListToExecutorDtoList(List<UserEntity> entityList){
        return entityList.stream().map(UserMapper::mapEntityToExecutorDto).collect(Collectors.toList());
    }

    public static List<EmployeeDto> mapEntityListToEmployeeDtoList(List<UserEntity> entityList){
        return entityList.stream().map(UserMapper::mapEntityToEmployeeDto).collect(Collectors.toList());
    }
}
