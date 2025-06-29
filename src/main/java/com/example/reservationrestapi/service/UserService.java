package com.example.reservationrestapi.service;

import com.example.reservationrestapi.dto.UserDto;
import java.util.List;

public interface UserService {

    UserDto getUserById(int userId);
    UserDto saveUser(UserDto userDto);
    List<UserDto> getAllUsers();
    UserDto updateUser(int userId, UserDto userDto);
}
