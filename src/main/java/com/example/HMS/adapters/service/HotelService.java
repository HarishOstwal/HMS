package com.example.HMS.adapters.service;

import com.example.HMS.adapters.function.DistanceCalculator;
import com.example.HMS.domain.models.Hotel;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;


@Service
public class HotelService {
    private final Jdbi jdbi;
    public HotelService(Jdbi jdbi){
        this.jdbi=jdbi;
    }
    public List<Hotel>getAllHotels(Double latitude, Double longitude){
        List<Hotel>hotels= jdbi.withHandle(handle -> {
            return handle.createQuery("select * from Hotel")
                    .mapToBean(Hotel.class)
                    .list();
        });
        if(latitude != null && longitude != null){
            DistanceCalculator distanceCalculator=new DistanceCalculator();
            return distanceCalculator.getHotelsByDistance(hotels,latitude,longitude);
        }
        return hotels;
    }
    public Hotel getHotelById(int hotelId){
        return jdbi.withHandle(handle -> {
            return handle.createQuery("select * from Hotel where hotel_id= :hotelId")
                    .bind("hotelId",hotelId)
                    .mapToBean(Hotel.class)
                    .findFirst()
                    .orElse(null);
        });
    }
    public Hotel createHotel(Hotel hotel){
        return jdbi.withHandle(handle -> {
            handle.createUpdate("INSERT INTO Hotel (hotel_id, hotel_address, hotel_description, hotel_name, hotel_type, hotel_lat, hotel_long) " +
                            "VALUES (:hotelId, :hotelAddress, :hotelDescription, :hotelName, :hotelType, :hotelLat, :hotelLong)")
                    .bindBean(hotel)
                    .execute();
            return hotel;
        });
    }
    public Hotel updateHotel(Hotel hotel){
        return jdbi.withHandle(handle -> {
            int numRowsAffected=handle.createUpdate("update Hotel " +
                            "set hotel_address = :hotelAddress, " +
                            "hotel_description = :hotelDescription, " +
                            "hotel_name = :hotelName, " +
                            "hotel_type = :hotelType, " +
                            "hotel_lat = :hotelLat, " +
                            "hotel_long = :hotelLong " +
                            "where hotel_id = :hotelId")
                    .bindBean(hotel)
                    .execute();

            if(numRowsAffected>0)return hotel;
            else return null;
        });
    }
    public String deleteHotel(int hotelId) {
        int numRowsAffected=jdbi.withHandle(handle -> {
            return handle.createUpdate("delete from Hotel where hotel_id = :hotelId")
                    .bind("hotelId", hotelId)
                    .execute();
        });

        if(numRowsAffected>0)return "Hotel with ID " + hotelId + " deleted successfully.";
        else return "Hotel with ID " + hotelId + " not found or couldn't be deleted.";
    }
    public List<Map<String, Object>> getUsersByHotelId(int hotelId){
        return jdbi.withHandle(handle -> {
            return handle.createQuery("select b.booking_id as bookingId, u.user_id as userId, u.user_name as userName, u.user_email as userEmail " +
                    "from Booking b, UserT u " +
                    " where b.usert_user_id = u.user_id and b.hotel_hotel_id = :hotelId")
                    .bind("hotelId",hotelId)
                    .mapToMap()
                    .list();
        });
    }
}
