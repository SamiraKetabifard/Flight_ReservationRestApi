package com.example.reservationrestapi.mapperTest;

import com.example.reservationrestapi.dto.FlightDto;
import com.example.reservationrestapi.entity.Flight;
import com.example.reservationrestapi.mapper.FlightMapper;
import com.example.reservationrestapi.mapper.FlightMapperImpl;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FlightMapperTest {

    private final FlightMapper flightMapper = new FlightMapperImpl();

    @Test
    void testMapEntityToDTO() {
        Flight flight = new Flight();
        flight.setFlightId(1L);
        flight.setFlightNumber("FL123");
        flight.setDepartureCity("New York");
        flight.setDestinationCity("London");
        flight.setAvailableSeats(150);
        //act
        FlightDto dto = flightMapper.toDTO(flight);

        assertEquals(1L, dto.getFlightId());
        assertEquals("FL123", dto.getFlightNumber());
        assertEquals("New York", dto.getDepartureCity());
        assertEquals("London", dto.getDestinationCity());
        assertEquals(150, dto.getAvailableSeats());
    }
    @Test
    void testMapDtoToEntity() {
        FlightDto dto = new FlightDto();
        dto.setFlightId(1L);
        dto.setFlightNumber("FL123");
        dto.setDepartureCity("New York");
        dto.setDestinationCity("London");
        dto.setAvailableSeats(150);
        //act
        Flight flight = flightMapper.toEntity(dto);
        //assert
        assertEquals(1L, flight.getFlightId());
        assertEquals("FL123", flight.getFlightNumber());
        assertEquals("New York", flight.getDepartureCity());
        assertEquals("London", flight.getDestinationCity());
        assertEquals(150, flight.getAvailableSeats());
    }
}
