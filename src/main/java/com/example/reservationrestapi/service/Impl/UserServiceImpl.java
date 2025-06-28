package com.example.reservationrestapi.service.Impl;

import com.example.reservationrestapi.dto.UserDto;
import com.example.reservationrestapi.entity.User;
import com.example.reservationrestapi.exception.ApiExc;
import com.example.reservationrestapi.exception.ErrorInfo;
import com.example.reservationrestapi.mapper.UserMapper;
import com.example.reservationrestapi.repository.UserRepository;
import com.example.reservationrestapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto getUserById(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApiExc(new ErrorInfo(404, "User not found ID: " + userId)));
        return userMapper.toDTO(user);
    }

    @Override
    public UserDto saveUser(UserDto userDTO) {
        validateUserForSave(userDTO);

        User user = userMapper.toEntity(userDTO);
        user = userRepository.save(user);
        return userMapper.toDTO(user);
    }

    @Override
    public List<UserDto> addMultipleUsers(List<UserDto> userDTOList) {
        if (userDTOList == null || userDTOList.isEmpty() ||
                !StringUtils.hasText(userDTOList.get(0).getName())) {
            throw new ApiExc(new ErrorInfo(400, "Invalid request"));
        }

        validateUsersForSave(userDTOList);

        List<User> users = userDTOList.stream()
                .map(userMapper::toEntity)
                .collect(Collectors.toList());

        List<User> savedUsers = userRepository.saveAll(users);
        return savedUsers.stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new ApiExc(new ErrorInfo(404, "No users found"));
        }
        return users.stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(int userId, UserDto userDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApiExc(new ErrorInfo(404, "User not found ID: " + userId)));

        validateUserForUpdate(user, userDto);

        user.setUsername(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword()); // Plain password (consider adding encoding later)
        user.setRole(userDto.getRole());

        user = userRepository.save(user);
        return userMapper.toDTO(user);
    }

    private void validateUserForSave(UserDto userDTO) {
        if (userRepository.existsByUsername(userDTO.getName())) {
            throw new ApiExc(new ErrorInfo(409, "Username already exists!"));
        }
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new ApiExc(new ErrorInfo(409, "Email already exists!"));
        }
    }

    private void validateUsersForSave(List<UserDto> userDTOList) {
        userDTOList.forEach(this::validateUserForSave);
    }

    private void validateUserForUpdate(User existingUser, UserDto updatedUserDto) {
        if (!existingUser.getUsername().equals(updatedUserDto.getName()) &&
                userRepository.existsByUsername(updatedUserDto.getName())) {
            throw new ApiExc(new ErrorInfo(409, "Username already exists!"));
        }
        if (!existingUser.getEmail().equals(updatedUserDto.getEmail()) &&
                userRepository.existsByEmail(updatedUserDto.getEmail())) {
            throw new ApiExc(new ErrorInfo(409, "Email already exists!"));
        }
    }
}