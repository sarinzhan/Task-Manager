package com.example.crmtaskmeneger.service.impl;

import com.example.crmtaskmeneger.entity.TaskEntity;
import com.example.crmtaskmeneger.entity.UserEntity;
import com.example.crmtaskmeneger.entity.enumeric.TaskStatus;
import com.example.crmtaskmeneger.repository.TaskRepository;
import com.example.crmtaskmeneger.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class TaskServiceImpl implements TaskService {

    public final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskEntity saveNewTask(TaskEntity task) {
        return taskRepository.save(task);
    }

    @Override
    public List<TaskEntity> getAll() {
        return taskRepository.findAll();
    }

    @Override
    public TaskEntity getTaskById(Long id) throws Exception {
        TaskEntity entity = taskRepository.findById(id).orElse(null);
        if(Objects.isNull(entity)){
            throw new Exception("Задача с таким Id не найдена в системе");
        }
        return entity;
    }

    @Override
    public List<TaskEntity> getTaskByTaskThem(String them) throws Exception {
        List<TaskEntity> entity = taskRepository.findByTaskThem(them).orElse(null);
        if(Objects.isNull(entity)){
            throw new Exception("Задача с такой темой не найдена в системе");
        }
        return entity;
    }

    @Override
    public List<TaskEntity> getTaskListByDateCompletion(LocalDate dateCompletion) throws Exception {
        List<TaskEntity> entityList = taskRepository.findByDateCompletion(dateCompletion).orElse(null);
        if(Objects.isNull(entityList)){
            throw new Exception("Задачи с данным сроком окончания не найдены в системы ");
        }
        return entityList;
    }

    @Override
    public List<TaskEntity> getTaskListByStartedTime(LocalDate dateStartExecutedTask) throws Exception {
        List<TaskEntity> entityList = taskRepository.findByTaskStartTime(dateStartExecutedTask).orElse(null);
        if(Objects.isNull(entityList)){
            throw new Exception("По данной дате нет принятых задач");
        }
        return entityList;
    }

    @Override
    public List<TaskEntity> getTaskListByDataCreate(LocalDateTime dateCreate) throws Exception {
        List<TaskEntity> entityList = taskRepository.findByDateCreate(dateCreate).orElse(null);
        if(Objects.isNull(entityList)){
            throw new Exception("По данной дате нет созданных задач");
        }
        return entityList;
    }

    @Override
    public List<TaskEntity> getTaskListByStatus(TaskStatus taskStatus) throws Exception {
        List<TaskEntity> entityList = taskRepository.findByStatus(taskStatus).orElse(null);
        if(Objects.isNull(entityList)){
            throw new Exception("Задачи с данным статусом не найдены в системы ");
        }
        return entityList;
    }

    @Override
    public List<TaskEntity> getTaskListByAuthor(UserEntity entity) throws Exception {
        List<TaskEntity> entityList = taskRepository.findByAuthor(entity).orElse(null);
        if(Objects.isNull(entityList)){
            throw new Exception("Данный пользователь не создавал задачи в системе ");
        }
        return entityList;
    }

    @Override
    public List<TaskEntity> getTaskListByExecutedUser(UserEntity executor) throws Exception {
        List<TaskEntity> entityList = taskRepository.findByExecutor(executor).orElse(null);
        if(Objects.isNull(entityList)){
            throw new Exception("Данный пользователь еще не выполнял задачи");
        }
        return entityList;
    }

    @Override
    public TaskEntity updateTask(TaskEntity taskEntity, UserEntity executor) throws Exception {
        if(Objects.isNull(taskEntity)){
            throw new Exception("Нельзя передавать на исполнение несуществующее задание");
        }
        if(Objects.isNull(executor)){
            throw new Exception("Нельзя передавать не зарегистрированного исполнителя");
        }
        taskEntity.setExecutor(executor);
        taskEntity = taskRepository.save(taskEntity);
        return taskEntity;
    }

    @Override
    public TaskEntity updateTask(TaskEntity taskEntity, TaskStatus status) throws Exception {
        if(Objects.isNull(taskEntity)){
            throw new Exception("Нельзя передавать на исполнение несуществующее задание");
        }
        if(Objects.isNull(status)){
            throw new Exception("Нельзя передавать не определенный статус в системе");
        }
        if(taskEntity.getStatus().equals(TaskStatus.COMPLETED)){
            throw new Exception("Нельзя изменить статус уже выполненной задачи: Попробуйте создать задачу заново");
        }
        taskEntity.setStatus(status);
        taskEntity = taskRepository.save(taskEntity);
        return taskEntity;
    }

    @Override
    public TaskEntity updateTask(TaskEntity taskEntity, LocalDate startTaskExecuted) throws Exception {
        if(Objects.isNull(taskEntity)){
            throw new Exception("Нельзя передавать на исполнение несуществующее задание");
        }
        if(Objects.isNull(startTaskExecuted)){
            throw new Exception("Нельзя передавать не определенное время");
        }
        if(Objects.nonNull(taskEntity.getTaskStartTime())){
            throw new Exception("Нельзя изменить время начала выполнения задачи: Попробуйте пересоздать задачу и попросите директора удалить текущи задачу");
        }
        taskEntity.setTaskStartTime(startTaskExecuted);
        taskEntity = taskRepository.save(taskEntity);
        return taskEntity;
    }

    @Override
    public void deleteTask(Long id) throws Exception {
        TaskEntity entity = null;
        try {
           entity = getTaskById(id);
        } catch (Exception e) {
            throw new Exception("Нельзя удалить не зарегистрированную задачу в системе");
        }
        deleteTask(entity);
    }
    @Override
    public void deleteTask(TaskEntity taskEntity) throws Exception {
        if(Objects.isNull(taskEntity)){
            throw new Exception("При удалении задачи что то пошло не так");
        }
        taskRepository.delete(taskEntity);
        throw new Exception("Задача успешно удалена из системы");
    }
}
