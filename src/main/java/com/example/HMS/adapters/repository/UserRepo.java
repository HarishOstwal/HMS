package com.example.HMS.adapters.repository;

import com.example.HMS.domain.models.UserT;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserT,Integer> {

}
