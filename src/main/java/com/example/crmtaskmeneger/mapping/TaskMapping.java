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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TaskMapping {

    public static Task mapModelResponseDtoToEntity(TaskDtoResponse taskDto)  {

        System.out.println("Mapper task to entity " + taskDto);
        Task taskEntity = new Task();
        taskEntity.setId(taskDto.getId())
                .setDescription(taskDto.getDescription())
                .setCompletionDate(LocalDate.from(LocalDate.parse(taskDto.getCompletionDate().substring(0,10), DateTimeFormatter.ofPattern("yyyy-MM-dd"))));

        taskEntity.setCreationDate(LocalDate.parse(taskDto.getCreationDate()).atStartOfDay());
        taskEntity.setAssignedDate(LocalDate.parse(taskDto.getCreationDate()).atStartOfDay());

        TaskStatus status = null;
        TaskStatus[] statuses = TaskStatus.values();
        for (int i = 0; i < statuses.length; i++) {
            if(taskDto.getStatus().equalsIgnoreCase(statuses[i].name())){
                status = statuses[i];
            }
        }
        taskEntity.setStatus(status);

        Employee author = MappingUser.mapModelDtoAuthorToEntity(taskDto.getCreatedBy());
        taskEntity.setAuthor(author);

        return taskEntity;
    }
    public static Task mapModelDtoToEntityRequest(TaskDtoRequest taskDto){
        Task task = new Task();
        task.setDescription(taskDto.getDescription())
                .setCreationDate(LocalDateTime.now())
                .setAssignedDate(LocalDateTime.now())
                .setCompletionDate(LocalDate.parse(taskDto.getCompletionDate()))
                .setExecutor(new Employee())
                .setAuthor(new Employee())
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

    public static List<TaskDtoResponse> mapModelEntityToDtoResponseWithAuthorAndExecutorList(List<Task> entityList){
        List<TaskDtoResponse> taskDtoResponsesList = new ArrayList<>();
        for (Task el : entityList){
            TaskDtoResponse response = mapModelEntityToDtoResponseWithAuthorAndExecutor(el,el.getExecutor(),el.getAuthor());
            taskDtoResponsesList.add(response);
        }
        return taskDtoResponsesList;
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
             task   .setExecutor(taskExecutorDto);
             task   .setAuthor(taskAuthorDto);
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
        task  .setAuthor(taskAuthorDto);
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
        taskDto.setAssignedTo(task.getExecutor().getEmployeeId());
        taskDto.setCreatedBy(task.getAuthor().getEmployeeId());
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
        if(Objects.nonNull(employee)) {
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
        return null;
    }
    public static List<TaskDto> mapModelListEntityToDto(List<Task> task){
        return task.stream().map(TaskMapping::mapModelEntityToTaskDto).collect(Collectors.toList());
    }
}
