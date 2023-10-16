package com.example.HMS;

import com.example.HMS.adapters.service.HotelService;
import com.example.HMS.domain.models.Hotel;
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
public class HotelServiceTest {
    @Mock
    private Jdbi jdbi;

    @InjectMocks
    private HotelService hotelService;

    @BeforeEach
    public void setUp() {
        Handle handle = Mockito.mock(Handle.class);
        Mockito.when(jdbi.open()).thenReturn(handle);
    }

    @Test
    public void getAllHotelsTest() {
        List<Hotel> Hotels = new ArrayList<>();
        Hotels.add(new Hotel(1000, "555 Riverfront Avenue", "Riverside getaway with scenic views", "Riverside Retreat", "Luxury", 41.8781, -87.6298));
        Hotels.add(new Hotel(2000, "555 Riverfront Avenue", "Riverside getaway with scenic views", "Riverside Retreat", "Luxury", 41.8781, -87.6298));
        Mockito.when(jdbi.withHandle(Mockito.any())).thenReturn(Hotels);
        assertEquals(Hotels,hotelService.getAllHotels(null,null));
    }

    @Test
    public void getAllHotelsByLatAndLongTest() {
        List<Hotel> Hotels = new ArrayList<>();
        Hotels.add(new Hotel(1000, "555 Riverfront Avenue", "Riverside getaway with scenic views", "Riverside Retreat", "Luxury", 41.8781, -87.6298));
        Hotels.add(new Hotel(2000, "555 Riverfront Avenue", "Riverside getaway with scenic views", "Riverside Retreat", "Luxury", 11.8781, -27.6298));
        Mockito.when(jdbi.withHandle(Mockito.any())).thenReturn(Hotels);
        assertEquals(Hotels.get(1),hotelService.getAllHotels(11.8781,-27.6298).get(0));
    }

    @Test
    public void getHotelByIdTest() {
        int hotelId = 1000;
        Hotel expectedHotel = new Hotel(hotelId, "555 Riverfront Avenue", "Riverside getaway with scenic views", "Riverside Retreat", "Luxury", 41.8781, -87.6298);
        Mockito.when(jdbi.withHandle(Mockito.any())).thenReturn(expectedHotel);
        Hotel hotel = hotelService.getHotelById(hotelId);
        assertEquals(expectedHotel, hotel);
    }

    @Test
    public void createHotelTest(){
        Hotel expectedHotel = new Hotel(2000, "555 Riverfront Avenue", "Riverside getaway with scenic views", "Riverside Retreat", "Luxury", 41.8781, -87.6298);
        Mockito.when(jdbi.withHandle(Mockito.any())).thenReturn(expectedHotel);
        Hotel hotel=hotelService.createHotel(expectedHotel);
        assertEquals(expectedHotel,hotel);
    }

    @Test
    public void updateHotelTest() {
        Hotel inputHotel = new Hotel(2000, "555 Riverfront Avenue", "Riverside getaway with scenic views", "Riverside Retreat", "Luxury", 41.8781, -87.6298);
        Mockito.when(jdbi.withHandle(Mockito.any())).thenReturn(inputHotel);
        Hotel hotel=hotelService.updateHotel(inputHotel);
        assertEquals(inputHotel,hotel);
    }


    @Test
    public void deleteHotelTest() {
        int numRowsAffected = 1;
        Mockito.when(jdbi.withHandle(Mockito.any())).thenReturn(numRowsAffected);
        int hotelId = 2000;
        String res = "Hotel with ID " + String.valueOf(hotelId) + " deleted successfully.";
        String result = hotelService.deleteHotel(hotelId);
        assertEquals(res, result);
    }
}


