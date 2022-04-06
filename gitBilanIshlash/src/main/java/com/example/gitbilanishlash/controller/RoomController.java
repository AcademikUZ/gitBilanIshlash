package com.example.gitbilanishlash.controller;

import com.example.gitbilanishlash.entity.Hotel;
import com.example.gitbilanishlash.entity.Room;
import com.example.gitbilanishlash.payload.ModelDto;
import com.example.gitbilanishlash.repository.HotelReopsitory;
import com.example.gitbilanishlash.repository.RoomReopsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class RoomController {


    @Autowired
    HotelReopsitory hotelReopsitory;

    @Autowired
    RoomReopsitory roomReopsitory;



    @GetMapping
    public Page<Room> roomPage(@RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return roomReopsitory.findAll(pageable);
    }

    @PostMapping
    public String addRoom(@RequestBody ModelDto modelDto) {
        try {

            if(modelDto == null)
                return "Roomga qiymat bering";
            Optional<Hotel> byIdHotel = hotelReopsitory.findById(modelDto.getHodelId());
            if(!byIdHotel.isPresent())
                return "Bunday hotel yo'q";

            Room room = new Room();
            room.setFloor(modelDto.getFloor());
            room.setSize(modelDto.getSize());
            room.setNumber(modelDto.getNumber());
            room.setHotel(byIdHotel.get());
            roomReopsitory.save(room);
            return "Room saqlandi!";
        } catch (Exception e) {
            return "Xatolik yuz berdi!";
        }
    }


    @PutMapping(value = "/{id}")
    public String editRoom(@PathVariable Long id, @RequestBody ModelDto modelDto) {
        try {
            if(modelDto == null)
                return "Roomga qiymat bering";
            Optional<Hotel> byIdHotel = hotelReopsitory.findById(modelDto.getHodelId());
            if(!byIdHotel.isPresent())
                return "Bunday hotel yo'q";

            Optional<Room> roomOptional = roomReopsitory.findById(id);
            if(!roomOptional.isPresent())
                return "Bunday room yo'q";
            Room room = roomOptional.get();
            room.setFloor(modelDto.getFloor());
            room.setSize(modelDto.getSize());
            room.setNumber(modelDto.getNumber());
            room.setHotel(byIdHotel.get());
            roomReopsitory.save(room);
            return "Room o'zgartirildi!";

        } catch (Exception e) {
            return "Xatolik yuz berdi!";
        }


    }


    @DeleteMapping(value = "/{id}")
    public String deleteRoom(@PathVariable Long id) {
        try {
            Optional<Room> roomOptional = roomReopsitory.findById(id);
            if (roomOptional.isPresent()) {
                roomReopsitory.deleteById(id);
                return "Room o'chirildi!";
            }
            return "Room mavjud emas!";

        } catch (Exception e) {
            return "Xatolik yuz berdi!";
        }

    }


}
