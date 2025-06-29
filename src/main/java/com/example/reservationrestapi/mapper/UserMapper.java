package com.example.reservationrestapi.mapper;

import com.example.reservationrestapi.dto.UserDto;
import com.example.reservationrestapi.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "username", target = "name")
    UserDto toDTO(User user);

    @Mapping(source = "name", target = "username")
    User toEntity(UserDto dto);
}