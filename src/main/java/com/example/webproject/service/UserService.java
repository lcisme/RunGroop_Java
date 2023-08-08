package com.example.webproject.service;

import com.example.webproject.dto.RegistrationDTO;
import com.example.webproject.entity.UserEntity;

public interface UserService {
    void saveUser(RegistrationDTO registrationDTO);

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);
}
