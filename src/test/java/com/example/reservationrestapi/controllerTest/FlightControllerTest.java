package com.example.reservationrestapi.controllerTest;

import com.example.reservationrestapi.controller.FlightController;
import com.example.reservationrestapi.dto.FlightDto;
import com.example.reservationrestapi.exception.ResourceNotFoundException;
import com.example.reservationrestapi.service.Impl.FlightServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FlightControllerTest {

    @Mock
    private FlightServiceImpl flightService;

    @InjectMocks
    private FlightController flightController;

    private FlightDto flightDto;

    @BeforeEach
    void setUp() {
        flightDto = new FlightDto();
        flightDto.setFlightId(100L);
        flightDto.setFlightNumber("FL123");
        flightDto.setDepartureCity("New York");
        flightDto.setDestinationCity("London");
        flightDto.setAvailableSeats(150);
    }
    @Test
    void testCreateFlight_HappyPath() {
        when(flightService.saveFlight(flightDto)).thenReturn(flightDto);
        //act
        ResponseEntity<FlightDto> response = flightController.createFlight(flightDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("London", response.getBody().getDestinationCity());
    }
    @Test
    void testGetFlight_HappyPath() {
        when(flightService.getFlightById(100L)).thenReturn(flightDto);
        //act
        ResponseEntity<FlightDto> response = flightController.getFlight(100L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("FL123", response.getBody().getFlightNumber());
    }
    @Test
    void testGetFlight_NotFound() {
        when(flightService.getFlightById(100L))
                .thenThrow(new ResourceNotFoundException("Flight not found"));
        assertThrows(ResourceNotFoundException.class,
                () -> flightController.getFlight(100L));
    }
}
