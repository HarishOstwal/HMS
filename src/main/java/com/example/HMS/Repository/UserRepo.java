package com.example.HMS.Repository;

import com.example.HMS.Model.UserT;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserT,Integer> {

}
