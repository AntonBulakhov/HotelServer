package com.edu.bsuir.hotel.hotel.repository;

import com.edu.bsuir.hotel.hotel.entity.EventEntity;
import com.edu.bsuir.hotel.hotel.entity.EventRecordEntity;
import com.edu.bsuir.hotel.hotel.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EventRecordRepository extends CrudRepository<EventRecordEntity, Long> {
    List<EventRecordEntity> findAllByUser(UserEntity userEntity);
    EventRecordEntity findByUserAndEvent(UserEntity user, EventEntity event);
}
