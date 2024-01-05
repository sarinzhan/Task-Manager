package com.example.crmtaskmeneger.service.impl;

import com.example.crmtaskmeneger.entity.TaskEntity;
import com.example.crmtaskmeneger.entity.UserEntity;
import com.example.crmtaskmeneger.entity.enumeric.UserRole;
import com.example.crmtaskmeneger.repository.UserRepository;
import com.example.crmtaskmeneger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity saveNewUser(UserEntity entity) {
        return userRepository.save(entity);
    }

    @Override
    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity getUserById(Long id) throws Exception {
        UserEntity entity = userRepository.findById(id).orElse(null);
        if(Objects.isNull(entity)){
            throw new Exception("Пользователя с таким ID не найдено в системе");
        }
        return entity;
    }

    @Override
    public UserEntity getUserByExecuteTask(TaskEntity task) throws Exception {
        UserEntity entity = userRepository.findByExecutedTask(task).orElse(null);
        if(Objects.isNull(entity)){
            throw new Exception("Исполнитель данной задачи не найден");
        }
        return entity;
    }

    @Override
    public UserEntity getUserByLogin(String login) throws Exception {
        UserEntity entity = userRepository.findByLogin(login).orElse(null);
        if(Objects.isNull(entity)){
            throw new Exception("Пользователь с таким логином не найден");
        }
        return entity;
    }

    @Override
    public List<UserEntity> getAllUsersByName(String name) throws Exception {
        List<UserEntity> entityList = userRepository.findByName(name).orElse(null);
        if(Objects.isNull(entityList)){
            throw new Exception("Пользователи с таким именем не найдены в системе");
        }
        return entityList;
    }

    @Override
    public List<UserEntity> getAllUsersBySerName(String serName) throws Exception {
        List<UserEntity> entityList = userRepository.findBySerName(serName).orElse(null);
        if(Objects.isNull(entityList)){
            throw new Exception("Пользователи с такой фамилией не найдены в системе");
        }
        return entityList;
    }

    @Override
    public List<UserEntity> getAllUsersByPatronymic(String patronymic) throws Exception {
        List<UserEntity> entityList = userRepository.findByPatronymic(patronymic).orElse(null);
        if(Objects.isNull(entityList)){
            throw new Exception("Пользователи с таким отчеством не найдены в системе");
        }
        return entityList;
    }

    @Override
    public List<UserEntity> getAllUsersByDateOfEmployment(LocalDate dateOfEmployment) throws Exception {
        List<UserEntity> entityList = userRepository.findByDateOfEmployment(dateOfEmployment).orElse(null);
        if(Objects.isNull(entityList)){
            throw new Exception("По данной дате найма сотрудников не было");
        }
        return entityList;
    }

    @Override
    public List<UserEntity> getAllUsersByRole(UserRole role) throws Exception {
        List<UserEntity> entityList = userRepository.findByUserRole(role).orElse(null);
        if(Objects.isNull(entityList)){
            throw new Exception("Пользователи с такой ролью не найдены в системе: Проверьте правильность указания роли");
        }
        return entityList;
    }

    @Override
    public UserEntity updateUserData(Long id, String name, String serName, String patronymic) throws Exception {

        if(Objects.isNull(id) || id < 0){
            throw new Exception("Для изменения данных пользователя нужно указать пользователя");
        }

        UserEntity entity = null;
        try {
            entity = getUserById(id);
        } catch (Exception e) {
            throw new Exception("Неверно указан пользователь операция прервана");
        }

        if(Objects.isNull(entity)){
            throw new Exception("При изменении данных пользователя что то пошло не так: Пользователь отсутствует в системе");
        }
        boolean setName = false;
        boolean setSerName = false;
        boolean setPatronymic = false;
        if(Objects.nonNull(name) && !name.isEmpty()){
            entity.setName(name);
            setName = true;
        }
        if(Objects.nonNull(serName) && !serName.isEmpty()){
            entity.setSerName(serName);
            setSerName = true;
        }
        if(Objects.nonNull(patronymic) && !patronymic.isEmpty()){
            entity.setPatronymic(patronymic);
            setPatronymic = true;
        }
        if(setName || setSerName || setPatronymic) {
            entity = userRepository.save(entity);
        }else throw new Exception("Неверно были переданы данные для обновления данных пользователя операция прервана.");
        return entity;
    }

    @Override
    public UserEntity updateUserRole(Long id, UserRole role) throws Exception {
        if(Objects.isNull(id) || id < 0){
            throw new Exception("Для изменения данных пользователя нужно указать пользователя");
        }
        if(Objects.isNull(role)){
            throw new Exception("Нужно передать только ту роль что присутствует в системе");
        }

        UserEntity entity = null;
        try {
            entity = getUserById(id);
        } catch (Exception e) {
            throw new Exception("Неверно указан пользователь операция прервана");
        }

        if(Objects.isNull(entity)){
            throw new Exception("При изменении данных пользователя что то пошло не так: Пользователь отсутствует в системе");
        }
        entity.setRole(role);
        entity = userRepository.save(entity);
        return entity;
    }

    @Override
    public UserEntity updateUserPassword(Long id, String passwordOld, String passwordNew) throws Exception {
        if(Objects.isNull(id) || id < 0){
            throw new Exception("Для изменения данных пользователя нужно указать пользователя");
        }
        if(Objects.isNull(passwordOld) || passwordOld.isEmpty()){
            throw new Exception("Нужно указать текущий пароль что бы подтвердить ваш пароль");
        }

        UserEntity entity = null;
        try {
            entity = getUserById(id);
        } catch (Exception e) {
            throw new Exception("Неверно указан пользователь операция прервана");
        }

        if(Objects.isNull(entity)){
            throw new Exception("При изменении данных пользователя что то пошло не так: Пользователь отсутствует в системе");
        }
        if (!entity.getPassword().equals(passwordOld)){
            throw new Exception("Неверный пароль пользователя");
        }
        entity.setPassword(passwordNew);
        entity = userRepository.save(entity);
        return entity;
    }

    @Override
    public void deleteUser(Long id) throws Exception {
        UserEntity entity = null;
        try {
           entity  = getUserById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage() + ": Удаление пользователя прервано");
        }
        deleteUser(entity);
    }

    @Override
    public void deleteUser(UserEntity entity) throws Exception {

        userRepository.delete(entity);
        throw  new Exception("Пользователь удален из системы");
    }
}
