package com.example.crmtaskmeneger.repository;

import com.example.crmtaskmeneger.entity.TaskEntity;
import com.example.crmtaskmeneger.entity.UserEntity;
import com.example.crmtaskmeneger.entity.enumeric.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findById(Long id);
    Optional<UserEntity> findByExecutedTask(TaskEntity task);
    Optional<UserEntity> findByLogin(String login);
    Optional<List<UserEntity>> findByName(String name);
    Optional<List<UserEntity>> findBySerName(String serName);
    Optional<List<UserEntity>> findByPatronymic(String patron);
    Optional<List<UserEntity>> findByDateOfEmployment(LocalDate dateEmployment);
    Optional<List<UserEntity>> findByRole(UserRole role);


}
