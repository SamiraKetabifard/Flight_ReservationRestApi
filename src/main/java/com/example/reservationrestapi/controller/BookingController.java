package com.example.reservationrestapi.controller;

import com.example.reservationrestapi.dto.BookingDto;
import com.example.reservationrestapi.exception.ApiResExc;
import com.example.reservationrestapi.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @GetMapping("/{bookingId}")
    public ResponseEntity<ApiResExc<BookingDto>> getBooking(@PathVariable int bookingId) {
        BookingDto getBookingById = bookingService.getBookingById(bookingId);
        return ResponseEntity.ok(new ApiResExc<>(true, getBookingById, null));
    }

    @PostMapping
    public ResponseEntity<ApiResExc<BookingDto>> createBooking(@RequestBody BookingDto bookingDto) {
        BookingDto createBooking= bookingService.saveBooking(bookingDto);
        return ResponseEntity.ok(new ApiResExc<>(true, createBooking, null));
    }
}
