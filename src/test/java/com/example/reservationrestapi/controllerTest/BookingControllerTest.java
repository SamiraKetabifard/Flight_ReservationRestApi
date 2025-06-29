package com.example.reservationrestapi.controllerTest;

import com.example.reservationrestapi.controller.BookingController;
import com.example.reservationrestapi.dto.BookingDto;
import com.example.reservationrestapi.exception.ResourceNotFoundException;
import com.example.reservationrestapi.service.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingControllerTest {

    @Mock
    private BookingService bookingService;

    @InjectMocks
    private BookingController bookingController;

    private BookingDto bookingDto;

    @BeforeEach
    void setUp() {
        bookingDto = new BookingDto();
        bookingDto.setBookingId(1);
        bookingDto.setUserId(1);
        bookingDto.setFlightId(100L);
        bookingDto.setSeatNumber("A12");
    }
    @Test
    void testGetBooking_HappyPath() {
        when(bookingService.getBookingById(1)).thenReturn(bookingDto);
        ResponseEntity<BookingDto> response = bookingController.getBooking(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().getUserId());
        assertEquals("A12", response.getBody().getSeatNumber());
    }
    @Test
    void testGetBooking_NotFound() {
        when(bookingService.getBookingById(1))
                .thenThrow(new ResourceNotFoundException("Booking not found"));
        assertThrows(ResourceNotFoundException.class,
                () -> bookingController.getBooking(1));
    }
    @Test
    void testCreateBooking_HappyPath() {
        BookingDto newBooking = new BookingDto();
        newBooking.setUserId(2);
        newBooking.setFlightId(101L);
        newBooking.setSeatNumber("B34");
        when(bookingService.saveBooking(newBooking)).thenReturn(newBooking);
        ResponseEntity<BookingDto> response = bookingController.createBooking(newBooking);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().getUserId());
    }
}
