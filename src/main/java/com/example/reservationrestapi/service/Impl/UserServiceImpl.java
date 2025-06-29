package com.example.reservationrestapi.service.Impl;

import com.example.reservationrestapi.dto.UserDto;
import com.example.reservationrestapi.entity.User;
import com.example.reservationrestapi.exception.ResourceNotFoundException;
import com.example.reservationrestapi.exception.ConflictException;
import com.example.reservationrestapi.mapper.UserMapper;
import com.example.reservationrestapi.repository.UserRepository;
import com.example.reservationrestapi.service.UserService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto getUserById(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
        return userMapper.toDTO(user);
    }

    @Override
    public UserDto saveUser(UserDto userDTO) {
        if(userRepository.existsByUsername(userDTO.getName())){
            throw new ConflictException("Username already exists: " + userDTO.getName());
        }
        if(userRepository.existsByEmail(userDTO.getEmail())){
            throw new ConflictException("Email already exists: " + userDTO.getEmail());
        }
        User user = userMapper.toEntity(userDTO);
        user = userRepository.save(user);
        return userMapper.toDTO(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new ResourceNotFoundException("No users found");
        }
        return users.stream().map(userMapper::toDTO).collect(Collectors.toList());
    }
    @Override
    public UserDto updateUser(int userId, UserDto userDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
        user.setUsername(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());

        user = userRepository.save(user);
        return userMapper.toDTO(user);
    }
}