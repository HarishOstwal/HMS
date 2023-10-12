package com.example.HMS.Controller;
import com.example.HMS.Model.Hotel;
import com.example.HMS.Service.HotelService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    private final HotelService hotelService;
    public HotelController(HotelService hotelService){
        this.hotelService=hotelService;
    }
    @GetMapping
    public List<Hotel> getAllHotels(@RequestParam(value = "latitude", required = false) Double latitude, @RequestParam(value = "longitude", required = false) Double longitude) {
        return hotelService.getAllHotels(latitude,longitude);
    }

    @GetMapping("/{hotelId}")
    public Hotel getHotelById(@PathVariable int hotelId) {
         return hotelService.getHotelById(hotelId);
    }
    @PostMapping
    public Hotel createHotel(@RequestBody Hotel hotel) {
         return hotelService.createHotel(hotel);
    }
    @PutMapping
    public Hotel updateHotel(@RequestBody Hotel hotel) {
        return hotelService.updateHotel(hotel);
    }
    @DeleteMapping("/{hotelId}")
    public String deleteHotel(@PathVariable int hotelId) {
        return hotelService.deleteHotel(hotelId);
    }
    @GetMapping("/{hotelId}/users")
    public List<Map<String, Object>> getUsersByHotelId(@PathVariable int hotelId) {
         return hotelService.getUsersByHotelId(hotelId);
    }
}
