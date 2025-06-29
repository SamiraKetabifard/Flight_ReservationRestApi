package com.example.reservationrestapi.service.Impl;

import com.example.reservationrestapi.dto.BookingDto;
import com.example.reservationrestapi.entity.Booking;
import com.example.reservationrestapi.entity.Flight;
import com.example.reservationrestapi.entity.User;
import com.example.reservationrestapi.exception.ResourceNotFoundException;
import com.example.reservationrestapi.exception.ConflictException;
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
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
        return bookingMapper.toDTO(booking);
    }

    @Override
    public BookingDto saveBooking(BookingDto bookingDTO) {
        User user = userRepository.findById(bookingDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + bookingDTO.getUserId()));
        Flight flight = flightRepository.findById(bookingDTO.getFlightId())
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found with ID: " + bookingDTO.getFlightId()));

        Booking booking = bookingMapper.toEntity(bookingDTO, user, flight);
        booking = bookingRepository.save(booking);
        return bookingMapper.toDTO(booking);
    }
}