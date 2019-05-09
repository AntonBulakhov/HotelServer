package com.edu.bsuir.hotel.hotel.controller;

import com.edu.bsuir.hotel.hotel.entity.RoomEntity;
import com.edu.bsuir.hotel.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping("/type/{type}")
    public List<RoomEntity> getRoomByType(@PathVariable String type){
        return roomService.findAllByRoomType(type);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("")
    public RoomEntity saveRoom(@RequestBody RoomEntity roomEntity){
        return roomService.save(roomEntity);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Boolean> ifExists(@PathVariable int id){
        Optional<RoomEntity> room = roomService.findById(id);

        if(room.isPresent()) return ResponseEntity.ok(true);
        else return ResponseEntity.ok(false);
    }

    @GetMapping("/list")
    public List<RoomEntity> getAllRooms(){
        return roomService.findAll();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping("/req")
    public ResponseEntity<RoomEntity> getRequired(@RequestBody RoomEntity roomEntity){
        List<RoomEntity> rooms = roomService.requiredRooms(roomEntity);
        if(!rooms.isEmpty()){
            return ResponseEntity.ok(rooms.get(0));
        }else {
            return ResponseEntity.ok(null);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/deactive/id/{id}")
    public RoomEntity deactivate(@PathVariable int id){
        Optional<RoomEntity> room = roomService.findById(id);
        RoomEntity entity = room.get();
        entity.setIsActive((byte)0);
        return roomService.save(entity);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/active/id/{id}")
    public RoomEntity activate(@PathVariable int id){
        Optional<RoomEntity> room = roomService.findById(id);
        RoomEntity entity = room.get();
        entity.setIsActive((byte)1);
        return roomService.save(entity);
    }
}
