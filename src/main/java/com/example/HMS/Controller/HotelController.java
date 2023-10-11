package com.example.HMS.Controller;
import com.example.HMS.Entity.Booking;
import com.example.HMS.Entity.Hotel;
import com.example.HMS.Entity.UserT;
import com.example.HMS.Functions.DistanceCalculator;
import com.example.HMS.Repository.BookingRepo;
import com.example.HMS.Repository.HotelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    @Autowired
    private HotelRepo userRepository;

    @Autowired
    private BookingRepo bookingRepo;

    @GetMapping
    public List<Hotel> getAllHotels(@RequestParam(value = "latitude", required = false) Double latitude,
                                    @RequestParam(value = "longitude", required = false) Double longitude
    ) {
        List<Hotel> hotelList=userRepository.findAll();
        if(latitude != null  && longitude != null){
            DistanceCalculator distanceCalculator=new DistanceCalculator();
            hotelList=distanceCalculator.getHotelsByDistance(hotelList,latitude,longitude);
            return hotelList;
        }
        return hotelList;
    }

    @GetMapping("/{hotelId}")
    public Hotel getHotelById(@PathVariable int hotelId) {
        return userRepository.findById(hotelId).get();
    }

    @PostMapping
    public Hotel createHotel(@RequestBody Hotel hotel) {
        return userRepository.save(hotel);
    }

    @PutMapping("/{hotelId}")
    public Hotel updateUser(@PathVariable int hotelId, @RequestBody Hotel hotel) {
        Hotel existingUser = userRepository.findById(hotelId).get();
        existingUser.setHotelId(hotel.getHotelId());
        existingUser.setHotelName(hotel.getHotelName());
        existingUser.setHotelType(hotel.getHotelType());
        existingUser.setHotelAddress(hotel.getHotelAddress());
        existingUser.setHotelDescription(hotel.getHotelDescription());
        return userRepository.save(existingUser);
    }

    @DeleteMapping("/{hotelId}")
    public String deleteHotel(@PathVariable int hotelId) {
        try {
            userRepository.findById(hotelId).get();
            userRepository.deleteById(hotelId);
            return "Hotel deleted successfully";
        } catch (Exception e) {
            return "Hotel not found";
        }
    }

    @GetMapping("/{hotelId}/users")
    public List<UserT> getUsersByHotelId(@PathVariable int hotelId){
        List<Booking>bookingList=bookingRepo.findAll();
        List<UserT>usersByHotelIdList=new ArrayList<UserT>();
        for(Booking booking: bookingList){
            Hotel hotel=booking.getHotel();
            UserT user=booking.getUserT();
            int id=hotel.getHotelId();
            if(id != hotelId)continue;
            usersByHotelIdList.add(user);
        }
        return  usersByHotelIdList;
    }

}
