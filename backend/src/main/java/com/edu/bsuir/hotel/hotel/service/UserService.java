package com.edu.bsuir.hotel.hotel.service;

import com.edu.bsuir.hotel.hotel.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserEntity findByLogin(String login);
    Optional<UserEntity> findById(int id);
    UserEntity findByEmail(String email);
    UserEntity save(UserEntity userEntity);
    List<UserEntity> findAllByBlocked(byte bl);
    List<UserEntity> findAll();
}
