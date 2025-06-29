package com.example.reservationrestapi.controller;

import com.example.reservationrestapi.dto.FlightDto;
import com.example.reservationrestapi.exception.ApiResExc;
import com.example.reservationrestapi.service.Impl.FlightServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @Autowired
    private FlightServiceImpl flightService;

    @GetMapping("/{flightId}")
    public ResponseEntity<ApiResExc<FlightDto>> getFlight(@PathVariable Long flightId) {
        FlightDto flightById = flightService.getFlightById(flightId);
        return ResponseEntity.ok(new ApiResExc<>(true, flightById,null));
    }
    @PostMapping
    public ResponseEntity<ApiResExc<FlightDto>> createFlight(@RequestBody FlightDto flightDto) {
        FlightDto createFlight = flightService.saveFlight(flightDto);
        return ResponseEntity.ok(new ApiResExc<>(true, createFlight,null));
    }
}
