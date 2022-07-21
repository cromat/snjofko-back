package com.example.snjofko.security;

import com.example.snjofko.app_user.model.command.LoginCommand;
import com.example.snjofko.app_user.model.dto.UserPrincipal;
import com.example.snjofko.app_user.model.entity.AppUser;
import com.example.snjofko.app_user.repository.AppUserRepository;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class AppUserAuthProvider implements AuthenticationManager {

    private final AppUserRepository appUserRepository;

    public AppUserAuthProvider(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        LoginCommand cmd = (LoginCommand) authentication.getCredentials();
        BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
        Optional<AppUser> user = appUserRepository.findByUsername(cmd.getUsername());
        if (user.isPresent() && passwordEncryptor.checkPassword(cmd.getPassword(), user.get().getPassword())) {
            return new UsernamePasswordAuthenticationToken(UserPrincipal.fromAppUser(user.get()), null, new ArrayList<>());
        }

        throw new AuthenticationServiceException("Error during authentication!");
    }



}
