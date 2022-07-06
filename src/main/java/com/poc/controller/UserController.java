package com.poc.controller;

import com.poc.dto.UserDto;
import com.poc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public UserDto save(@RequestBody @Valid UserDto user) {
        return userService.save(user);
    }

    @PutMapping("/update")
    public String update(@RequestBody UserDto user) {
        return userService.updateUser(user);
    }

    @GetMapping("/get/{Id}")
    public UserDto getUserById(@PathVariable Long Id) {
        return userService.getUserById(Id);
    }

    @GetMapping("/salary/{salary}")
    public List<UserDto> getSalary(@PathVariable Long salary) {
        return userService.getSalary(salary);
    }

    @GetMapping("/type/{type}")
    public List<UserDto> findByType(@PathVariable int type) {
        return userService.findByType(type);
    }

    @GetMapping("/location/latitude/longitude/{centerLatitude}/{centerLongitude}/{radius}")
    public List<UserDto> findLocation(@PathVariable double centerLatitude, @PathVariable double centerLongitude,@PathVariable double radius){
        return userService.findLocation(centerLatitude,centerLongitude,radius);
    }
}
