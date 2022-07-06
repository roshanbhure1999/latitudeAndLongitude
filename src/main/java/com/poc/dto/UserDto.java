package com.poc.dto;

import com.poc.constant.ConstantType;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data

public class UserDto {
    private Long id;
    @NotBlank(message = "Name.required ")
    private String name;
    @Email(message = "Email.required ")
    private String email;
    @NotBlank(message = "Salary.required ")
    private double salary;
    @NotBlank(message = "Type.required ")
    private ConstantType type;
    @NotBlank(message = "Longitude.required ")
    private double longitude;
    @NotBlank(message = "Latitude.required ")
    private double latitude;
}
