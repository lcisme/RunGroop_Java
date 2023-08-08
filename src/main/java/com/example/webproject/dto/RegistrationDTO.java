package com.example.webproject.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
@Data
public class RegistrationDTO {
    private Long id;
    @NotEmpty
    private String username;
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
}

