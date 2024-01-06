package com.example.crmtaskmeneger.util;

import com.example.crmtaskmeneger.dto.TaskDto;
import com.example.crmtaskmeneger.entity.TaskEntity;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TaskMapper {

    public static TaskDto mapEntityToDto(TaskEntity entity){
        TaskDto taskDto = new TaskDto();
        taskDto.setTaskId(entity.getId())
                .setTaskThem(entity.getTaskThem())
                .setTaskDescription(entity.getDescription())
                .setTaskDataCreate(taskDto.getTaskDataCreate())
                .setTaskDataCompletion(Objects.nonNull(entity.getDateCompletion())?entity.getDateCompletion().toString():null)
                .setTaskStartTime(Objects.nonNull(entity.getTaskStartTime())?entity.getTaskStartTime().toString(): null)
                .setTaskStatus(entity.getStatus())
                .setTaskAuthor(UserMapper.mapEntityToAuthorDto(entity.getAuthor()))
                .setTaskExecutor(Objects.nonNull(entity.getExecutor())? UserMapper.mapEntityToExecutorDto(entity.getExecutor()):null);
        return taskDto;
    }

    public static List<TaskDto> mapEntityListToTaskDtoList(List<TaskEntity> entityList){
        return entityList.stream().map(TaskMapper::mapEntityToDto).collect(Collectors.toList());
    }
}
