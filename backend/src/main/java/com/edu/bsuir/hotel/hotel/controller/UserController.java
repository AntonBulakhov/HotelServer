package com.edu.bsuir.hotel.hotel.controller;

import com.edu.bsuir.hotel.hotel.converter.UserToUserDTO;
import com.edu.bsuir.hotel.hotel.dto.UserDTO;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private RoomService roomService;

    private UserToUserDTO userConverter = new UserToUserDTO();

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/login/{login}")
    public UserEntity getUserByLogin(@PathVariable(name = "login") String login){
        return userService.findByLogin(login);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/id/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable int id){
        Optional<UserEntity> userEntity = userService.findById(id);
        if(userEntity.isPresent()){
            UserDTO userDTO = userConverter.convert(userEntity.get());
            return ResponseEntity.ok(userDTO);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/login/{login}/ex")
    public ResponseEntity<Boolean> ifUserExistsByLogin(@PathVariable String login){
        UserEntity userEntity = userService.findByLogin(login);
        if(userEntity != null){
            return ResponseEntity.ok(true);
        }else {
            return ResponseEntity.ok(false);
        }
    }

    @GetMapping("/email/{email}/ex")
    public ResponseEntity<Boolean> ifUserExistsByEmail(@PathVariable String email){
        UserEntity userEntity = userService.findByEmail(email);
        if(userEntity != null){
            return ResponseEntity.ok(true);
        }else {
            return ResponseEntity.ok(false);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity saveNewUser(@RequestBody UserEntity userEntity){
        UserEntity userEntity1 = userService.save(userEntity);
        if(userEntity1 != null){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/list")
    public ResponseEntity<List<UserDTO>> getUsersList(){
        List<UserEntity> userEntities = userService.findAllByBlocked((byte)0);
        if(!userEntities.isEmpty()){
            List<UserDTO> userDTOS = userConverter.convert(userEntities);
            return ResponseEntity.ok(userDTOS);
        }else {
            return ResponseEntity.ok(null);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/blacklist")
    public List<UserDTO> getUserBlackList(){
        List<UserEntity> list = userService.findAllByBlocked((byte)1);
        return userConverter.convert(list);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/block/{id}")
    public ResponseEntity blockUser(@PathVariable int id){
        Optional<UserEntity> op = userService.findById(id);
        UserEntity ent = op.get();
        ent.setBlocked((byte) 1);
        userService.save(ent);
        deleteReservation(ent);
        return ResponseEntity.ok().build();
    }

    private void deleteReservation(UserEntity ent){
        ArrayList<ReservationEntity> list = (ArrayList<ReservationEntity>) reservationService.getAllByUser(ent);

        for (ReservationEntity res:list) {
            RoomEntity room = res.getRoom();
            room.setIsActive((byte) 1);
            roomService.save(room);
            reservationService.delete(res);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/unblock/{id}")
    public ResponseEntity unblockUser(@PathVariable int id){
        Optional<UserEntity> op = userService.findById(id);
        UserEntity ent = op.get();
        ent.setBlocked((byte) 0);
        userService.save(ent);
        return ResponseEntity.ok().build();
    }
}
