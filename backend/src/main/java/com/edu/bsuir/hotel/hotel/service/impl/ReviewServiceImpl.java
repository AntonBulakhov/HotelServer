package com.edu.bsuir.hotel.hotel.service.impl;

import com.edu.bsuir.hotel.hotel.entity.ReservationEntity;
import com.edu.bsuir.hotel.hotel.entity.ReviewEntity;
import com.edu.bsuir.hotel.hotel.repository.ReviewRepository;
import com.edu.bsuir.hotel.hotel.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public List<ReviewEntity> findAll() {
        return (List<ReviewEntity>) reviewRepository.findAll();
    }

    @Override
    public ReviewEntity findByReservations(List<ReservationEntity> list) {
        return reviewRepository.getByReservationIn(list);
    }

    @Override
    public ReviewEntity save(ReviewEntity entity) {
        return reviewRepository.save(entity);
    }
}
