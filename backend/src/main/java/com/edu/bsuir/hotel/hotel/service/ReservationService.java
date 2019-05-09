package com.edu.bsuir.hotel.hotel.service;

import com.edu.bsuir.hotel.hotel.entity.ReservationEntity;
import com.edu.bsuir.hotel.hotel.entity.UserEntity;

import java.sql.Date;
import java.util.List;

public interface ReservationService {
    ReservationEntity save(ReservationEntity reservationEntity);
    List<ReservationEntity> getAllByUser(UserEntity user);
    List<ReservationEntity> getAllByDateToLessThan(Date date);
    void delete(ReservationEntity entity);
}
