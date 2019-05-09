package com.edu.bsuir.hotel.hotel.controller;

import com.edu.bsuir.hotel.hotel.dto.EventRecordDTO;
import com.edu.bsuir.hotel.hotel.entity.EventEntity;
import com.edu.bsuir.hotel.hotel.entity.EventRecordEntity;
import com.edu.bsuir.hotel.hotel.entity.UserEntity;
import com.edu.bsuir.hotel.hotel.service.EventRecordService;
import com.edu.bsuir.hotel.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/event-records")
public class EventRecordController {
    @Autowired
    private EventRecordService eventRecordService;
    @Autowired
    private UserService userService;

    @PostMapping("")
    public EventRecordEntity saveRecord(@RequestBody EventRecordEntity entity){
        return eventRecordService.save(entity);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/list/user/{id}")
    public List<EventRecordEntity> getAll(@PathVariable int id){
        Optional<UserEntity> user = userService.findById(id);
        return eventRecordService.getAll(user.get());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping("/ex/user/{id}")
    public ResponseEntity<Boolean> ifExistByUser(@PathVariable int id, @RequestBody EventEntity eventEntity){
        Optional<UserEntity> user = userService.findById(id);
        EventRecordEntity recodr = eventRecordService.findByUserAndEvent(user.get(), eventEntity);
        if(recodr != null) return ResponseEntity.ok(true);
        else return ResponseEntity.ok(false);
    }
}
