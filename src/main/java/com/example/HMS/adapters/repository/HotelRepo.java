package com.example.HMS.adapters.repository;

import com.example.HMS.domain.models.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepo extends JpaRepository<Hotel,Integer> {
}
