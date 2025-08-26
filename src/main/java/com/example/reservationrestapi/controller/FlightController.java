package com.example.reservationrestapi.controller;

import com.example.reservationrestapi.dto.FlightDto;
import com.example.reservationrestapi.service.Impl.FlightServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @Autowired
    private FlightServiceImpl flightService;

    @PostMapping
    public ResponseEntity<FlightDto> createFlight(@RequestBody FlightDto flightDto) {
        return ResponseEntity.ok(flightService.saveFlight(flightDto));
    }
    @GetMapping("/{flightId}")
    public ResponseEntity<FlightDto> getFlight(@PathVariable Long flightId) {
        return ResponseEntity.ok(flightService.getFlightById(flightId));
    }
}