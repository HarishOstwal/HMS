package com.example.HMS.Entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@Entity
public class Booking {
    @Id
    private int bookingId;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date checkIn, checkOut;
    private int totalCost;
    @ManyToOne
    private UserT userT;
    @ManyToOne
    private Hotel hotel;
    public UserT getUserT() {
        return userT;
    }
    public void setUserT(UserT userT) {
        this.userT = userT;
    }
    public Hotel getHotel() {
        return hotel;
    }
    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
    public int getBookingId() {
        return bookingId;
    }
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }
    public Date getCheckIn() {
        return checkIn;
    }
    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }
    public Date getCheckOut() {
        return checkOut;
    }
    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }
    public int getTotalCost() {
        return totalCost;
    }
    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }
}
