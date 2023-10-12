package com.example.HMS.Model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class UserT {
    @Id
    private int userId;
    private String userName, userEmail, userPhone, userAddress;
}
