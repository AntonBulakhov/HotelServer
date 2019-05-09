package com.edu.bsuir.hotel.hotel.monitor;

import com.edu.bsuir.hotel.hotel.entity.ReservationEntity;
import com.edu.bsuir.hotel.hotel.entity.RoomEntity;
import com.edu.bsuir.hotel.hotel.service.EventRecordService;
import com.edu.bsuir.hotel.hotel.service.ReservationService;
import com.edu.bsuir.hotel.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

@Component
public class RoomMonitor {

    @Autowired
    private RoomService roomService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private EventRecordService eventRecordService;

    @Scheduled(cron = "0 0 1 * * ?")
//    @Scheduled(fixedDelay = 5000)
    public void monitorRooms(){
        Calendar calendar = Calendar.getInstance();
        Date today = new Date(calendar.getTime().getTime());

        ArrayList<ReservationEntity> reserv = (ArrayList<ReservationEntity>) reservationService.getAllByDateToLessThan(today);

        for (ReservationEntity res:reserv) {
            RoomEntity room = res.getRoom();
            room.setIsActive((byte) 1);
            roomService.save(room);
            reservationService.delete(res);
        }
    }
}
