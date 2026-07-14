package ru.yandex.authapp.dto;

import lombok.*;
@Data
@Builder
public class UserResponse {

    private String id;
    private String username;
    private String email;
    private boolean enabled;

    public UserResponse(String id, String username, String email, boolean enabled) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.enabled = enabled;
    }
}
