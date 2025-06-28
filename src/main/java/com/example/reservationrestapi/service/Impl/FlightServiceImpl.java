package com.example.reservationrestapi.service.Impl;

import com.example.reservationrestapi.dto.FlightDto;
import com.example.reservationrestapi.entity.Flight;
import com.example.reservationrestapi.exception.ApiExc;
import com.example.reservationrestapi.exception.ErrorInfo;
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
    private FlightMapper flightMapper;  // Autowired MapStruct mapper

    @Override
    public FlightDto getFlightById(Long flightId) {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new ApiExc(new ErrorInfo(404, "Flight not found ID: " + flightId)));
        return flightMapper.toDTO(flight);  // Using mapper instance
    }

    @Override
    public FlightDto saveFlight(FlightDto flightDto) {
        if (flightRepository.existsById(flightDto.getFlightId())) {
            throw new ApiExc(new ErrorInfo(409, "Flight Already Exists"));
        }

        Flight flight = flightMapper.toEntity(flightDto);  // Using mapper instance
        flight = flightRepository.save(flight);
        return flightMapper.toDTO(flight);  // Using mapper instance
    }
}
