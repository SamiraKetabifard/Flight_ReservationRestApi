package com.example.reservationrestapi.service;

import com.example.reservationrestapi.dto.UserDto;
import java.util.List;

public interface UserService {

    UserDto saveUser(UserDto userDto);
    UserDto getUserById(int userId);
    List<UserDto> getAllUsers();
    UserDto updateUser(int userId, UserDto userDto);
}
