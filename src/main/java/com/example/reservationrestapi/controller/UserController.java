package com.example.reservationrestapi.controller;

import com.example.reservationrestapi.dto.UserDto;
import com.example.reservationrestapi.exception.ApiResExc;
import com.example.reservationrestapi.exception.ErrorInfo;
import com.example.reservationrestapi.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResExc<UserDto>> getUser(@PathVariable int userId) {
        UserDto getUser = userService.getUserById(userId);
        if(getUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResExc<>(false, null, new ErrorInfo(404, "User not found")));
        }
        return ResponseEntity.ok(new ApiResExc<>(true, getUser, null));
    }
    @PostMapping
    public ResponseEntity<ApiResExc<UserDto>> createUser(@RequestBody UserDto userDto) {
        UserDto createUser = userService.saveUser(userDto);
        return ResponseEntity.ok(new ApiResExc<>(true, createUser, null));
    }

    @PostMapping("/addMultiple")
    public ResponseEntity<ApiResExc<List<UserDto>>> addUsers(@RequestBody List<UserDto> userDTOList) {
        List<UserDto> addUsers = userService.addMultipleUsers(userDTOList);
        return ResponseEntity.ok(new ApiResExc<>(true, addUsers, null));
    }

    @GetMapping
    public ResponseEntity<ApiResExc<List<UserDto>>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(new ApiResExc<>(true, users, null));
    }
    @PutMapping("/{userId}")
    public ResponseEntity<ApiResExc<UserDto>> updateUser(@PathVariable int userId, @RequestBody UserDto userDto) {
        UserDto updatedUser = userService.updateUser(userId,userDto);
        if(updatedUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResExc<>(false, null, new ErrorInfo(404, "User not found")));
        }
        return ResponseEntity.ok(new ApiResExc<>(true, updatedUser, null));
    }
}
