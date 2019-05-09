package com.edu.bsuir.hotel.hotel.controller;

import com.edu.bsuir.hotel.hotel.converter.ReviewToReviewDTO;
import com.edu.bsuir.hotel.hotel.dto.ReviewDTO;
import com.edu.bsuir.hotel.hotel.entity.ReservationEntity;
import com.edu.bsuir.hotel.hotel.entity.ReviewEntity;
import com.edu.bsuir.hotel.hotel.entity.UserEntity;
import com.edu.bsuir.hotel.hotel.service.ReservationService;
import com.edu.bsuir.hotel.hotel.service.ReviewService;
import com.edu.bsuir.hotel.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private UserService userService;
    @Autowired
    private ReservationService reservationService;

    private ReviewToReviewDTO revConverter = new ReviewToReviewDTO();

    @GetMapping("")
    public List<ReviewDTO> getAll(){
        List<ReviewEntity> entities = reviewService.findAll();
        if(entities != null){
            return revConverter.convert(entities);
        }else {
            return null;
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/user/{id}")
    public ResponseEntity<Boolean> isCommented(@PathVariable int id){
        Optional<UserEntity> user = userService.findById(id);
        List<ReservationEntity> list = reservationService.getAllByUser(user.get());

        ReviewEntity reviewEntity = reviewService.findByReservations(list);
        if(reviewEntity != null){
            return ResponseEntity.ok(true);
        }else {
            return ResponseEntity.ok(false);
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping("/user/{id}")
    public ResponseEntity<Boolean> comment(@PathVariable int id, @RequestBody ReviewEntity entity){
        Optional<UserEntity> user = userService.findById(id);
        List<ReservationEntity> list = reservationService.getAllByUser(user.get());
        entity.setReservation(list.get(list.size()-1));
        ReviewEntity res = reviewService.save(entity);
        if(res != null) return ResponseEntity.ok(true);
        else return ResponseEntity.ok(false);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/available/user/{id}")
    public ResponseEntity<Boolean> canComment(@PathVariable int id){
        Optional<UserEntity> user = userService.findById(id);
        List<ReservationEntity> list = reservationService.getAllByUser(user.get());

        if(list.isEmpty()) return ResponseEntity.ok(false);
        else return ResponseEntity.ok(true);
    }
}
