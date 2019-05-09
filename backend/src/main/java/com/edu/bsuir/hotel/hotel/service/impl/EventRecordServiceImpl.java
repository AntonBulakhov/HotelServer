package com.edu.bsuir.hotel.hotel.service.impl;

import com.edu.bsuir.hotel.hotel.entity.EventEntity;
import com.edu.bsuir.hotel.hotel.entity.EventRecordEntity;
import com.edu.bsuir.hotel.hotel.entity.UserEntity;
import com.edu.bsuir.hotel.hotel.repository.EventRecordRepository;
import com.edu.bsuir.hotel.hotel.service.EventRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventRecordServiceImpl implements EventRecordService {
    @Autowired
    private EventRecordRepository eventRecordRepository;

    @Override
    public EventRecordEntity save(EventRecordEntity entity) {
        return eventRecordRepository.save(entity);
    }

    @Override
    public List<EventRecordEntity> getAll(UserEntity entity) {
        return eventRecordRepository.findAllByUser(entity);
    }

    @Override
    public EventRecordEntity findByUserAndEvent(UserEntity user, EventEntity event) {
        return eventRecordRepository.findByUserAndEvent(user, event);
    }
}
