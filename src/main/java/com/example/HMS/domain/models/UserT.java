package com.example.HMS.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserT {
    @Id
    private int userId;
    private String userName, userEmail, userPhone, userAddress;
}
