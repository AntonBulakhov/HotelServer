package com.edu.bsuir.hotel.hotel.controller;

import com.edu.bsuir.hotel.hotel.dto.EventRecordDTO;
import com.edu.bsuir.hotel.hotel.entity.EventRecordEntity;
import com.edu.bsuir.hotel.hotel.service.EventRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/event-records")
public class EventRecordController {
    @Autowired
    private EventRecordService eventRecordService;

    @PostMapping("")
    private EventRecordEntity saveRecord(@RequestBody EventRecordEntity entity){
        return eventRecordService.save(entity);
    }
}
