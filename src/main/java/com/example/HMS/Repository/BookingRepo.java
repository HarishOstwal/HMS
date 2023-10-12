package com.example.HMS.Repository;

import com.example.HMS.Model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookingRepo extends JpaRepository<Booking,Integer> {

}
