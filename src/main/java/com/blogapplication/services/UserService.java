package com.blogapplication.services;

import com.blogapplication.payloads.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto, Integer userId);
    UserDto getUser(Integer userId);
    List<UserDto> getAllUser();
    void deleteUser(Integer userId);

}
