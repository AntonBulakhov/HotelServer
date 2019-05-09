package com.edu.bsuir.hotel.hotel.repository;

import com.edu.bsuir.hotel.hotel.entity.ReservationEntity;
import com.edu.bsuir.hotel.hotel.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;


public interface ReservationRepository extends CrudRepository<ReservationEntity, Long> {
    List<ReservationEntity> getAllByUser(UserEntity user);
    List<ReservationEntity> getAllByDateToLessThan(Date date);
}
