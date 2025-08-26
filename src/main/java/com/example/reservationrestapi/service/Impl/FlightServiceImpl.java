package com.example.reservationrestapi.service.Impl;

import com.example.reservationrestapi.dto.FlightDto;
import com.example.reservationrestapi.entity.Flight;
import com.example.reservationrestapi.exception.ResourceNotFoundException;
import com.example.reservationrestapi.exception.ConflictException;
import com.example.reservationrestapi.mapper.FlightMapper;
import com.example.reservationrestapi.repository.FlightRepository;
import com.example.reservationrestapi.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private FlightMapper flightMapper;

    @Override
    public FlightDto saveFlight(FlightDto flightDto) {
        if (flightDto.getFlightId() != null && flightRepository.existsById(flightDto.getFlightId())) {
            throw new ConflictException("Flight already exists with ID: " + flightDto.getFlightId());
        }
        Flight flight = flightMapper.toEntity(flightDto);
        flight = flightRepository.save(flight);
        return flightMapper.toDTO(flight);
    }

    @Override
    public FlightDto getFlightById(Long flightId) {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found with ID: " + flightId));
        return flightMapper.toDTO(flight);
    }
}