package com.example.HMS.Functions;

import com.example.HMS.Entity.Hotel;
import java.util.List;

public class DistanceCalculator {
    public List<Hotel> getHotelsByDistance(List<Hotel> hotels, double latitude, double longitude) {
        hotels.sort((hotel1, hotel2) -> {
            double distance1=Math.sqrt(Math.pow(hotel1.getHotelLat()-latitude,2)+Math.pow(hotel1.getHotelLong()-longitude,2));
            double distance2=Math.sqrt(Math.pow(hotel2.getHotelLat()-latitude,2)+Math.pow(hotel2.getHotelLong()-longitude,2));
            if(distance1 < distance2) return -1;
            else if(distance1 > distance2) return 1;
            return 0;
        });
        return hotels;
    }
}
