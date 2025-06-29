package com.example.reservationrestapi.excTest;

import com.example.reservationrestapi.exception.ConflictException;
import com.example.reservationrestapi.exception.GlobalExceptionHandler;
import com.example.reservationrestapi.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void testHandleUserConflict() {
        ConflictException ex = new ConflictException("Email sk@gmail.com already exists");
        ResponseEntity<String> response = handler.handleConflict(ex);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Email sk@gmail.com already exists", response.getBody());
    }
    @Test
    void testHandleUserNotFound() {
        ResourceNotFoundException ex = new ResourceNotFoundException("User Mina Nosrati not found");
        ResponseEntity<String> response = handler.handleResourceNotFound(ex);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User Mina Nosrati not found", response.getBody());
    }
}
