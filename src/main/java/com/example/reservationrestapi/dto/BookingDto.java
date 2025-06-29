package com.example.reservationrestapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingDto {

    private int bookingId;
    private int userId;
    private Long flightId;
    private String seatNumber;
}
