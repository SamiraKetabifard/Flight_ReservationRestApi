package com.example.reservationrestapi.controller;

import com.example.reservationrestapi.dto.BookingDto;
import com.example.reservationrestapi.service.Impl.BookingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingServiceImpl bookingService;

    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingDto> getBooking(@PathVariable int bookingId) {
        return ResponseEntity.ok(bookingService.getBookingById(bookingId));
    }
    @PostMapping
    public ResponseEntity<BookingDto> createBooking(@RequestBody BookingDto bookingDto) {
        return ResponseEntity.ok(bookingService.saveBooking(bookingDto));
    }
}