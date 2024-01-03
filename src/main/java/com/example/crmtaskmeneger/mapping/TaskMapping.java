package com.example.crmtaskmeneger.mapping;

import com.example.crmtaskmeneger.dto.TaskDto;
import com.example.crmtaskmeneger.dto.request.TaskDtoRequest;
import com.example.crmtaskmeneger.dto.response.TaskDtoResponse;
import com.example.crmtaskmeneger.entities.Employee;
import com.example.crmtaskmeneger.entities.Task;
import com.example.crmtaskmeneger.entities.TaskStatus;
import com.example.crmtaskmeneger.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class TaskMapping {
    TaskRepository taskRepository;
    @Autowired
    public TaskMapping(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public static Task mapModelDtoRequestToEntity(TaskDtoRequest taskDto){
        Task task = new Task();
        task.setDescription(taskDto.getDescription())
                .setCreationDate(LocalDateTime.now())
                .setAssignedDate(LocalDateTime.now())
                .setCompletionDate(LocalDate.parse(taskDto.getCompletionDate()))
                .setAssignedTo(new Employee())
                .setCreatedBy(new Employee())
                .setStatus(TaskStatus.IN_PROGRESS.toString());
        return task;
    }
    public static Task mapModelDtoResponseToEntity(TaskDtoResponse taskDto){
        Task task = new Task();
        Long assignedTo = taskDto.getAssignedTo();
        Long createdBy = taskDto.getCreatedBy();

        task.setDescription(taskDto.getDescription())
                .setCreationDate(LocalDateTime.now())
                .setAssignedDate(LocalDateTime.now())
                .setCompletionDate(LocalDate.parse(taskDto.getCompletionDate()))
//                  .setAssignedTo()
//                .setCreatedBy()
                .setStatus(TaskStatus.IN_PROGRESS.toString())
                .setId(taskDto.getId());
        return task;
    }
    public static TaskDtoResponse mapModelEntityToDto(Task task){
        TaskDtoResponse taskDtoResponse = new TaskDtoResponse();
        taskDtoResponse.setId(task.getId());
        taskDtoResponse.setDescription(task.getDescription());
        taskDtoResponse.setCreationDate(task.getCreationDate().toString());
        taskDtoResponse.setAssignedDate(task.getAssignedDate().toString());
        taskDtoResponse.setCompletionDate(task.getCompletionDate().toString());
        taskDtoResponse.setAssignedTo(task.getAssignedTo().getEmployeeId());
        taskDtoResponse.setCreatedBy(task.getCreatedBy().getEmployeeId());
        taskDtoResponse.setStatus(task.getStatus());
        return taskDtoResponse;
    }
    public static List<TaskDtoResponse> mapListModelEntityToDtoResponse(List<Task> taskList){
        return taskList.stream().map(TaskMapping::mapModelEntityToDto).collect(Collectors.toList());
    }
//    public static List<Task> mapListModelDtoResponseToEntity(List<TaskDtoResponse> dtoResponseList){
//        return dtoResponseList.stream().map(TaskMapping::mapModelDtoToEntity).collect(Collectors.toList())
//    }
}
