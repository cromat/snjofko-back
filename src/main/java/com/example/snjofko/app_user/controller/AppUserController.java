package com.example.snjofko.app_user.controller;

import com.example.snjofko.app_user.model.command.LoginCommand;
import com.example.snjofko.app_user.model.command.SignupCommand;
import com.example.snjofko.app_user.model.dto.LoginResponseDTO;
import com.example.snjofko.app_user.service.AppUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class AppUserController {

    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping("signup")
    public ResponseEntity<Boolean> signup(@RequestBody SignupCommand cmd) {
        return ResponseEntity.ok(appUserService.signup(cmd));
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginCommand cmd) {
        return ResponseEntity.ok(appUserService.login(cmd));
    }

}
