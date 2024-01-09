package com.example.crmtaskmeneger.util;

import com.example.crmtaskmeneger.dto.TaskDto;
import com.example.crmtaskmeneger.entity.TaskEntity;
import com.example.crmtaskmeneger.entity.UserEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TaskMapper {

    public static TaskEntity mapTaskDtoToEntity(TaskDto taskDto){
        TaskEntity entity = new TaskEntity();
        entity.setId(taskDto.getTaskId());
        entity.setTaskThem(taskDto.getTaskThem())
                .setDescription(taskDto.getTaskDescription())
                .setDateCreate(Objects.nonNull(taskDto.getTaskDataCreate()) && !taskDto.getTaskDataCreate().isEmpty()?
                        LocalDateTime.parse(taskDto.getTaskDataCreate()) : null)
                .setDateCompletion(Objects.nonNull(taskDto.getTaskDataCompletion()) && !taskDto.getTaskDataCompletion().isEmpty()?
                        LocalDate.parse(taskDto.getTaskDataCompletion()) : null)
                .setTaskStartTime(Objects.nonNull(taskDto.getTaskStartTime()) && !taskDto.getTaskStartTime().isEmpty()?
                        LocalDate.parse(taskDto.getTaskStartTime()): null)
                .setStatus(taskDto.getTaskStatus());
        return entity;
    }

    public static TaskDto mapEntityToDto(TaskEntity entity){
        TaskDto taskDto = new TaskDto();
        taskDto.setTaskId(entity.getId())
                .setTaskThem(entity.getTaskThem())
                .setTaskDescription(entity.getDescription())
                .setTaskDataCreate(taskDto.getTaskDataCreate())
                .setTaskDataCompletion(Objects.nonNull(entity.getDateCompletion())?entity.getDateCompletion().toString():null)
                .setTaskStartTime(Objects.nonNull(entity.getTaskStartTime())?entity.getTaskStartTime().toString(): null)
                .setTaskStatus(entity.getStatus())
                .setTaskAuthor(Objects.nonNull(entity.getAuthor())?UserMapper.mapEntityToAuthorDto(entity.getAuthor()): null)
                .setTaskExecutor(Objects.nonNull(entity.getExecutor())? UserMapper.mapEntityToExecutorDto(entity.getExecutor()):null);
        return taskDto;
    }

    public static List<TaskDto> mapEntityListToTaskDtoList(List<TaskEntity> entityList){
        return entityList.stream().map(TaskMapper::mapEntityToDto).collect(Collectors.toList());
    }
}
