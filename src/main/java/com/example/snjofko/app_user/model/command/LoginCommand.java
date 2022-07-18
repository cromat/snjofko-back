package com.example.snjofko.app_user.model.command;

import lombok.Data;

@Data
public class LoginCommand {
    private String username;
    private String password;
}
