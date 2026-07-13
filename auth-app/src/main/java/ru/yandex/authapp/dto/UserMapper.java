package ru.yandex.authapp.dto;

import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
@Component
public class UserMapper {

    public UserResponse toResponse(UserRepresentation user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .enabled(Boolean.TRUE.equals(user.isEnabled()))
                .build();
    }
}
