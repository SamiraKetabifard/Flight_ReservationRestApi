package com.example.reservationrestapi.service.Impl;

import com.example.reservationrestapi.dto.BookingDto;
import com.example.reservationrestapi.entity.Booking;
import com.example.reservationrestapi.entity.Flight;
import com.example.reservationrestapi.entity.User;
import com.example.reservationrestapi.exception.ApiExc;
import com.example.reservationrestapi.exception.ErrorInfo;
import com.example.reservationrestapi.mapper.BookingMapper;
import com.example.reservationrestapi.repository.BookingRepository;
import com.example.reservationrestapi.repository.FlightRepository;
import com.example.reservationrestapi.repository.UserRepository;
import com.example.reservationrestapi.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private BookingMapper bookingMapper;

    @Override
    public BookingDto getBookingById(int id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ApiExc(new ErrorInfo(404, "Booking not found")));
        return bookingMapper.toDTO(booking);
    }
    @Override
    public BookingDto saveBooking(BookingDto bookingDTO) {
        User user = userRepository.findById(bookingDTO.getUserId())
                .orElseThrow(() -> new ApiExc(new ErrorInfo(404, "User not found ID: " + bookingDTO.getUserId())));
        Flight flight = flightRepository.findById(bookingDTO.getFlightId())
                .orElseThrow(() -> new ApiExc(new ErrorInfo(404, "Flight not found ID: " + bookingDTO.getFlightId())));

        // Use the mapper with all three arguments
        Booking booking = bookingMapper.toEntity(bookingDTO, user, flight);
        booking = bookingRepository.save(booking);
        return bookingMapper.toDTO(booking);
    }
}