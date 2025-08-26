package com.example.reservationrestapi.service;

import com.example.reservationrestapi.dto.BookingDto;

public interface BookingService {

    BookingDto saveBooking(BookingDto bookingDto);
    BookingDto getBookingById(int bookingId);
}
