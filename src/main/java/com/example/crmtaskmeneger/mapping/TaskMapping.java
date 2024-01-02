package com.example.crmtaskmeneger.mapping;

import com.example.crmtaskmeneger.dto.TaskDto;
import com.example.crmtaskmeneger.dto.request.TaskDtoRequest;
import com.example.crmtaskmeneger.entities.Employee;
import com.example.crmtaskmeneger.entities.Task;
import com.example.crmtaskmeneger.entities.TaskStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public class TaskMapping {
    public static Task mapModelDtoToEntity(TaskDtoRequest taskDto){
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
}
