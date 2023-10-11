package com.example.HMS.Controller;

import com.example.HMS.Entity.Booking;
import com.example.HMS.Entity.Hotel;
import com.example.HMS.Entity.UserT;
import com.example.HMS.Repository.BookingRepo;
import com.example.HMS.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepo userRepository;
    @Autowired
    private BookingRepo bookingRepo;
    @GetMapping
    public List<UserT> getAllUsers() {
        return userRepository.findAll();
    }
    @GetMapping("/{userId}")
    public UserT getUserById(@PathVariable int userId) {
        return userRepository.findById(userId).get();
    }
    @PostMapping
    public UserT createUser(@RequestBody UserT user) {
        return userRepository.save(user);
    }
    @PutMapping("/{userId}")
    public UserT updateUser(@PathVariable int userId, @RequestBody UserT user) {
        UserT existingUser = userRepository.findById(userId).get();
        existingUser.setUserId(user.getUserId());
        existingUser.setUserName(user.getUserName());
        existingUser.setUserEmail(user.getUserEmail());
        existingUser.setUserPhone(user.getUserPhone());
        existingUser.setUserAddress(user.getUserEmail());
        return userRepository.save(existingUser);
    }
    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable int userId) {
        try {
            userRepository.findById(userId).get();
            userRepository.deleteById(userId);
            return "User deleted successfully";
        } catch (Exception e) {
            return "User not found";
        }
    }

    @GetMapping("/{userId}/hotels")
    public List<Hotel> getHotelsByUserId(@PathVariable int userId){
        List<Booking>bookingList=bookingRepo.findAll();
        List<Hotel>hotelsByUserIdList=new ArrayList<Hotel>();
        for(Booking booking: bookingList){
            Hotel hotel=booking.getHotel();
            UserT user=booking.getUserT();
            if(userId != user.getUserId())continue;
            hotelsByUserIdList.add(hotel);
        }
        return hotelsByUserIdList;
    }

}