package com.example.reservationrestapi.service;

import com.example.reservationrestapi.dto.FlightDto;

public interface FlightService {

    FlightDto saveFlight(FlightDto flightDto);
    FlightDto getFlightById(Long flightId);
}
