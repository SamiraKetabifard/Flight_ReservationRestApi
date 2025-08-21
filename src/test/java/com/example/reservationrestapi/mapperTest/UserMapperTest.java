package com.example.reservationrestapi.mapperTest;

import com.example.reservationrestapi.dto.UserDto;
import com.example.reservationrestapi.entity.User;
import com.example.reservationrestapi.mapper.UserMapper;
import com.example.reservationrestapi.mapper.UserMapperImpl;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    private final UserMapper userMapper = new UserMapperImpl();

    @Test
    void testToDTO() {
        User user = new User();
        user.setUserId(1);
        user.setUsername("samira");
        user.setEmail("sk@gmail.com");

        UserDto dto = userMapper.toDTO(user);

        assertEquals(1, dto.getUserId());
        assertEquals("samira", dto.getName());
        assertEquals("sk@gmail.com", dto.getEmail());
    }
    @Test
    void testToEntity() {
        UserDto dto = new UserDto();
        dto.setUserId(2);
        dto.setName("Mina Nosrati");
        dto.setEmail("mn@gmail.com");

        User user = userMapper.toEntity(dto);

        assertEquals(2, user.getUserId());
        assertEquals("Mina Nosrati", user.getUsername());
        assertEquals("mn@gmail.com", user.getEmail());
    }
}
