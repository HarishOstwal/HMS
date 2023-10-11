package com.example.HMS.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
@Entity
public class Hotel {
    @Id
    private int hotelId;
    private String hotelName;
    private String hotelType;
    private String hotelAddress;
    private String hotelDescription;
    private float hotelLat;
    private float hotelLong;
    public float getHotelLat() {
        return hotelLat;
    }
    public void setHotelLat(float hotelLat) {
        this.hotelLat = hotelLat;
    }
    public float getHotelLong() {
        return hotelLong;
    }
    public void setHotelLong(float hotelLong) {
        this.hotelLong = hotelLong;
    }
    public int getHotelId() {
        return hotelId;
    }
    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }
    public String getHotelName() {
        return hotelName;
    }
    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }
    public String getHotelType() {
        return hotelType;
    }
    public void setHotelType(String hotelType) {
        this.hotelType = hotelType;
    }
    public String getHotelAddress() {
        return hotelAddress;
    }
    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }
    public String getHotelDescription() {
        return hotelDescription;
    }
    public void setHotelDescription(String hotelDescription) {
        this.hotelDescription = hotelDescription;
    }
}

