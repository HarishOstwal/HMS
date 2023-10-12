package com.example.HMS.Service;
import com.example.HMS.Model.UserT;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {
    private final Jdbi jdbi;
    public UserService(Jdbi jdbi){
        this.jdbi=jdbi;
    }
    public List<UserT> getAllUsers() {
        return jdbi.withHandle(handle -> {
            return handle.createQuery("select * from UserT")
                    .mapToBean(UserT.class)
                    .list();
        });
    }

    public UserT getUsersById(int userId){
        return jdbi.withHandle(handle -> {
            return handle.createQuery("select * from UserT where user_id= :userId")
                    .bind("userId",userId)
                    .mapToBean(UserT.class)
                    .findFirst()
                    .orElse(null);
        });
    }

    public  UserT createUser(UserT user){
        jdbi.withHandle(handle -> {
            return handle.createUpdate("insert into UserT (user_id, user_name, user_email, user_phone, user_address) values " +
                    "(:userId, :userName, :userEmail, :userPhone, :userAddress)")
                    .bindBean(user)
                    .execute();
        });
        return user;
    }

    public UserT updateUser(UserT user) {
        int numRowsAffected = jdbi.withHandle(handle -> {
            return handle.createUpdate("update UserT set " +
                            "user_name = :userName, " +
                            "user_email = :userEmail, " +
                            "user_phone = :userPhone, " +
                            "user_address = :userAddress ")
                    .bindBean(user)
                    .execute();
        });

        if (numRowsAffected > 0) {
            return user;
        } else {
            return null;
        }
    }
    public String deleteUser(int userId) {
        int numRowsAffected=jdbi.withHandle(handle -> {
            return handle.createUpdate("delete from UserT where user_id = :userId")
                    .bind("userId", userId)
                    .execute();
        });

        if(numRowsAffected>0)return "Hotel with ID " + userId + " deleted successfully.";
        else return "Hotel with ID " + userId + " not found or couldn't be deleted.";
    }

    public List<Map<String,Object>>getHotelsByUserId(int userId){
        return jdbi.withHandle(handle -> {
           return handle.createQuery("select b.booking_id as bookingId, h.hotel_id as hotelId, h.hotel_name as hotelName from booking b, hotel h where b.hotel_hotel_id=h.hotel_id and b.usert_user_id = :userId")
                   .bind("userId",userId)
                   .mapToMap()
                   .list();
        });
    }
}
