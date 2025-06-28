package com.example.reservationrestapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private int userId;
    private String name;
    private String email;
    private String password;
    private String role;
}
