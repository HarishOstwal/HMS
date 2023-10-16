package com.example.HMS;

import com.example.HMS.adapters.service.UserService;
import com.example.HMS.domain.models.Hotel;
import com.example.HMS.domain.models.UserT;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserServiceTest {
    @Mock
    Jdbi jdbi;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setup(){
        Handle handle = Mockito.mock(Handle.class);
        Mockito.when(jdbi.open()).thenReturn(handle);
    }

    @Test
    public void getAllUserTest(){
        List<UserT>Users=new ArrayList<>();
        Users.add(new UserT(1000, "123 Main Street", "user1@example.com", "John Doe 1", "+1-123-456-7890-1"));
        Users.add(new UserT(2000, "123 Main Street", "user1@example.com", "John Doe 1", "+1-123-456-7890-1"));
        Mockito.when(jdbi.withHandle(Mockito.any())).thenReturn(Users);
        assertEquals(Users,userService.getAllUsers());
    }

    @Test
    public void getAllUsersByIdTest(){
        int userId=1000;
        UserT expectedUser=new UserT(1000, "123 Main Street", "user1@example.com", "John Doe 1", "+1-123-456-7890-1");
        Mockito.when(jdbi.withHandle(Mockito.any())).thenReturn(expectedUser);
        UserT user = userService.getUsersById(userId);
        assertEquals(expectedUser, user);
    }

    @Test
    public void createUserTest(){
        UserT expectedUser=new UserT(1000, "123 Main Street", "user1@example.com", "John Doe 1", "+1-123-456-7890-1");
        Mockito.when(jdbi.withHandle(Mockito.any())).thenReturn(expectedUser);
        UserT user=userService.createUser(expectedUser);
        assertEquals(expectedUser,user);
    }

    @Test
    public void updateUserTest(){
        UserT inputUser=new UserT(1000, "123 Main Street", "user1@example.com", "John Doe 1", "+1-123-456-7890-1");
        Mockito.when(jdbi.withHandle(Mockito.any())).thenReturn(inputUser);
        UserT user=userService.updateUser(inputUser);
        assertEquals(inputUser,user);
    }

    @Test
    public void deleteUserTest(){
        int numRowsAffected = 1;
        Mockito.when(jdbi.withHandle(Mockito.any())).thenReturn(numRowsAffected);
        int userId = 2000;
        String res = "User with ID " + String.valueOf(userId) + " deleted successfully.";
        String result = userService.deleteUser(userId);
        assertEquals(res, result);
    }
}
