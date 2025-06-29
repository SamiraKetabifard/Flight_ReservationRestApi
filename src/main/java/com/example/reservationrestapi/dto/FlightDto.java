package com.example.reservationrestapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlightDto {

    private Long flightId;
    private String flightNumber;
    private String departureCity;
    private String destinationCity;
    private int availableSeats;
}
