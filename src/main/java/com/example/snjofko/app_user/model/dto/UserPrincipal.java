package com.example.snjofko.app_user.model.dto;

import com.example.snjofko.app_user.model.entity.AppUser;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserPrincipal {
    private Long id;
    private String fullName;
    private String username;
    private String email;
    private String phone;

    public static UserPrincipal fromAppUser(AppUser user) {
        return UserPrincipal.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .username(user.getUsername())
                .email(user.getEmail())
                .phone(user.getPhone())
                .build();
    }
}
