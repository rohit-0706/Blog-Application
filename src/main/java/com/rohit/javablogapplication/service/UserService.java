package com.rohit.javablogapplication.service;

import com.rohit.javablogapplication.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto,Integer userId);
    Object getUserById(Integer userId);

    List<UserDto> getAllUsers();
    void deleteUser(Integer userId);

}
