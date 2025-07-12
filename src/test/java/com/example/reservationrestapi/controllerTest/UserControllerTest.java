package com.example.reservationrestapi.controllerTest;

import com.example.reservationrestapi.controller.UserController;
import com.example.reservationrestapi.dto.UserDto;
import com.example.reservationrestapi.service.Impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private UserController userController;

    private UserDto samiraDto;
    private UserDto minaDto;

    @BeforeEach
    void setUp() {
        samiraDto = new UserDto();
        samiraDto.setUserId(1);
        samiraDto.setName("samira");
        samiraDto.setEmail("sk@gmail.com");

        minaDto = new UserDto();
        minaDto.setUserId(2);
        minaDto.setName("Mina Nosrati");
        minaDto.setEmail("mn@gmail.com");
    }
    @Test
    void testCreateUser_Success() {
        when(userService.saveUser(minaDto)).thenReturn(minaDto);
        ResponseEntity<UserDto> response = userController.createUser(minaDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Mina Nosrati", response.getBody().getName());
    }
    @Test
    void testGetAllUsers_Success() {
        when(userService.getAllUsers()).thenReturn(List.of(samiraDto, minaDto));
        ResponseEntity<List<UserDto>> response = userController.getAllUsers();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        assertTrue(response.getBody().stream().anyMatch(u -> u.getName().equals("samira")));
        assertTrue(response.getBody().stream().anyMatch(u -> u.getName().equals("Mina Nosrati")));
    }
    @Test
    void testUpdateUser_Success() {
        UserDto updatedSamira = new UserDto();
        updatedSamira.setName("Samira ketabifard");
        updatedSamira.setEmail("sk_new@gmail.com");
        when(userService.updateUser(1, updatedSamira)).thenReturn(updatedSamira);
        ResponseEntity<UserDto> response = userController.updateUser(1, updatedSamira);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Samira ketabifard", response.getBody().getName());
    }
}