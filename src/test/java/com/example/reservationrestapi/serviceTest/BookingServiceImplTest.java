package com.example.reservationrestapi.serviceTest;

import com.example.reservationrestapi.dto.BookingDto;
import com.example.reservationrestapi.entity.Booking;
import com.example.reservationrestapi.entity.Flight;
import com.example.reservationrestapi.entity.User;
import com.example.reservationrestapi.exception.ResourceNotFoundException;
import com.example.reservationrestapi.mapper.BookingMapper;
import com.example.reservationrestapi.repository.BookingRepository;
import com.example.reservationrestapi.repository.FlightRepository;
import com.example.reservationrestapi.repository.UserRepository;
import com.example.reservationrestapi.service.Impl.BookingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingServiceImplTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private BookingMapper bookingMapper;

    @InjectMocks
    private BookingServiceImpl bookingService;

    private BookingDto bookingDto;
    private User user;
    private Flight flight;

    @BeforeEach
    void setUp() {
        bookingDto = new BookingDto();
        bookingDto.setBookingId(1);
        bookingDto.setUserId(1);
        bookingDto.setFlightId(100L);
        bookingDto.setSeatNumber("A12");

        user = new User();
        user.setUserId(1);
        user.setUsername("samira");

        flight = new Flight();
        flight.setFlightId(100L);
        flight.setFlightNumber("FL123");
    }
    @Test
    void testGetBookingById_HappyPath() {
        Booking booking = new Booking();
        booking.setId(1);
        booking.setUser(user);
        booking.setFlight(flight);
        when(bookingRepository.findById(1)).thenReturn(Optional.of(booking));
        when(bookingMapper.toDTO(booking)).thenReturn(bookingDto);

        BookingDto result = bookingService.getBookingById(1);

        assertEquals(1, result.getBookingId());
        assertEquals(1, result.getUserId());
    }
    @Test
    void testGetBookingById_NotFound() {
        when(bookingRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,
                () -> bookingService.getBookingById(1));
    }
    @Test
    void testSaveBooking_HappyPath() {
        BookingDto minaBooking = new BookingDto();
        minaBooking.setUserId(2);
        minaBooking.setFlightId(101L);
        minaBooking.setSeatNumber("B34");

        User mina = new User();
        mina.setUserId(2);
        mina.setUsername("Mina Nosrati");

        Flight flight2 = new Flight();
        flight2.setFlightId(101L);

        Booking booking = new Booking();
        booking.setUser(mina);
        booking.setFlight(flight2);

        when(userRepository.findById(2)).thenReturn(Optional.of(mina));
        when(flightRepository.findById(101L)).thenReturn(Optional.of(flight2));
        when(bookingMapper.toEntity(minaBooking, mina, flight2)).thenReturn(booking);
        when(bookingRepository.save(booking)).thenReturn(booking);
        when(bookingMapper.toDTO(booking)).thenReturn(minaBooking);

        BookingDto result = bookingService.saveBooking(minaBooking);

        assertEquals(2, result.getUserId());
    }
    @Test
    void testSaveBooking_UserNotFound() {
        when(userRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,
                () -> bookingService.saveBooking(bookingDto));
    }
    @Test
    void testSaveBooking_FlightNotFound() {
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(flightRepository.findById(100L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,
                () -> bookingService.saveBooking(bookingDto));
    }
}
