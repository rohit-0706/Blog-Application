package com.rohit.javablogapplication.service.Impl;

import com.rohit.javablogapplication.dto.UserDto;
import com.rohit.javablogapplication.entity.User;
import com.rohit.javablogapplication.exceptions.ResourceNotFoundException;
import com.rohit.javablogapplication.repository.UserRepository;
import com.rohit.javablogapplication.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        User savedUser = this.userRepository.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id", userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        User updatedUser = userRepository.save(user);
        return userToDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer userId) throws ResourceNotFoundException {
    User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
    return  this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        return users.stream().map(this::userToDto).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","Id", userId));
        this.userRepository.delete(user);
    }

    private User dtoToUser(UserDto userDto){
        return this.modelMapper.map(userDto,User.class);
    }

    public UserDto userToDto (User user){
        return this.modelMapper.map(user, UserDto.class);
    }
}
