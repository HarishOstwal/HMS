package com.example.HMS.adapters.controller;

import com.example.HMS.domain.models.UserT;
import com.example.HMS.adapters.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService){
        this.userService=userService;
    }
    @GetMapping
    public List<UserT> getAllUsers() {
        return userService.getAllUsers();
    }
    @GetMapping("/{userId}")
    public UserT getUserById(@PathVariable int userId) {
        return userService.getUsersById(userId);
    }
    @PostMapping
    public UserT createUser(@RequestBody UserT user) {
        return userService.createUser(user);
    }
    @PutMapping
    public UserT updateUser(@RequestBody UserT user) {
       return userService.updateUser(user);
    }
    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable int userId) {
        return userService.deleteUser(userId);
    }
    @GetMapping("/{userId}/hotels")
    public List<Map<String,Object>> getHotelsByUserId(@PathVariable int userId){
       return userService.getHotelsByUserId(userId);
    }
}