package com.example.reservationrestapi.service;

import com.example.reservationrestapi.dto.BookingDto;

public interface BookingService {
    BookingDto getBookingById(int bookingId);
    BookingDto saveBooking(BookingDto bookingDTO);

}
