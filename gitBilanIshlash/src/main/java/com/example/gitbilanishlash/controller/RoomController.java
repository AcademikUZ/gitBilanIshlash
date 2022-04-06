package com.example.gitbilanishlash.controller;

import com.example.gitbilanishlash.entity.Hotel;
import com.example.gitbilanishlash.repository.HotelReopsitory;
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


    @GetMapping
    public Page<Hotel> hotelList(@RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return hotelReopsitory.findAll(pageable);
    }

    @PostMapping
    public String addhotel(@RequestBody Hotel hotel) {
        try {
            if(hotel == null)
                return "Hotelga qiymat bering";
            hotelReopsitory.save(hotel);
            return "Hotel saqlandi!";
        } catch (Exception e) {
            return "Xatolik yuz berdi!";
        }
    }


    @PutMapping(value = "/{id}")
    public String editAddress(@PathVariable Long id, @RequestBody Hotel modelDto) {
        try {
            Optional<Hotel> optionalhotel = hotelReopsitory.findById(id);
            if (optionalhotel.isPresent()) {
                Hotel hotel = optionalhotel.get();
                hotel.setNameHotel(modelDto.getNameHotel());
                hotelReopsitory.save(hotel);
                return "Hotel o'zgartirildi!";
            } else return "Bunday hotel yo'q!";

        } catch (Exception e) {
            return "Xatolik yuz berdi!";
        }


    }


    @DeleteMapping(value = "/{id}")
    public String editAddress(@PathVariable Long id) {
        try {
            Optional<Hotel> optionalhotel = hotelReopsitory.findById(id);
            if (optionalhotel.isPresent()) {
                hotelReopsitory.deleteById(id);
                return "Hotel o'chirildi!";
            }
            return "Hotel mavjud emas!";

        } catch (Exception e) {
            return "Xatolik yuz berdi!";
        }

    }


}
