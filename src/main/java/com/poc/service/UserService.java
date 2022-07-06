package com.poc.service;

import com.poc.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto save(UserDto user);

    String updateUser(UserDto user);

    UserDto getUserById(Long id);

    List<UserDto> getSalary(Long salary);

    List<UserDto> findByType(int type);

    List<UserDto> findLocation(double centerLatitude, double centerLongitude, double radius);
}

