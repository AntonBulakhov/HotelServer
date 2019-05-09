package com.edu.bsuir.hotel.hotel.service;

import com.edu.bsuir.hotel.hotel.entity.EventEntity;
import com.edu.bsuir.hotel.hotel.entity.EventRecordEntity;
import com.edu.bsuir.hotel.hotel.entity.UserEntity;

import java.util.List;

public interface EventRecordService {
    EventRecordEntity save(EventRecordEntity entity);
    List<EventRecordEntity> getAll(UserEntity entity);
    EventRecordEntity findByUserAndEvent(UserEntity user, EventEntity event);
}
