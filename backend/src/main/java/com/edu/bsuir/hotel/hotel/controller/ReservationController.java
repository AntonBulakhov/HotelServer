package com.edu.bsuir.hotel.hotel.controller;

import com.edu.bsuir.hotel.hotel.entity.ReservationEntity;
import com.edu.bsuir.hotel.hotel.entity.RoomEntity;
import com.edu.bsuir.hotel.hotel.entity.UserEntity;
import com.edu.bsuir.hotel.hotel.service.ReservationService;
import com.edu.bsuir.hotel.hotel.service.RoomService;
import com.edu.bsuir.hotel.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping("")
    public ResponseEntity saveReservation(@RequestBody ReservationEntity reservationEntity){
        RoomEntity entity = reservationEntity.getRoom();
        entity.setIsActive((byte)0);
        roomService.save(entity);

        ReservationEntity res = reservationService.save(reservationEntity);
        if(res != null) {
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/list/user/{id}")
    public List<ReservationEntity> getReservations(@PathVariable int id){
        Optional<UserEntity> user = userService.findById(id);

        List<ReservationEntity> list = reservationService.getAllByUser(user.get());

        if(!list.isEmpty()){
            return list;
        }else {
            return null;
        }
    }
}
