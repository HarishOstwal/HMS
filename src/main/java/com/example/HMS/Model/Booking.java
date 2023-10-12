package com.example.HMS.Model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@Entity
@Data
public class Booking {
    @Id private int bookingId;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date checkIn, checkOut;
    private int totalCost;
    @ManyToOne private UserT userT;
    @ManyToOne private Hotel hotel;
}
