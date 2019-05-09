package com.edu.bsuir.hotel.hotel.service.impl;

import com.edu.bsuir.hotel.hotel.entity.ReservationEntity;
import com.edu.bsuir.hotel.hotel.entity.UserEntity;
import com.edu.bsuir.hotel.hotel.repository.ReservationRepository;
import com.edu.bsuir.hotel.hotel.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public ReservationEntity save(ReservationEntity reservationEntity) {
        return reservationRepository.save(reservationEntity);
    }

    @Override
    public List<ReservationEntity> getAllByUser(UserEntity user) {
        return reservationRepository.getAllByUser(user);
    }

    @Override
    public List<ReservationEntity> getAllByDateToLessThan(Date date) {
        return reservationRepository.getAllByDateToLessThan(date);
    }

    @Override
    public void delete(ReservationEntity entity) {
        reservationRepository.delete(entity);
    }
}
