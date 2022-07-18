package com.example.snjofko.app_user.service;

import com.example.snjofko.app_user.model.command.LoginCommand;
import com.example.snjofko.app_user.model.command.SignupCommand;
import com.example.snjofko.app_user.model.dto.LoginResponseDTO;
import com.example.snjofko.app_user.model.entity.AppUser;
import com.example.snjofko.app_user.repository.AppUserRepository;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final AuthenticationManager authenticationManager;

    public AppUserServiceImpl(AppUserRepository appUserRepository, AuthenticationManager authenticationManager) {
        this.appUserRepository = appUserRepository;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Boolean signup(SignupCommand cmd) {

        if (cmd.getPassword().equals(cmd.getConfirmedPassword())) {
            BasicPasswordEncryptor passEncryptor = new BasicPasswordEncryptor();
            AppUser user = AppUser.builder()
                    .username(cmd.getUsername())
                    .email(cmd.getEmail())
                    .fullName(cmd.getFullName())
                    .phone(cmd.getPhone())
                    .password(passEncryptor.encryptPassword(cmd.getPassword()))
                    .build();
            appUserRepository.save(user);
            return true;
        }

        return false;
    }

    @Override
    public LoginResponseDTO login(LoginCommand cmd) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(null, cmd));
        return null;
    }

}
