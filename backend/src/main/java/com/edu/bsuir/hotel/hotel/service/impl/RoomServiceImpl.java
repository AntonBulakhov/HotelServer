package com.edu.bsuir.hotel.hotel.service.impl;

import com.edu.bsuir.hotel.hotel.entity.RoomEntity;
import com.edu.bsuir.hotel.hotel.repository.RoomRepository;
import com.edu.bsuir.hotel.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Override
    public List<RoomEntity> findAllByRoomType(String roomType) {
        return roomRepository.findAllByRoomType(roomType);
    }

    @Override
    public RoomEntity save(RoomEntity roomEntity) {
        return roomRepository.save(roomEntity);
    }

    @Override
    public List<RoomEntity> requiredRooms(RoomEntity roomEntity) {
        return roomRepository.findAllByHasBabyBedAndHasTvAndHasBathAndHasFridgeAndMaxPersonsGreaterThanAndIsActiveAndRoomType(roomEntity.getHasBabyBed(),
                roomEntity.getHasTv(), roomEntity.getHasBath(), roomEntity.getHasFridge(), roomEntity.getMaxPersons(), (byte)1, roomEntity.getRoomType());
    }

    @Override
    public List<RoomEntity> findAll() {
        return (List<RoomEntity>) roomRepository.findAll();
    }

    @Override
    public Optional<RoomEntity> findById(int id) {
        return roomRepository.findById(id);
    }
}
