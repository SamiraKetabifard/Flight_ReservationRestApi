package com.example.reservationrestapi.service;

import com.example.reservationrestapi.dto.FlightDto;

public interface FlightService {

    FlightDto getFlightById(Long flightId);
    FlightDto saveFlight(FlightDto flightDto);
}
