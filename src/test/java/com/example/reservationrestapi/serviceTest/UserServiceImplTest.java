package com.example.reservationrestapi.serviceTest;

import com.example.reservationrestapi.dto.UserDto;
import com.example.reservationrestapi.entity.User;
import com.example.reservationrestapi.exception.ConflictException;
import com.example.reservationrestapi.mapper.UserMapper;
import com.example.reservationrestapi.repository.UserRepository;
import com.example.reservationrestapi.service.Impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;
    private UserDto userDto;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUserId(1);
        user.setUsername("samira");
        user.setEmail("sk@gmail.com");

        userDto = new UserDto();
        userDto.setUserId(1);
        userDto.setName("samira");
        userDto.setEmail("sk@gmail.com");
    }
    @Test
    void testGetUserById_Success() {
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(userMapper.toDTO(user)).thenReturn(userDto);

        UserDto result = userService.getUserById(1);

        assertNotNull(result);
        assertEquals(1, result.getUserId());
        assertEquals("samira", result.getName());
    }
    @Test
    void testSaveUser_Success() {
        UserDto minaDto = new UserDto();
        minaDto.setName("Mina Nosrati");
        minaDto.setEmail("mn@gmail.com");

        User minaUser = new User();
        minaUser.setUsername("Mina Nosrati");
        minaUser.setEmail("mn@gmail.com");

        when(userRepository.existsByUsername("Mina Nosrati")).thenReturn(false);
        when(userRepository.existsByEmail("mn@gmail.com")).thenReturn(false);
        when(userMapper.toEntity(minaDto)).thenReturn(minaUser);
        when(userRepository.save(minaUser)).thenReturn(minaUser);
        when(userMapper.toDTO(minaUser)).thenReturn(minaDto);

        UserDto result = userService.saveUser(minaDto);

        assertNotNull(result);
        assertEquals("Mina Nosrati", result.getName());
    }
    @Test
    void testSaveUser_EmailConflict() {
        when(userRepository.existsByEmail("sk@gmail.com")).thenReturn(true);
        assertThrows(ConflictException.class, () ->
                userService.saveUser(userDto));
    }
    @Test
    void testUpdateUser_Success() {
        UserDto updatedDto = new UserDto();
        updatedDto.setName("Samira ketabi");
        updatedDto.setEmail("sk_up@gmail.com");

        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userMapper.toDTO(any(User.class))).thenReturn(updatedDto);

        UserDto result = userService.updateUser(1, updatedDto);

        assertNotNull(result);
        assertEquals("Samira ketabi", result.getName());
        assertEquals("sk_up@gmail.com", result.getEmail());
    }
}
