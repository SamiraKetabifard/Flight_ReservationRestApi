package com.example.reservationrestapi.mapper;

import com.example.reservationrestapi.dto.FlightDto;
import com.example.reservationrestapi.entity.Flight;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FlightMapper {

    FlightDto toDTO(Flight flight);
    Flight toEntity(FlightDto dto);
}