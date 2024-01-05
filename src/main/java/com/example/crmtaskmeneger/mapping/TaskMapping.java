package com.example.crmtaskmeneger.mapping;

import com.example.crmtaskmeneger.dto.TaskAuthorDto;
import com.example.crmtaskmeneger.dto.TaskDto;
import com.example.crmtaskmeneger.dto.TaskExecutorDto;
import com.example.crmtaskmeneger.dto.request.TaskDtoRequest;
import com.example.crmtaskmeneger.dto.response.TaskDtoResponse;
import com.example.crmtaskmeneger.entities.Employee;
import com.example.crmtaskmeneger.entities.Task;
import com.example.crmtaskmeneger.entities.TaskStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class TaskMapping {
    public static Task mapModelDtoToEntityRequest(TaskDtoRequest taskDto){
        Task task = new Task();
        task.setDescription(taskDto.getDescription())
                .setCreationDate(LocalDateTime.now())
                .setAssignedDate(LocalDateTime.now())
                .setCompletionDate(LocalDate.parse(taskDto.getCompletionDate()))
                .setAssignedTo(new Employee())
                .setCreatedBy(new Employee())
                .setStatus(TaskStatus.IN_PROGRESS);
        return task;
    }
    public static TaskDtoResponse mapModelEntityToDtoResponseWithAuthorAndExecutor(Task task,Employee executor,Employee author){
        TaskAuthorDto taskAuthorDto = mapModelEntityToDtoAuthor(author);
        TaskExecutorDto taskExecutorDto = mapModelEntityToDtoExecutor(executor);
        TaskDtoResponse taskDtoResponse = new TaskDtoResponse();
        taskDtoResponse.setId(task.getId());
        taskDtoResponse.setDescription(task.getDescription());
        taskDtoResponse.setCreationDate(task.getCreationDate().toString());
        taskDtoResponse.setAssignedDate(task.getAssignedDate().toString());
        taskDtoResponse.setCompletionDate(task.getCompletionDate().toString());
        taskDtoResponse.setAssignedTo(taskExecutorDto);
        taskDtoResponse.setCreatedBy(taskAuthorDto);
        taskDtoResponse.setStatus(task.getStatus().toString());
        return taskDtoResponse;
    }
    public static Task mapModelTaskDtoResponseWithAuthAndExecToEntity(TaskDtoResponse taskDtoResponse, Employee taskExecutorDto, Employee taskAuthorDto) throws Exception {
        Task task = new Task();
        System.out.println(taskDtoResponse.getCompletionDate().substring(0,10));
        if(taskDtoResponse.getCompletionDate().isEmpty()){
            throw new Exception("No date");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate parse = LocalDate.parse(taskDtoResponse.getCompletionDate().substring(0,10),formatter);
        task.setDescription(taskDtoResponse.getDescription());
                task.setCreationDate(LocalDateTime.now());
           task     .setAssignedDate(LocalDateTime.now());
           task     .setCompletionDate(parse);
             task   .setAssignedTo(taskExecutorDto);
             task   .setCreatedBy(taskAuthorDto);
               task .setStatus(TaskStatus.IN_PROGRESS);
        return task;
    }
    public static Task mapModelTaskDtoResponseWithAuthToEntity(TaskDtoResponse taskDtoResponse,Employee taskAuthorDto) throws Exception {
        Task task = new Task();
        System.out.println(taskDtoResponse.getCompletionDate().substring(0,10));
        if(taskDtoResponse.getCompletionDate().isEmpty()){
            throw new Exception("No date");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate parse = LocalDate.parse(taskDtoResponse.getCompletionDate().substring(0,10),formatter);

        task.setDescription(taskDtoResponse.getDescription());
         task.setCreationDate(LocalDateTime.now());
            task    .setAssignedDate(LocalDateTime.now());
        task   .setCompletionDate(parse);
        task  .setCreatedBy(taskAuthorDto);
        task   .setStatus(TaskStatus.IN_PROGRESS);
        return task;
    }

    public static TaskDto mapModelEntityToTaskDto(Task task){
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setDescription(task.getDescription());
        taskDto.setCreationDate(task.getCreationDate().toString());
        taskDto.setAssignedDate(task.getAssignedDate().toString());
        taskDto.setCompletionDate(task.getCompletionDate().toString());
        taskDto.setAssignedTo(task.getAssignedTo().getEmployeeId());
        taskDto.setCreatedBy(task.getCreatedBy().getEmployeeId());
        taskDto.setStatus(task.getStatus().toString());
        return taskDto;
    }
    public static TaskAuthorDto mapModelEntityToDtoAuthor(Employee employee){
        TaskAuthorDto taskAuthorDto = new TaskAuthorDto();
        taskAuthorDto.setAuthorId(employee.getEmployeeId());
        taskAuthorDto.setAuthorName(employee.getFirstName());
        taskAuthorDto.setAuthorPatrol(employee.getMiddleName());
        taskAuthorDto.setAuthorSerName(employee.getLastName());
        taskAuthorDto.setAuthorEmail(employee.getEmail());
        taskAuthorDto.setAuthorHireDate(employee.getHireDate().toString());
        taskAuthorDto.setAuthorPhone(employee.getPhoneNum());
        taskAuthorDto.setAuthorRole(employee.getRole());
        taskAuthorDto.setAuthorLogin(employee.getLogin());
        return taskAuthorDto;
    }
    public static TaskExecutorDto mapModelEntityToDtoExecutor(Employee employee){
        TaskExecutorDto taskExecutorDto = new TaskExecutorDto();
        taskExecutorDto.setExecutorId(employee.getEmployeeId());
        taskExecutorDto.setExecutorName(employee.getFirstName());
        taskExecutorDto.setExecutorPatrol(employee.getMiddleName());
        taskExecutorDto.setExecutorSerName(employee.getLastName());
        taskExecutorDto.setExecutorEmail(employee.getEmail());
        taskExecutorDto.setExecutorHireDate(employee.getHireDate().toString());
        taskExecutorDto.setExecutorPhone(employee.getPhoneNum());
        taskExecutorDto.setExecutorRole(employee.getRole());
        taskExecutorDto.setExecutorLogin(employee.getLogin());
        return taskExecutorDto;
    }
    public static List<TaskDto> mapModelListEntityToDto(List<Task> task){
        return task.stream().map(TaskMapping::mapModelEntityToTaskDto).collect(Collectors.toList());
    }
}
