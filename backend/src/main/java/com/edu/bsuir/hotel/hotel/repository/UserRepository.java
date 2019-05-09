package com.edu.bsuir.hotel.hotel.repository;

import com.edu.bsuir.hotel.hotel.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    UserEntity findByLogin(String login);
    UserEntity findByEmail(String email);
    List<UserEntity> findAllByBlocked(byte bl);
    List<UserEntity> findAll();
}
