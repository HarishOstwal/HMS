package com.example.HMS.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Hotel {
    @Id
    private int hotelId;
    private String hotelName, hotelType, hotelAddress, hotelDescription;
    private float hotelLat, hotelLong;
}

