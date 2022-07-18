package com.example.snjofko.app_user.service;

import com.example.snjofko.app_user.model.command.LoginCommand;
import com.example.snjofko.app_user.model.command.SignupCommand;
import com.example.snjofko.app_user.model.dto.LoginResponseDTO;

public interface AppUserService {

    Boolean signup(SignupCommand cmd);

    LoginResponseDTO login(LoginCommand cmd);
}
