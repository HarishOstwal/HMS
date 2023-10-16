package com.example.HMS.domain.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {
    @Id
    private int hotelId;
    private String hotelName, hotelType, hotelAddress, hotelDescription;
    private Double hotelLat, hotelLong;
}

