package com.example.crmtaskmeneger.mapping;

import com.example.crmtaskmeneger.dto.TaskDto;
import com.example.crmtaskmeneger.dto.request.TaskDtoRequest;
import com.example.crmtaskmeneger.entities.Task;

import java.time.LocalDateTime;
import java.util.Optional;

public class TaskMapping {
    public static Task taskDtoToTask(TaskDto taskDto){
        Task task = new Task();
        task.setDescription(taskDto.getDescription());
        task.setCompletionDate(taskDto.getCompletionDate());
        task.setCreatedBy(taskDto.getCreatedBy());
        taskDto.getAssignedDate()
        if(taskDto.getAssignedDate() )
        task.setAssignedDate(taskDto.getAssignedDate());

    }
}
