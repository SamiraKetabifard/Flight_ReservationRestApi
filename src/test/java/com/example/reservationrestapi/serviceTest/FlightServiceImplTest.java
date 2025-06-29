package com.example.reservationrestapi.serviceTest;

import com.example.reservationrestapi.dto.FlightDto;
import com.example.reservationrestapi.entity.Flight;
import com.example.reservationrestapi.exception.ConflictException;
import com.example.reservationrestapi.exception.ResourceNotFoundException;
import com.example.reservationrestapi.mapper.FlightMapper;
import com.example.reservationrestapi.repository.FlightRepository;
import com.example.reservationrestapi.service.Impl.FlightServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FlightServiceImplTest {

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private FlightMapper flightMapper;

    @InjectMocks
    private FlightServiceImpl flightService;

    private FlightDto flightDto;
    private Flight flight;

    @BeforeEach
    void setUp() {
        flightDto = new FlightDto();
        flightDto.setFlightId(100L);
        flightDto.setFlightNumber("FL123");
        flightDto.setDepartureCity("New York");
        flightDto.setDestinationCity("London");
        flightDto.setAvailableSeats(150);

        flight = new Flight();
        flight.setFlightId(100L);
        flight.setFlightNumber("FL123");
    }
    @Test
    void testGetFlightById_HappyPath() {
        when(flightRepository.findById(100L)).thenReturn(Optional.of(flight));
        when(flightMapper.toDTO(flight)).thenReturn(flightDto);

        FlightDto result = flightService.getFlightById(100L);

        assertEquals("FL123", result.getFlightNumber());
    }
    @Test
    void testGetFlightById_NotFound() {
        when(flightRepository.findById(100L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> flightService.getFlightById(100L));
    }
    @Test
    void testSaveFlight_HappyPath() {
        FlightDto newFlightDto = new FlightDto();
        newFlightDto.setFlightNumber("FL456");

        Flight newFlight = new Flight();
        newFlight.setFlightNumber("FL456");

        when(flightMapper.toEntity(newFlightDto)).thenReturn(newFlight);
        when(flightRepository.save(newFlight)).thenReturn(newFlight);
        when(flightMapper.toDTO(newFlight)).thenReturn(newFlightDto);

        FlightDto result = flightService.saveFlight(newFlightDto);

        assertEquals("FL456", result.getFlightNumber());
    }
    @Test
    void testSaveFlight_Conflict() {
        when(flightRepository.existsById(100L)).thenReturn(true);
        assertThrows(ConflictException.class,
                () -> flightService.saveFlight(flightDto));
    }
}
