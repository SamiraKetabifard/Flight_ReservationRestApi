package com.example.reservationrestapi.mapper;

import com.example.reservationrestapi.dto.FlightDto;
import com.example.reservationrestapi.entity.Flight;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FlightMapper {

    @Mapping(source = "flightId", target = "flightId")
    @Mapping(source = "flightNumber", target = "flightNumber")
    @Mapping(source = "departureCity", target = "departureCity")
    @Mapping(source = "destinationCity", target = "destinationCity")
    @Mapping(source = "availableSeats", target = "availableSeats")
    FlightDto toDTO(Flight flight);

    @Mapping(source = "flightId", target = "flightId")
    @Mapping(source = "flightNumber", target = "flightNumber")
    @Mapping(source = "departureCity", target = "departureCity")
    @Mapping(source = "destinationCity", target = "destinationCity")
    @Mapping(source = "availableSeats", target = "availableSeats")
    Flight toEntity(FlightDto dto);
}