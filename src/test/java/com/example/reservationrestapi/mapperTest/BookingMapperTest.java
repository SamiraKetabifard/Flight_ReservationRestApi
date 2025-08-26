package com.example.reservationrestapi.mapperTest;

import com.example.reservationrestapi.dto.BookingDto;
import com.example.reservationrestapi.entity.Booking;
import com.example.reservationrestapi.entity.Flight;
import com.example.reservationrestapi.entity.User;
import com.example.reservationrestapi.mapper.BookingMapper;
import com.example.reservationrestapi.mapper.BookingMapperImpl;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BookingMapperTest {

    private final BookingMapper bookingMapper = new BookingMapperImpl();

    @Test
    void testMapEntityToDTO() {
        User user = new User();
        user.setUserId(1);
        user.setUsername("samira");

        Flight flight = new Flight();
        flight.setFlightId(100L);
        flight.setFlightNumber("FL123");

        Booking booking = new Booking();
        booking.setId(10);
        booking.setUser(user);
        booking.setFlight(flight);
        booking.setSeatNumber("A12");
        //act
        BookingDto dto = bookingMapper.toDTO(booking);
        //assert
        assertEquals(10, dto.getBookingId());
        assertEquals(1, dto.getUserId());
        assertEquals(100L, dto.getFlightId());
        assertEquals("A12", dto.getSeatNumber());
    }
    @Test
    void testMapDtoToEntity() {
        BookingDto dto = new BookingDto();
        dto.setBookingId(10);
        dto.setUserId(1);
        dto.setFlightId(100L);
        dto.setSeatNumber("B34");

        User user = new User();
        user.setUserId(1);
        user.setUsername("Mina Nosrati");

        Flight flight = new Flight();
        flight.setFlightId(100L);
        //act
        Booking booking = bookingMapper.toEntity(dto, user, flight);
        //assert
        assertEquals(10, booking.getId());
        assertEquals("Mina Nosrati", booking.getUser().getUsername());
        assertEquals(100L, booking.getFlight().getFlightId());
        assertEquals("B34", booking.getSeatNumber());
    }
}