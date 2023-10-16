package com.example.HMS.adapters.repository;

import com.example.HMS.domain.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookingRepo extends JpaRepository<Booking,Integer> {

}
