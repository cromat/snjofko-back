package com.example.snjofko.app_user.model.command;

import lombok.Data;

@Data
public class SignupCommand {
    private String username;
    private String password;
    private String confirmedPassword;
    private String fullName;
    private String email;
    private String phone;
}
