package com.poc.service.impl;

import com.poc.constant.ConstantType;
import com.poc.dto.UserDto;
import com.poc.entity.UserEntity;
import com.poc.exception.UserException;
import com.poc.repo.UserRepository;
import com.poc.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto save(UserDto user) {
        return entityToDto(userRepository.save(dtoToEntity(user)));
    }

    @Override
    public String updateUser(UserDto user) {
        UserEntity save = userRepository.save(dtoToEntity(user));
        return "successfully update ";
    }

    @Override
    public UserDto getUserById(Long id) {
        UserEntity byId = userRepository.findById(id).orElse(null);
        if (Objects.isNull(byId)) {
            throw new UserException("This User Not Present " + id, HttpStatus.NO_CONTENT);
        }
        return entityToDto(byId);
    }

    @Override
    public List<UserDto> getSalary(Long salary) {
        List<UserEntity> bySalary = userRepository.findBySalary(salary);
        List<UserDto> userDtos = new ArrayList<>();
        for (UserEntity userEntity : bySalary) {
            userDtos.add(entityToDto(userEntity));
        }
        return userDtos;
    }


    public List<UserDto> findByType(int enums) {
        ConstantType value = ConstantType.values()[enums];
        List<UserEntity> byType = userRepository.findByType(value);
        List<UserDto> userDtos = new ArrayList<>();
        for (UserEntity userEntity : byType) {
            userDtos.add(entityToDto(userEntity));
        }
        return userDtos;
    }

    @Override
    public List<UserDto> findLocation(double centerLatitude, double centerLongitude, double radius) {
        final int R = 6371; // Radius of the earth
        List<UserEntity> userEntities = new ArrayList<>();
        List<UserEntity> allUser = userRepository.findAll();

        if (allUser.isEmpty()) {

            throw new UserException("No Data present inside the database");
        }
        for (UserEntity user : allUser) {
            double latitude = user.getLatitude();
            double longitude = user.getLongitude();
            double latDistance = Math.toRadians(latitude - centerLatitude);
            double lonDistance = Math.toRadians(longitude - centerLongitude);
            double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(Math.toRadians(centerLatitude))
                    * Math.cos(Math.toRadians(latitude)) * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            double distance = R * c * 1000; // convert to meters
            distance = Math.pow(distance, 2);
            double sqrt = Math.sqrt(distance);
            if (radius <= sqrt) {
                userEntities.add(user);
            }
        }
        List<UserDto> userDtos = new ArrayList<>();
        for (UserEntity userEntity : userEntities) {
            userDtos.add(entityToDto(userEntity));
        }

        if (userDtos.isEmpty()) {
            throw new UserException("No any User present inside radius " + radius);
        }
        return userDtos;


    }

    private UserEntity dtoToEntity(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDto, userEntity);
        return userEntity;
    }

    private UserDto entityToDto(UserEntity userEntity) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userEntity, userDto);
        return userDto;
    }
}
